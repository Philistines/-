package bwie.com.classification.cart.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import bwie.com.classification.bean.MessageBean;
import bwie.com.classification.bean.Product;
import bwie.com.classification.bean.Shopper;
import bwie.com.classification.cart.model.CartModel;
import bwie.com.classification.cart.view.CartView;
import bwie.com.classification.constant.ServerUrl;
import bwie.com.classification.net.ICallBack;

/**
 * Created by 小哥 on 2018/11/21.
 */

public class CartPresenter {
    private CartView cv;
    private CartModel model;

    public void attach(CartView cv){
        this.cv = cv;
        model = new CartModel();
    }
    public void getData(String userid){
        String url = ServerUrl.QUERY_CART_URL+userid;
        Type type = new TypeToken<MessageBean<List<Shopper<List<Product>>>>>() {
        }.getType();
        model.getData(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                MessageBean<List<Shopper<List<Product>>>> data = (MessageBean<List<Shopper<List<Product>>>>) obj;
                cv.success(data);
            }

            @Override
            public void onFailed(Exception e) {
                cv.failed(e);
            }
        },type);
    }
    public void detach() {
        if (cv != null) {
            cv = null;
        }
    }

}
