package com.atguigu.shoppingmall.community.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.adapter.NewPostAdapter;
import com.atguigu.shoppingmall.community.bean.NewPostBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/4.
 */

public class NewPostFragment extends BaseFragment {

    @BindView(R.id.lv_new_post)
    ListView lvNewPost;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_news_post, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.NEW_POST_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败=="+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功==");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        NewPostBean bean = JSON.parseObject(response,NewPostBean.class);
        //Toast.makeText(mContext, ""+bean.getResult().get(0).getSaying(), Toast.LENGTH_SHORT).show();
        List<NewPostBean.ResultBean> result = bean.getResult();
        if(result != null && result.size() >0) {
            NewPostAdapter adapter = new NewPostAdapter(mContext,result);
            lvNewPost.setAdapter(adapter);
        }
    }

}
