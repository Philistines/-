package bwie.com.classification.fenlei.right.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import bwie.com.classification.bean.RightBean;
import bwie.com.classification.net.ICallBack;
import bwie.com.classification.fenlei.right.model.RightModel;
import bwie.com.classification.fenlei.right.view.IView;

/**
 * Created by 小哥 on 2018/11/20.
 */

public class RightPresenter {
    private IView iv;
    private RightModel rightModel;
    public void attch(IView iv){
        this.iv = iv;
        rightModel = new RightModel();
    }
    public void getright(String url){
        Type type = new TypeToken<RightBean>(){}.getType();
        rightModel.getrights(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                RightBean rightBean = (RightBean) obj;
                if (rightBean!=null){
                    iv.getrights(rightBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void detach(){
        if (iv !=null){
            iv = null;
        }
    }
}

