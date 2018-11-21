package bwie.com.classification.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import bwie.com.classification.R;
import bwie.com.classification.bean.Product;
import bwie.com.classification.bean.Shopper;

/**
 * Created by 小哥 on 2018/11/21.
 */

public class ShopperAdapter extends RecyclerView.Adapter<ShopperAdapter.ViewHolder>{
   private Context context;
   private List<Shopper<List<Product>>> list;

    public ShopperAdapter(Context context, List<Shopper<List<Product>>> list) {
        this.context = context;
        this.list = list;
    }
    // 一级列表（商家）发生变化的接口
    public interface OnShopperClickListener{
        void onShopperClick(int position,boolean isCheck);
    }
    private OnShopperClickListener shopperClickListener;

    public void setOnShopperClickListener(OnShopperClickListener listener) {
        this.shopperClickListener = listener;
    }
    // 二级列表的加减器监听
    private ProductAdaper.OnAddDecreaseProductListener productListener;

    public void setOnAddDecreaseProductListener(ProductAdaper.OnAddDecreaseProductListener listener) {
        this.productListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_shopper, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Shopper<List<Product>> shopper = list.get(position);
        holder.txtShopperName.setText(shopper.getSellerName());

        //产品的列表
        RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(context);
        holder.rvProduct.setLayoutManager(pLayoutManager);

        final ProductAdaper adaper = new ProductAdaper(context,shopper.getList());
        //二级列表加价器的监听
        if (productListener != null){
            adaper.setOnAddDecreaseProductListener(productListener);
        }
        //二级条目（商品）复选点击事件
        adaper.setOnProductClickListener(new ProductAdaper.OnProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                // 当前商品未选中，商家也就未选中
                if (!isChecked){
                    shopper.setChecked(false);
                    // 只要是当前条目未选中，全选复选框也就没选中
                    shopperClickListener.onShopperClick(position,false);
                }else{
                    // 当前商品如果选中，需要遍历商家所有的商品是否选中
                    // 循环遍历之前先设置一个true标志位，只要有一条商品没有被选中，商家也就选中，标志位变成false
                    boolean isAllProductSelected = true;
                    for (Product product : shopper.getList()){
                        if (!product.isChecked()){
                            isAllProductSelected = false;
                            break;
                        }
                    }
                    shopper.setChecked(isAllProductSelected);
                    //当前商品选中时，需要循环遍历的商家是否被选中
                    shopperClickListener.onShopperClick(position,true);
                }
                // 数据发生变化之后刷新适配器
                notifyDataSetChanged();
                productListener.onChange(0,0);
            }
        });

        holder.rvProduct.setAdapter(adaper);
        //先取消掉之前的点击变化监听
//        holder.cbSHopper.setOnCheckedChangeListener(null);
        //设置好初始化的状态
        holder.cbSHopper.setChecked(shopper.isChecked());
        //等设置完初始化状态之后我们自己的监听
        //商品列表的复选框
        holder.cbSHopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopper.setChecked(holder.cbSHopper.isChecked());
                //1.商品被选中的时候，子类所有的商品被选中
                List<Product> productList = shopper.getList();
                for (Product product : productList){
                    product.setChecked(holder.cbSHopper.isChecked());
                }
                //子类商品的适配器
                adaper.notifyDataSetChanged();
                // 当点击一级条目的时候，外部的全选按钮状态发生变化
                if (shopperClickListener != null){
                    shopperClickListener.onShopperClick(position,holder.cbSHopper.isChecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cbSHopper;
        private TextView txtShopperName;
        private RecyclerView rvProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            cbSHopper = itemView.findViewById(R.id.cb_shopper);
            txtShopperName = itemView.findViewById(R.id.txt_shopper_name);
            rvProduct = itemView.findViewById(R.id.rv_product);
        }
    }
}
