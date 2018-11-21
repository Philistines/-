package bwie.com.classification.cart.model;

import java.lang.reflect.Type;

import bwie.com.classification.net.HttpUtils;
import bwie.com.classification.net.ICallBack;

/**
 * Created by 小哥 on 2018/11/21.
 */

public class CartModel {
    public void getData(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url, callBack, type);
    }
}
