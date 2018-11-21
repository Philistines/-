package bwie.com.classification.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eric on 2018/10/25.
 */

public abstract class BaseFragment extends Fragment {
    protected View view;
    protected FragmentActivity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getContentView(), container, false);
        initView(view);
        return view;
    }

    public abstract int getContentView();

    public abstract void initView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        initData();
        setListener();
        setMoreAction();
    }

    public void initData() {

    }

    public void setListener() {

    }

    public void setMoreAction() {

    }
}
