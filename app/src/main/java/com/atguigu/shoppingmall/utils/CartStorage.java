package com.atguigu.shoppingmall.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 熊猛 on 2017/2/27.
 */

public class CartStorage {
    public static final String JSON_CART = "jsonCart";
    public static CartStorage instance;
    private final Context mContext;
    //SparseArray替代HashMap
    private SparseArray<GoodsBean> sparseArrays;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArrays = new SparseArray<>();
        //从本地获取数据
        listToSparseArray();
    }

    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        for (int i = 0;i<goodsBeanList.size();i++){
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArrays.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }
    //得到所有的数据
    private List<GoodsBean> getAllData() {
        return getLocalData();
    }
    //得到本地缓存的数据
    private List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeens = new ArrayList<>();
        //从本地获取数据
        String jsonCart = CacheUtils.getString(mContext, JSON_CART);//保持的json数据
        if(!TextUtils.isEmpty(jsonCart)) {
            //把它转化成列表
            goodsBeens = new Gson().fromJson(jsonCart,new TypeToken<List<GoodsBean>>(){}.getType());
        }
        //把json数据解析成List数据
        return goodsBeens;
    }


    /**
     * 懒汉式
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context){
        if(instance == null) {
            synchronized (CartStorage.class){
                if(instance == null) {
                    instance = new CartStorage(context);
                }
            }
        }
        return instance;
    }
}
