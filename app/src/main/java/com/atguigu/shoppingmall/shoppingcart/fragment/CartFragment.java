package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.adapter.CartAdapter;
import com.atguigu.shoppingmall.utils.CartStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/2/22.
 */

public class CartFragment extends BaseFragment {
    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;
    private CartAdapter adapter;
    private List<GoodsBean> allData;

    //private TextView textView;
    @Override
    public View initView() {
        /*textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);*/
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        /*Log.e("TAG", "购物车数据初始化了");
        textView.setText("购物车");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setProduct_id("10659");
                goodsBean.setNumber(3);
                CartStorage.getInstance(mContext).updateData(goodsBean);
            }
        });*/
        allData = CartStorage.getInstance(mContext).getAllData();
        if(allData != null && allData.size()>0) {
            //购物车有数据
            llEmptyShopcart.setVisibility(View.GONE);
            //设置RecyclerView的适配器
            adapter = new CartAdapter(mContext,allData,tvShopcartTotal,checkboxAll,checkboxDeleteAll);
            recyclerview.setAdapter(adapter);
            //布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            //设置点击事件
            adapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //1.设置Bean对象状态取反
                    GoodsBean goodsBean = allData.get(position);
                    goodsBean.setChecked(!goodsBean.isChecked());
                    adapter.notifyItemChanged(position);
                    //2.刷新价格
                    adapter.showTotalPrice();
                    //3.校验是否全选
                    adapter.checkAll();
                }
            });
            //3.校验是否全选
            adapter.checkAll();
        }else {
            //购物车没有数据
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            for (int i = 0; i < CartStorage.getInstance(mContext).getAllData().size(); i++) {
                Log.e("TAG", "" + CartStorage.getInstance(mContext).getAllData().get(i).toString());
            }
        }
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                Toast.makeText(mContext, "结算的全选/反选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_check_out:
                Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_delete_all:
                Toast.makeText(mContext, "删除的全选/反选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(mContext, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
