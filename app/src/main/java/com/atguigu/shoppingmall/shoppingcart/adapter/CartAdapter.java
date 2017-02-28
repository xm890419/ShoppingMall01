package com.atguigu.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/2/28.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<GoodsBean> allData;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;


    public CartAdapter(Context mContext, List<GoodsBean> allData, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.allData = allData;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;

        showTotalPrice();
    }

    private void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计"+getTotalPrice());
    }
    //返回总价格
    private double getTotalPrice() {
        double totalPrice = 0;
        if(allData != null && allData.size() >0) {
            for (int i =0;i < allData.size();i++){
                GoodsBean goodsBean = allData.get(i);
                if(goodsBean.isChecked()) {
                    totalPrice = totalPrice + Double.parseDouble(goodsBean.getCover_price())*goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext,R.layout.item_shop_cart,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.先得到数据
        GoodsBean goodsBean = allData.get(position);
        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥"+goodsBean.getCover_price());
        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        //设置库存-来自服务器-
        holder.addSubView.setMaxValue(100);

    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.addSubView)
        AddSubView addSubView;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
