package com.atguigu.shoppingmall.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.community.bean.HotPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.atguigu.shoppingmall.utils.DensityUtil;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/4.
 */

public class HotPostAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HotPostBean.ResultBean> result;

    public HotPostAdapter(Context mContext, List<HotPostBean.ResultBean> result) {
        this.mContext = mContext;
        this.result = result;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hotpost_listview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HotPostBean.ResultBean resultBean = result.get(position);

        //图像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+resultBean.getAvatar()).into(viewHolder.ivNewPostAvatar);
        viewHolder.tvHotUsername.setText(resultBean.getUsername());
        //图像
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+resultBean.getFigure()).into(viewHolder.ivHotFigure);
        viewHolder.tvHotSaying.setText(resultBean.getSaying());
        viewHolder.tvHotLikes.setText(resultBean.getLikes());
        viewHolder.tvHotComments.setText(resultBean.getComments());


        //设置置顶
        String is_top = resultBean.getIs_top();
        if("1".equals(is_top)) {
            //参数
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            textViewLp.setMargins(DensityUtil.dip2px(mContext,5),0,0,0);

            //显示置顶
            TextView topTextView = new TextView(mContext);
            topTextView.setText("置顶");
            topTextView.setGravity(Gravity.CENTER);
            //白色
            topTextView.setTextColor(Color.WHITE);
            //设置背景
            topTextView.setBackgroundResource(R.drawable.is_top_shape);
            //padding都是5
            topTextView.setPadding(DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5));
            //先移除
            viewHolder.llHotPost.removeAllViews();
            //参数

            viewHolder.llHotPost.addView(topTextView,textViewLp);
        }
        //热门
        String is_hot = resultBean.getIs_hot();
        if("1".equals(is_hot)) {
            //参数
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView hotTextView = new TextView(mContext);
            textViewLp.setMargins(DensityUtil.dip2px(mContext,5),0,DensityUtil.dip2px(mContext,5),0);
            hotTextView.setText("热门");
            hotTextView.setGravity(Gravity.CENTER);
            //白色
            hotTextView.setTextColor(Color.WHITE);
            //设置背景
            hotTextView.setBackgroundResource(R.drawable.is_hot_shape);
            //padding都是5
            hotTextView.setPadding(DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5));
            viewHolder.llHotPost.addView(hotTextView,textViewLp);
        }
        //精华
        String is_essence = resultBean.getIs_essence();
        if("1".equals(is_essence)) {
            //参数
            LinearLayout.LayoutParams textViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView textView = new TextView(mContext);
            textViewLp.setMargins(0,0,DensityUtil.dip2px(mContext,5),0);
            textView.setText("精华");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5),DensityUtil.dip2px(mContext,5));
            textView.setBackgroundResource(R.drawable.is_essence_shape);
            viewHolder.llHotPost.addView(textView,textViewLp);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_hot_username)
        TextView tvHotUsername;
        @BindView(R.id.tv_hot_addtime)
        TextView tvHotAddtime;
        @BindView(R.id.rl)
        RelativeLayout rl;
        @BindView(R.id.iv_new_post_avatar)
        ImageView ivNewPostAvatar;
        @BindView(R.id.iv_hot_figure)
        ImageView ivHotFigure;
        @BindView(R.id.ll_hot_post)
        LinearLayout llHotPost;
        @BindView(R.id.tv_hot_saying)
        TextView tvHotSaying;
        @BindView(R.id.tv_hot_likes)
        TextView tvHotLikes;
        @BindView(R.id.tv_hot_comments)
        TextView tvHotComments;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
