package com.atguigu.shoppingmall.user.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.hankkin.gradationscroll.GradationScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/2/22.
 */

public class UserFragment extends BaseFragment implements GradationScrollView.ScrollViewListener {
    @BindView(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tv_user_pay)
    TextView tvUserPay;
    @BindView(R.id.tv_user_receive)
    TextView tvUserReceive;
    @BindView(R.id.tv_user_finish)
    TextView tvUserFinish;
    @BindView(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @BindView(R.id.tv_user_location)
    TextView tvUserLocation;
    @BindView(R.id.tv_user_collect)
    TextView tvUserCollect;
    @BindView(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @BindView(R.id.tv_user_score)
    TextView tvUserScore;
    @BindView(R.id.tv_user_prize)
    TextView tvUserPrize;
    @BindView(R.id.tv_user_ticket)
    TextView tvUserTicket;
    @BindView(R.id.tv_user_invitation)
    TextView tvUserInvitation;
    @BindView(R.id.tv_user_callcenter)
    TextView tvUserCallcenter;
    @BindView(R.id.tv_user_feedback)
    TextView tvUserFeedback;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.scrollview)
    GradationScrollView scrollview;
    @BindView(R.id.tv_usercenter)
    TextView tvUsercenter;
    @BindView(R.id.ib_user_setting)
    ImageButton ibUserSetting;
    @BindView(R.id.ib_user_message)
    ImageButton ibUserMessage;
    public int height;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        ViewTreeObserver vto = rlHeader.getViewTreeObserver();
        //添加监听
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除视图树的监听
                rlHeader.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = rlHeader.getHeight();

                scrollview.setScrollViewListener(UserFragment.this);
            }
        });
        //设置默认是隐藏的 //设置标题的背景颜色  -透明
        tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255, 0, 0)) ;
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            //设置标题的背景颜色  -透明
            tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255, 0, 0));

        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            //滑动距离 ： 总距离 = 改变的透明度 ： 总透明度
            //改变的透明度 = 总透明度*(滑动距离 ：总距离)

            tvUsercenter.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            tvUsercenter.setBackgroundColor(Color.argb((int) alpha, 255, 0, 0));
        } else {
            //滑动到banner下面设置普通颜色 - 非透明
            tvUsercenter.setBackgroundColor(Color.argb((int) 255, 255, 0, 0));
        }
    }

}
