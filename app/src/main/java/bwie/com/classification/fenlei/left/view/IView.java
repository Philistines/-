package bwie.com.classification.fenlei.left.view;

import java.util.List;

import bwie.com.classification.bean.LeftBean;

/**
 * Created by 小哥 on 2018/11/20.
 */

public interface IView {

    void getLeft(List<LeftBean.DataBean> list);

    void failed(Exception e);

}

