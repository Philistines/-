package bwie.com.classification.constant;

/**
 * Created by 小哥 on 2018/11/21.
 */

public class ServerUrl {
    /**
     * 服务器根地址
     */
    private static final String SERVER_ROOT = "http://www.zhaoapi.cn/";

    /**
     * 首页轮播图地址
     */
    public static final String BANNER_URL = SERVER_ROOT + "ad/getAd";
    /**
     * 一级分类
     */
    public static final String LEFT_CATEGORY_URL = SERVER_ROOT + "product/getCatagory";
    /**
     * 二级分类
     */
    public static final String RIGHT_CATEGORY_URL = SERVER_ROOT + "product/getProductCatagory?cid=";
    /**
     * 购物车
     */
    public static final String QUERY_CART_URL = SERVER_ROOT + "product/getCarts?uid=";

}
