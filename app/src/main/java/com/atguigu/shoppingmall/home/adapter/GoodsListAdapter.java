package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsListBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/6.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<GoodsListBean.ResultBean.PageDataBean> datas;
    private final LayoutInflater inflater;


    public GoodsListAdapter(Context context, List<GoodsListBean.ResultBean.PageDataBean> datas) {
        this.mContext = context;

        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View itemView = inflater.inflate(R.layout.item_goods_list, null);
        return new MyViewHolder(inflater.inflate(R.layout.item_goods_list, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.根据位置得到对应的数据
        GoodsListBean.ResultBean.PageDataBean pageDataBean = datas.get(position);
        //2.绑定数据
        holder.tvName.setText(pageDataBean.getName());
        holder.tvPrice.setText("￥"+pageDataBean.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+pageDataBean.getFigure()).placeholder(R.drawable.new_img_loading_2).into(holder.ivHot);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot)
        ImageView ivHot;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(datas.get(getLayoutPosition()));
                    }
                }
            });
        }

    }
    //点击item的接口
    public interface OnItemClickListener{
        public void onItemClick(GoodsListBean.ResultBean.PageDataBean data);
    }

    private OnItemClickListener listener;

    //设置item的点击事件
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
