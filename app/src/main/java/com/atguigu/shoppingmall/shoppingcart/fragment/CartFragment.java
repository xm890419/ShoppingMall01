package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.utils.CartStorage;

/**
 * Created by 熊猛 on 2017/2/22.
 */

public class CartFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "购物车数据初始化了");
        textView.setText("购物车");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setProduct_id("10659");
                goodsBean.setNumber(3);
                CartStorage.getInstance(mContext).updateData(goodsBean);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        for (int i=0;i<CartStorage.getInstance(mContext).getAllData().size();i++){
            Log.e("TAG",""+CartStorage.getInstance(mContext).getAllData().get(i).toString());
        }
    }
}
