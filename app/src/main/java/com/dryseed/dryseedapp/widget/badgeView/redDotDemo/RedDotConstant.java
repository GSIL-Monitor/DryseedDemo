package com.dryseed.dryseedapp.widget.badgeView.redDotDemo;

/**
 * Created by caiminming on 2017/10/31.
 */

public class RedDotConstant {
    /*
        在布局文件中注明的红点的tag
        如果存在公共红点，则可以另外添加一个tag名,例如：RedDotTagCommon，在BaseActivity中查找可以提高效率。
     */
    public static final String RED_DOT_TAG = "RedDotTag";
    public static final String RED_DOT_TAG_COMMON = "RedDotTagCommon";

    /*
        Link类型，各个业务逻辑自行添加
     */
    public static final String LINK_TYPE_CONSERVATION = "LINK_TYPE_CONSERVATION";
    public static final String LINK_TYPE_RELATION = "LINK_TYPE_RELATION";

    /*
        红点类型，各个业务逻辑可以自行定义，通过RedDotEvent透传
     */
    public static final int RED_DOT_TYPE_SHOW_DOT = -1;
    public static final int RED_DOT_TYPE_HIDE = 0;
}
