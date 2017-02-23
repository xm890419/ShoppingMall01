package com.atguigu.shoppingmall.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.HomeBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/2/22.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tv_search_home)
    TextView tvSearchHome;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.ib_home)
    ImageButton ibHome;

    //private TextView textView;
    @Override
    public View initView() {
        /*textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);*/
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "主页数据初始化了");
        //textView.setText("主页");
        getDataFromNet();
    }

    private void getDataFromNet() {

        OkHttpUtils.get().url(Constants.HOME_URL).id(100).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败=="+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                //Log.e("TAG", "联网成功=="+response);
                processData(response);

            }
        });
    }
    //1.三种解析方式：fastjson解析数据和Gson和手动解析
    //2.设置适配器
    private void processData(String response) {
        //使用fastjson解析json数据
        HomeBean homeBean = JSON.parseObject(response,HomeBean.class);
        Log.e("TAG","解析数据成功=="+homeBean.getResult().getHot_info().get(0).getName());

    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_home:
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
                break ;
        }
    }
}
