package com.atguigu.shoppingmall.community.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.adapter.CommunityViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/2/22.
 */

public class CommunityFragment extends BaseFragment {
    @BindView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @BindView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> fragments;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_community, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initFragment();
        CommunityViewPagerAdapter adapter = new CommunityViewPagerAdapter(getFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new NewPostFragment());
        fragments.add(new HotPostFragment());
    }


    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                break;
            case R.id.ib_community_message:
                break;
        }
    }
}
