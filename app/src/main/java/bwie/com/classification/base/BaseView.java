package bwie.com.classification.base;

/**
 * Created by 小哥 on 2018/11/21.
 */

public interface BaseView<T>{
    void success(T t);

    void failed(Exception e);
}
