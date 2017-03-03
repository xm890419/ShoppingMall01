package com.atguigu.shoppingmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.type.bean.TypeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/3.
 */

public class TypeRightAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<TypeBean.ResultBean.ChildBean> child;
    private final List<TypeBean.ResultBean.HotProductListBean> hotList;
    private final LayoutInflater inflater;
    /**
     * 热卖推荐
     */
    private static final int HOT = 0;
    /**
     * 常用分类
     */
    private static final int COMMON = 1;

    private int currentType = HOT;

    public TypeRightAdapter(Context mContext, List<TypeBean.ResultBean> result) {
        this.mContext = mContext;
        child = result.get(0).getChild();
        hotList = result.get(0).getHot_product_list();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HOT) {
            currentType = HOT;
        } else {
            currentType = COMMON;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOT) {
            return new HotViewHolder(mContext, inflater.inflate(R.layout.item_hot_right, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(hotList);
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_hot_right)
        LinearLayout llHotRight;
        @BindView(R.id.hsl_hot_right)
        HorizontalScrollView hslHotRight;
        public HotViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }

        public void setData(final List<TypeBean.ResultBean.HotProductListBean> hotList) {
            for (int i =0;i<hotList.size();i++){
                final TypeBean.ResultBean.HotProductListBean hotProductListBean = hotList.get(i);
                //外面的线性布局
                LinearLayout layout = new LinearLayout(mContext);
                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2,-2);
                params.setMargins(DensityUtil.dip2px(mContext,5),0,DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,20));

                layout.setGravity(Gravity.CENTER);//设置布局居中
                layout.setOrientation(LinearLayout.VERTICAL);//设置竖直方向
                //创建图片
                ImageView imageView = new ImageView(mContext);
                //设置图片宽和高80dip
                LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(DensityUtil.dip2px(mContext,80),DensityUtil.dip2px(mContext,80));
                //设置间距
                ivParams.setMargins(0,0,0,DensityUtil.dip2px(mContext,10));
                //请求图片
                Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotProductListBean.getFigure()).into(imageView);
                layout.addView(imageView,ivParams);

                //--创建文本
                TextView textView = new TextView(mContext);
                LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#ed3f3f"));
                textView.setText("￥"+hotProductListBean.getCover_price());
                //把文本添加到线性布局
                layout.addView(textView,tvParams);

                //把每个线性布局添加到外部的线性布局中
                llHotRight.addView(layout,params);
                //设置item的点击事件
                layout.setTag(i);
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position  = (int) v.getTag();
                        //Toast.makeText(mContext, "position=="+hotList.get(position).getCover_price(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
