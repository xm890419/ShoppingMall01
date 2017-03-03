package com.atguigu.shoppingmall.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atguigu.shoppingmall.MainActivity;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/2/22.
 */

public class TypeFragment extends BaseFragment {
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @BindView(R.id.fl_type)
    FrameLayout flType;
    private String[] titles = {"分类","标签"};
    private List<BaseFragment> fragments;
    private Fragment tempFragment;

    //private TextView textView;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initListener();
        //Log.e("TAG", "分类数据初始化了");
        //textView.setText("分类");
        initFragment();
        switchFragment(fragments.get(0));
    }

    private void switchFragment(Fragment currentFragment) {
        MainActivity activity = (MainActivity) mContext;
        if(tempFragment != currentFragment) {

                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                if(!currentFragment.isAdded()) {
                    if(tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.add(R.id.fl_type,currentFragment);
                }else {
                    if(tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    ft.show(currentFragment);
                }
                ft.commit();

            tempFragment = currentFragment;
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new TagFragment());
    }

    private void initListener() {
        tl1.setTabData(titles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                switchFragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick(R.id.iv_type_search)
    public void onClick() {
    }
}
