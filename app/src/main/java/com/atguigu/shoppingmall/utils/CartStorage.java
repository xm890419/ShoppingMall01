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
        //循环起来，把数据转存到sparseArray
        for (int i = 0;i<goodsBeanList.size();i++){
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArrays.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        }
    }
    //得到所有的数据
    public List<GoodsBean> getAllData() {
        return getLocalData();
    }
    //得到本地缓存的数据
    private List<GoodsBean> getLocalData() {
        List<GoodsBean> goodsBeens = new ArrayList<>();
        //从本地获取数据 从sp中
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
    //添加数据
    public void addData(GoodsBean goodsBean){
        //1.数据添加到sparseArray
        GoodsBean tempGoodsBean = sparseArrays.get(Integer.parseInt(goodsBean.getProduct_id()));
        //已经保存过
        if(tempGoodsBean != null) {
            tempGoodsBean.setNumber(tempGoodsBean.getNumber()+ goodsBean.getNumber());
        }else {
            //没有添加过
            tempGoodsBean = goodsBean;
        }
        //添加到集合中
        sparseArrays.put(Integer.parseInt(tempGoodsBean.getProduct_id()),tempGoodsBean);
        //2.保持到本地
        saveLocal();
    }

    //删除数据
    public void deleteData(GoodsBean goodsBean){
        //1.删除数据
        sparseArrays.delete(Integer.parseInt(goodsBean.getProduct_id()));
        //2.保持到本地
        saveLocal();
    }

    //修改数据
    public void updateData(GoodsBean goodsBean){
        //1.修改数据
        sparseArrays.put(Integer.parseInt(goodsBean.getProduct_id()),goodsBean);
        //2.保持到本地
        saveLocal();
    }

    //保持到本地
    private void saveLocal() {
        //1.把sparseArray转成List
        List<GoodsBean> been = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String savejson = new Gson().toJson(been);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext,JSON_CART,savejson);
    }
    // 把sparseArray转成List
    private List<GoodsBean> sparseArrayToList() {
        //列表数据
        List<GoodsBean> beanLists = new ArrayList<>();
        for (int i =0;i<sparseArrays.size();i++){
            GoodsBean goodsBean = sparseArrays.valueAt(i);
            beanLists.add(goodsBean);
        }
        return beanLists;
    }
}
