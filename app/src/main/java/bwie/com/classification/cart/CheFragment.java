package bwie.com.classification.cart;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.com.classification.R;
import bwie.com.classification.adapter.ProductAdaper;
import bwie.com.classification.adapter.ShopperAdapter;
import bwie.com.classification.base.BaseFragment;
import bwie.com.classification.bean.MessageBean;
import bwie.com.classification.bean.Product;
import bwie.com.classification.bean.Shopper;
import bwie.com.classification.cart.presenter.CartPresenter;
import bwie.com.classification.cart.view.CartView;

/**
 * Created by 小哥 on 2018/11/20.
 */

public class CheFragment extends BaseFragment implements CartView{
    private CheckBox cbTotal;
    private TextView txtPrice;
    private Button btnCalu;
    private RecyclerView rvShoper;

    private CartPresenter presenter;
    private ShopperAdapter adapter;

    private List<Shopper<List<Product>>> list;

    @Override
    public int getContentView() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initView(View view) {
        cbTotal = view.findViewById(R.id.cb_total_select);
        txtPrice = view.findViewById(R.id.txt_total_price);
        btnCalu = view.findViewById(R.id.btn_calu);
        rvShoper = view.findViewById(R.id.rv_shopper);
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList<>();
        //商家的列表
        adapter = new ShopperAdapter(context,list);
        // 添加一级条目（商家）状态发生变化时
        adapter.setOnShopperClickListener(new ShopperAdapter.OnShopperClickListener() {
            @Override
            public void onShopperClick(int position, boolean isCheck) {
                // 为了效率考虑，当点击状态变成未选中时，全选按钮肯定就不是全选了，就不用再循环一次
                if (!isCheck){
                    cbTotal.setChecked(false);
                }else{
                    // 如果是商家变成选中状态时，需要循环遍历所有的商家是否被选中
                    // 循环遍历之前先设置一个true标志位，只要有一个是未选中就改变这个标志位为false
                    boolean isAllShopperChecked = true;
                    for (Shopper<List<Product>> listShopper : list){
                        // 只要有一个商家没有被选中，全选复选框就变成未选中状态，并且结束循环
                        if (!listShopper.isChecked()){
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbTotal.setChecked(isAllShopperChecked);
                }
                // 一级条目发生变化时，计算一下总价
                calculatePrice();
            }
        });
        adapter.setOnAddDecreaseProductListener(new ProductAdaper.OnAddDecreaseProductListener() {
            @Override
            public void onChange(int position, int num) {
                calculatePrice();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        rvShoper.setLayoutManager(layoutManager);
        rvShoper.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        rvShoper.setAdapter(adapter);

        presenter = new CartPresenter();
        presenter.attach(this);
        presenter.getData("1538");
    }

    @Override
    public void setListener() {
        super.setListener();
        cbTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = cbTotal.isChecked();
                //遍历一级列表,和下方的全选状态一致
                for (Shopper<List<Product>> listShopper : list){
                    listShopper.setChecked(isChecked);
                    //遍历二级列表，和下放的全选状态一致
                    List<Product> products = listShopper.getList();
                    for (Product product : products){
                        product.setChecked(isChecked);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void success(MessageBean<List<Shopper<List<Product>>>> listMessageBean) {
        if (listMessageBean != null){
            List<Shopper<List<Product>>> shoppers = listMessageBean.getData();
            if (shoppers != null){
                list.clear();
                list.addAll(shoppers);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
    }
    //计算商品总价
    private void calculatePrice() {
        // 遍历商家
        float totalPrice = 0;
        for (Shopper<List<Product>> listShopper : list){
            // 遍历商家的商品
            List<Product> list = listShopper.getList();
            for (Product product : list){
                // 如果商品被选中
                if (product.isChecked()){
                    totalPrice += product.getNum() * product.getPrice();
                }
            }
        }
        txtPrice.setText("总价：" + totalPrice);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null){
            presenter.detach();
        }
    }
}
