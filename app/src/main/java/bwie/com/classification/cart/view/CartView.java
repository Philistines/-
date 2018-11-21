package bwie.com.classification.cart.view;

import java.util.List;

import bwie.com.classification.base.BaseView;
import bwie.com.classification.bean.MessageBean;
import bwie.com.classification.bean.Product;
import bwie.com.classification.bean.Shopper;

/**
 * Created by 小哥 on 2018/11/21.
 */

public interface CartView extends BaseView<MessageBean<List<Shopper<List<Product>>>>>{

}
