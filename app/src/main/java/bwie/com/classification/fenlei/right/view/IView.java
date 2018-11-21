package bwie.com.classification.fenlei.right.view;

import java.util.List;

import bwie.com.classification.bean.RightBean;

/**
 * Created by 小哥 on 2018/11/20.
 */

public interface IView {

    void getrights(List<RightBean.DataBean> list);

    void failed(Exception e);
}

