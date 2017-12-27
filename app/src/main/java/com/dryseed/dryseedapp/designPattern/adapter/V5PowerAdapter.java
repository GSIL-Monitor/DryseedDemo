package com.dryseed.dryseedapp.designPattern.adapter;


/**
 * Created by jingbin on 2016/10/30.
 * 将200v家用电转换为5v手机用电的适配器
 * <p>
 * <p>
 * 当我们要访问的接口A中没有我们想要的方法 ，却在另一个接口B中发现了合适的方法，我们又不能改变访问接口A，
 * 在这种情况下，我们可以定义一个适配器p来进行中转，这个适配器p要实现我们访问的接口A，这样我们就能继续访问当前接口A中的方法（虽然它目前不是我们的菜），
 * 然后在适配器P中定义私有变量C（对象）（B接口指向变量名），再定义一个带参数的构造器用来为对象C赋值，再在A接口的方法实现中使用对象C调用其来源于B接口的方法。
 */

public class V5PowerAdapter implements V5Power {

    private V220Power v220Power;

    public V5PowerAdapter(V220Power v220Power) {
        this.v220Power = v220Power;
    }

    @Override
    public int provideV5Power() {
        System.out.println("适配器: 经过复杂的操作,将" + v220Power.provideV220Power() + "v电压转为5v");
        return 5;
    }

}
