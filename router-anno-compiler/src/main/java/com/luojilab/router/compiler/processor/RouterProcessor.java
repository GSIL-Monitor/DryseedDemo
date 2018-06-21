package com.luojilab.router.compiler.processor;

import com.google.auto.service.AutoService;
import com.luojilab.router.compiler.utils.FileUtils;
import com.luojilab.router.compiler.utils.Logger;
import com.luojilab.router.compiler.utils.TypeUtils;
import com.luojilab.router.facade.annotation.Autowired;
import com.luojilab.router.facade.annotation.RouteNode;
import com.luojilab.router.facade.enums.NodeType;
import com.luojilab.router.facade.model.Node;
import com.luojilab.router.facade.utils.RouteUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.luojilab.router.compiler.utils.Constants.ACTIVITY;
import static com.luojilab.router.compiler.utils.Constants.ANNOTATION_TYPE_ROUTER;
import static com.luojilab.router.compiler.utils.Constants.ANNOTATION_TYPE_ROUTE_NODE;
import static com.luojilab.router.compiler.utils.Constants.BASECOMPROUTER;
import static com.luojilab.router.compiler.utils.Constants.KEY_HOST_NAME;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * <p><b>Package:</b> com.luojilab.router.compiler.processor </p>
 * <p><b>Project:</b> DDComponentForAndroid </p>
 * <p><b>Classname:</b> RouterProcessor </p>
 * <p><b>Description:</b> generate RouterLoader class for 'Router' annotated class,
 * parse 'RouteNode' annotated Activities to mapper
 * </p>
 * Created by leobert on 2017/9/18.
 */
@AutoService(Processor.class)
@SupportedOptions(KEY_HOST_NAME)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({ANNOTATION_TYPE_ROUTE_NODE, ANNOTATION_TYPE_ROUTER})
public class RouterProcessor extends AbstractProcessor {

    private static final String mRouteMapperFieldName = "routeMapper";
    private static final String mParamsMapperFieldName = "paramsMapper";

    private Logger logger;

    private Filer mFiler;
    private Types types;
    private Elements elements;

    private TypeMirror type_String;

    private ArrayList<Node> routerNodes;
    private TypeUtils typeUtils;
    private String host = null;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        routerNodes = new ArrayList<>();

        //Filer：用于创建 Java 文件的工具类。
        mFiler = processingEnv.getFiler();
        //Types：用于操作类型的工具类。
        types = processingEnv.getTypeUtils();
        //Elements：用于处理 Element 的工具类。
        elements = processingEnv.getElementUtils();
        //将两个工具类进行封装。
        typeUtils = new TypeUtils(types, elements);

        type_String = elements.getTypeElement("java.lang.String").asType();

        logger = new Logger(processingEnv.getMessager());

