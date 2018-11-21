package bwie.com.classification.fenlei.right.model;

import java.lang.reflect.Type;

import bwie.com.classification.net.HttpUtils;
import bwie.com.classification.net.ICallBack;

/**
 * Created by 小哥 on 2018/11/20.
 */

public class RightModel {
    public void getrights(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
