package com.atguigu.shoppingmall.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.type.adapter.TagAdapter;
import com.atguigu.shoppingmall.type.bean.TagBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/3.
 */

public class TagFragment extends BaseFragment {
    @BindView(R.id.gv_tag)
    GridView gvTag;

    //private TextView textView;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tag, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.TAG_URL).build().execute(new StringCallback() {
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

    private void processData(final String response) {
        TagBean tagBean = JSON.parseObject(response,TagBean.class);
        final List<TagBean.ResultBean> result = tagBean.getResult();
        //Toast.makeText(mContext, "result=="+result.get(0), Toast.LENGTH_SHORT).show();
        if(result != null && result.size()>0) {
            TagAdapter adapter = new TagAdapter(mContext,result);
            gvTag.setAdapter(adapter);

            gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TagBean.ResultBean resultBean = result.get(position);
                    Toast.makeText(mContext, "resultBean=="+resultBean.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
