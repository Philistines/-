package bwie.com.classification.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.classification.R;
import bwie.com.classification.bean.RightBean;

/**
 * Created by 小哥 on 2018/11/20.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<RightBean.DataBean.ListBean> list;

    public RightAdapter(Context context, List<RightBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.right_item, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imageright);
        holder.textright.setText(list.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageright;
        private TextView textright;
        public ViewHolder(View itemView) {
            super(itemView);
            imageright = itemView.findViewById(R.id.img_right);
            textright = itemView.findViewById(R.id.txt_right);
        }
    }
}

