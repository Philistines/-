package bwie.com.classification.fenlei.left.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import bwie.com.classification.bean.LeftBean;
import bwie.com.classification.fenlei.left.model.LeftModel;
import bwie.com.classification.fenlei.left.view.IView;
import bwie.com.classification.net.ICallBack;

/**
 * Created by 小哥 on 2018/11/20.
 */

public class LeftPresenter {
    private IView iv;
    private LeftModel leftModel;
    public void attch(IView iv){
        this.iv = iv;
        leftModel = new LeftModel();
    }

    public void getLeft() {
        Type type = new TypeToken<LeftBean>() {
        }.getType();
        leftModel.getlefts("http://www.zhaoapi.cn/product/getCatagory", new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                LeftBean leftBean = (LeftBean) obj;
                if (leftBean != null){
                    iv.getLeft(leftBean.getData());
                }
            }
            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void datach(){
        if (iv != null){
            iv = null;
        }
    }
}