        //解析我们在 android 节点下配置的 moduleName，其为对应模块的名字。
        Map<String, String> options = processingEnv.getOptions();
        if (MapUtils.isNotEmpty(options)) {
            host = options.get(KEY_HOST_NAME);
            logger.info(">>> host is " + host + " <<<");
        }
        if (host == null || host.equals("")) {
            host = "default";
        }
        logger.info(">>> RouteProcessor init. <<<");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (CollectionUtils.isNotEmpty(set)) {
            //获得所有被 @RouteNode 注解的元素。
            Set<? extends Element> routeNodes = roundEnvironment.getElementsAnnotatedWith(RouteNode.class);
            try {
                logger.info(">>> Found routes, start... <<<");
                //开始处理。
                parseRouteNodes(routeNodes);
            } catch (Exception e) {
                logger.error(e);
            }
            //生成HostUIRouter.java
            generateRouterImpl();
            //生成HostRouterTable.txt
            generateRouterTable();
            return true;
        }
        return false;
    }

    /*
        AppRouterTable.txt

            auto generated, do not change !!!!

            HOST : app

            butterknife
            /butterknife
            NAME:String
     */

    /**
     * generate HostRouterTable.txt
     */
    private void generateRouterTable() {
        String fileName = RouteUtils.genRouterTable(host);
        if (FileUtils.createFile(fileName)) {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("auto generated, do not change !!!! \n\n");
            stringBuilder.append("HOST : " + host + "\n\n");

            for (Node node : routerNodes) {
                stringBuilder.append(node.getDesc() + "\n");
                stringBuilder.append(node.getPath() + "\n");
                Map<String, String> paramsType = node.getParamsDesc();
                if (MapUtils.isNotEmpty(paramsType)) {
                    for (Map.Entry<String, String> types : paramsType.entrySet()) {
                        stringBuilder.append(types.getKey() + ":" + types.getValue() + "\n");
                    }
                }
                stringBuilder.append("\n");
            }
            FileUtils.writeStringToFile(fileName, stringBuilder.toString(), false);
        }
    }


    /**
     * generate HostUIRouter.java
     * eg:AppUiRouter.java
     */
    private void generateRouterImpl() {

        String claName = RouteUtils.genHostUIRouterClass(host);

        //pkg
        String pkg = claName.substring(0, claName.lastIndexOf("."));
        //simpleName
        String cn = claName.substring(claName.lastIndexOf(".") + 1);
        // superClassName
        ClassName superClass = ClassName.get(elements.getTypeElement(BASECOMPROUTER));

        MethodSpec initHostMethod = generateInitHostMethod();
        MethodSpec initMapMethod = generateInitMapMethod();

        try {
            JavaFile.builder(pkg, TypeSpec.classBuilder(cn)
                    .addModifiers(PUBLIC)
                    .superclass(superClass)
                    .addMethod(initHostMethod)
                    .addMethod(initMapMethod)
                    .build()
            ).build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRouteNodes(Set<? extends Element> routeElements) {

        //1.获得 Activity的信息，这些是定义在 Android SDK 当中的。
        TypeMirror type_Activity = elements.getTypeElement(ACTIVITY).asType();

        //2.遍历所有的被 @RouteNode 注解的 Element
        for (Element element : routeElements) {
            //获得该元素的类型信息。
            TypeMirror tm = element.asType();
            //获得该元素的注解。
            RouteNode route = element.getAnnotation(RouteNode.class);
            //Activity 的子类。
            if (types.isSubtype(tm, type_Activity)) {
                logger.info(">>> Found activity route: " + tm.toString() + " <<<");

                Node node = new Node();
                String path = route.path();

                checkPath(path);

                node.setPath(path);
                node.setDesc(route.desc());
                node.setPriority(route.priority());
                node.setNodeType(NodeType.ACTIVITY);
                node.setRawType(element);

                Map<String, Integer> paramsType = new HashMap<>();
                Map<String, String> paramsDesc = new HashMap<>();
                //获得其所有被 @Autowired 注解的成员变量。
                for (Element field : element.getEnclosedElements()) {
                    if (field.getKind().isField() && field.getAnnotation(Autowired.class) != null) {
                        Autowired paramConfig = field.getAnnotation(Autowired.class);
                        //将所有被 @Autowired 注解的相关信息放到 map 当中。
                        paramsType.put(StringUtils.isEmpty(paramConfig.name())
                                ? field.getSimpleName().toString() : paramConfig.name(), typeUtils.typeExchange(field));
                        paramsDesc.put(StringUtils.isEmpty(paramConfig.name())
                                ? field.getSimpleName().toString() : paramConfig.name(), typeUtils.typeDesc(field));
                    }
                }
                node.setParamsType(paramsType);
                node.setParamsDesc(paramsDesc);

                if (!routerNodes.contains(node)) {
                    routerNodes.add(node);
                }
            } else {
                throw new IllegalStateException("only activity can be annotated by RouteNode");
            }
        }
    }

    private void checkPath(String path) {
        if (path == null || path.isEmpty() || !path.startsWith("/"))
            throw new IllegalArgumentException("path cannot be null or empty,and should start with /,this is:" + path);

        if (path.contains("//") || path.contains("&") || path.contains("?"))
            throw new IllegalArgumentException("path should not contain // ,& or ?,this is:" + path);

        if (path.endsWith("/"))
            throw new IllegalArgumentException("path should not endWith /,this is:" + path
                    + ";or append a token:index");
    }

    /**
     * create init host method
     */
    private MethodSpec generateInitHostMethod() {
        TypeName returnType = TypeName.get(type_String);

        MethodSpec.Builder openUriMethodSpecBuilder = MethodSpec.methodBuilder("getHost")
                .returns(returnType)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC);

        openUriMethodSpecBuilder.addStatement("return $S", host);

        return openUriMethodSpecBuilder.build();
    }

    /**
     * create init map method
     */
    private MethodSpec generateInitMapMethod() {
        TypeName returnType = TypeName.VOID;

        MethodSpec.Builder openUriMethodSpecBuilder = MethodSpec.methodBuilder("initMap")
                .returns(returnType)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC);

        openUriMethodSpecBuilder.addStatement("super.initMap()");

        for (Node node : routerNodes) {
            openUriMethodSpecBuilder.addStatement(
                    mRouteMapperFieldName + ".put($S,$T.class)",
                    node.getPath(),
                    ClassName.get((TypeElement) node.getRawType()));

            // Make map body for paramsType
            StringBuilder mapBodyBuilder = new StringBuilder();
            Map<String, Integer> paramsType = node.getParamsType();
            if (MapUtils.isNotEmpty(paramsType)) {
                for (Map.Entry<String, Integer> types : paramsType.entrySet()) {
                    mapBodyBuilder.append("put(\"").append(types.getKey()).append("\", ").append(types.getValue()).append("); ");
                }
            }
            String mapBody = mapBodyBuilder.toString();
            logger.info(">>> mapBody: " + mapBody + " <<<");
            if (!StringUtils.isEmpty(mapBody)) {
                openUriMethodSpecBuilder.addStatement(
                        mParamsMapperFieldName + ".put($T.class,"
                                + "new java.util.HashMap<String, Integer>(){{" + mapBody + "}}" + ")",
                        ClassName.get((TypeElement) node.getRawType()));
            }
        }

        return openUriMethodSpecBuilder.build();
    }

    /*

        public class AppUiRouter extends BaseCompRouter {
          @Override
          public String getHost() {
            return "app";
          }

          @Override
          public void initMap() {
            super.initMap();
            routeMapper.put("/butterknife",TestButterKnifeActivity.class);
            paramsMapper.put(TestButterKnifeActivity.class,new java.util.HashMap<String, Integer>(){{put("NAME", 8); }});
          }
        }

     */
}
