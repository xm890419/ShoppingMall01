package com.atguigu.shoppingmall.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.app.GoodsInfoActivity;
import com.atguigu.shoppingmall.home.adapter.GoodsListAdapter;
import com.atguigu.shoppingmall.home.adapter.HomeAdapter;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.home.bean.GoodsListBean;
import com.atguigu.shoppingmall.home.view.SpaceItemDecoration;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class GoodsListActivity extends AppCompatActivity {

    @BindView(R.id.ib_goods_list_back)
    ImageButton ibGoodsListBack;
    @BindView(R.id.tv_goods_list_search)
    TextView tvGoodsListSearch;
    @BindView(R.id.ib_goods_list_home)
    ImageButton ibGoodsListHome;
    @BindView(R.id.tv_goods_list_sort)
    TextView tvGoodsListSort;
    @BindView(R.id.tv_goods_list_price)
    TextView tvGoodsListPrice;
    @BindView(R.id.iv_goods_list_arrow)
    ImageView ivGoodsListArrow;
    @BindView(R.id.ll_goods_list_price)
    LinearLayout llGoodsListPrice;
    @BindView(R.id.tv_goods_list_select)
    TextView tvGoodsListSelect;
    @BindView(R.id.ll_goods_list_head)
    LinearLayout llGoodsListHead;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.dl_left)
    DrawerLayout dlLeft;
    private int position;
    /**
     * 请求网络
     */
    private String[] urls = new String[]{
            Constants.CLOSE_STORE,
            Constants.GAME_STORE,
            Constants.COMIC_STORE,
            Constants.COSPLAY_STORE,
            Constants.GUFENG_STORE,
            Constants.STICK_STORE,
            Constants.WENJU_STORE,
            Constants.FOOD_STORE,
            Constants.SHOUSHI_STORE,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        position = getIntent().getIntExtra("position", 0);
        //Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        getDataFromNet(urls[position]);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网请求数据失败"+e.getMessage());
            }

            @Override
            public void onResponse(String rsponse, int id) {
                Log.e("TAG", "联网请求数据成功==");
                processData(rsponse);
            }
        });
    }

    private void processData(String rsponse) {
        GoodsListBean bean = JSON.parseObject(rsponse,GoodsListBean.class);
        Log.e("TAG",bean.getResult().getPage_data().get(0).getName());
        List<GoodsListBean.ResultBean.PageDataBean> datas = bean.getResult().getPage_data();
        if(datas != null && datas.size()>0) {
            //有数据-设置适配器
            GoodsListAdapter adapter = new GoodsListAdapter(this,datas);
            recyclerview.setAdapter(adapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new GridLayoutManager(this,2));

            recyclerview.addItemDecoration(new SpaceItemDecoration(10));

            adapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(GoodsListBean.ResultBean.PageDataBean data) {
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setFigure(data.getFigure());
                    goodsBean.setProduct_id(data.getProduct_id());
                    goodsBean.setName(data.getName());
                    goodsBean.setCover_price(data.getCover_price());

                    Intent intent  = new Intent(GoodsListActivity.this, GoodsInfoActivity.class);
                    intent.putExtra(HomeAdapter.GOODS_BEAN,goodsBean);
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick({R.id.ib_goods_list_back, R.id.tv_goods_list_search, R.id.ib_goods_list_home, R.id.tv_goods_list_sort, R.id.tv_goods_list_price, R.id.tv_goods_list_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goods_list_back:
                finish();
                break;
            case R.id.tv_goods_list_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_goods_list_home:
                Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_sort:
                Toast.makeText(this, "综合排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_price:
                Toast.makeText(this, "价格", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_list_select:
                Toast.makeText(this, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
