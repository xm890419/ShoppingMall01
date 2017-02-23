package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.HomeBean;

import java.util.List;

/**
 * Created by 熊猛 on 2017/2/23.
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    private final HomeBean.ResultBean result;
    private final LayoutInflater inflater;
    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;


    /**
     * 当前类型
     */
    public int currentType = BANNER;

    //根据位置得到对应的类型

    @Override
    public int getItemViewType(int position) {
        if(position == BANNER) {
            currentType = BANNER;
        }else if(position == CHANNEL) {
            currentType = CHANNEL;
        }else if(position == ACT) {
            currentType = ACT;
        }else if(position == SECKILL) {
            currentType = SECKILL;
        }else if(position == RECOMMEND) {
            currentType = RECOMMEND;
        }else if(position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //所有的类型写完后改成6
        return 1;
    }


    public HomeAdapter(Context mContext, HomeBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        }else if(viewType == CHANNEL) {

        }else if(viewType == ACT) {

        }else if(viewType == SECKILL) {

        }else if(viewType == RECOMMEND) {

        }else if(viewType == HOT) {

        }
        return null;
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(result.getBanner_info());
        }else if (getItemViewType(position) == CHANNEL){

        }else if (getItemViewType(position) == ACT){

        }else if (getItemViewType(position) == RECOMMEND){

        }else if (getItemViewType(position) == SECKILL){

        }else if (getItemViewType(position) == HOT){

        }

    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_banner;

        public BannerViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            tv_banner = (TextView) inflate.findViewById(R.id.tv_banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            tv_banner.setText("banner图片");
        }
    }
}
