/*
package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.zhuangxiu.OkHttpUtils;
import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.URLS;
import com.zx.zhuangxiu.adapter.ShopListViewAdapter1;
import com.zx.zhuangxiu.model.AddressOne;
import com.zx.zhuangxiu.model.ChakangouwucheBean;
import com.zx.zhuangxiu.model.ChangeShopNum;
import com.zx.zhuangxiu.model.GouWuCheBean;
import com.zx.zhuangxiu.model.GwcDingDanOne;
import com.zx.zhuangxiu.model.GwcDingDanThree;
import com.zx.zhuangxiu.utils.CustomProgressDialog;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class shop extends AppCompatActivity implements View.OnClickListener, ShopListViewAdapter1.CheckInterface, ShopListViewAdapter1.ModifyCountInterface {

    private TextView shopcar_back;
    //全选
    private CheckBox ckAll;
    //总额,结算,编辑
    private TextView tvShowPrice;
    private ListView shopcar_listview;
    private ShopListViewAdapter1 shopListViewAdapter1;

    private float totalPrice = 0;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private Button tvSettlement;

    private GouWuCheBean gouWuCheBean;
    private List<GouWuCheBean> gouWuCheBeanList = new ArrayList<>();
    private String changeMsg = "";
    private String msg = "";


    private String dingDanHao = "";
    private String address;
    private String addressname;
    private int pikid;

    private AlertDialog changeNumDialog;

    private List<GwcDingDanThree> gwcDingDanThreeList = new ArrayList<>();

    private CustomProgressDialog progressDialog = null;//声明自定义弹出框


    private List<ChakangouwucheBean.DataBean> mlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        initView();
    }

    private void initView() {

        progressDialog = CustomProgressDialog.createDialog(shop.this);

        shopcar_back = (TextView) findViewById(R.id.shopcar_back);
        shopcar_back.setOnClickListener(this);

        ckAll = (CheckBox) findViewById(R.id.all_chose);
        tvShowPrice = (TextView) findViewById(R.id.sc_price);
        tvSettlement = (Button) findViewById(R.id.jiesuan_button);
        ckAll.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);

        shopcar_listview = (ListView) findViewById(R.id.shopcar_listview);


        shopListViewAdapter1 = new ShopListViewAdapter1(shop.this);
        shopListViewAdapter1.setCheckInterface(this);
        shopListViewAdapter1.setModifyCountInterface(this);

        getGouWuCheList();

        getmorenaddress();
    }

    */
/**
     * 购物车列表
     *//*

    private void getGouWuCheList() {
        String userid = String.valueOf(URLS.getUser_id());
        String url = URLS.chaXunShopShow();
        FormBody formBody=new FormBody.Builder().add("userId",userid).build();

        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<ChakangouwucheBean>() {
            @Override
            public void onSuccess(ChakangouwucheBean response) {
                if (response.getResult() == 1){
                    mlist.addAll(response.getData());
                    shopcar_listview.setAdapter(shopListViewAdapter1);
                    shopListViewAdapter1.setShoppingCartBeanList(mlist);
                }else {
                    Toast.makeText(shop.this, "很抱歉，购物车还没有数据!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(),"请求失败");
            }
        });
    }


    private void getmorenaddress() {
        String url = URLS.myAddressShow(URLS.getUser_id());
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<AddressOne>() {
            @Override
            public void onSuccess(AddressOne response) {
                if (response.getResult() == 1) {
                    List<AddressOne.DataBean> data = response.getData();
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getStatus()==1){
                            addressname=data.get(i).getName()+data.get(i).getTele();
                            pikid = data.get(i).getId();
                            address = data.get(i).getProvince()+data.get(i).getCity()+data.get(i).getCounty();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopcar_back:
                this.finish();
                break;
            //全选按钮
            case R.id.all_chose:
                if (gouWuCheBeanList.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < gouWuCheBeanList.size(); i++) {
                            gouWuCheBeanList.get(i).setChoosed(true);
                        }
                        shopListViewAdapter1.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < gouWuCheBeanList.size(); i++) {
                            gouWuCheBeanList.get(i).setChoosed(false);
                        }
                        shopListViewAdapter1.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.jiesuan_button: //结算
                lementOnder();
                break;
        }
    }


    */
/**
     * 结算订单、支付
     *//*

    private void lementOnder() {
        String shopId = "";

        //选中的需要提交的商品清单
        for (GouWuCheBean bean : gouWuCheBeanList) {
            boolean choosed = bean.isChoosed();
            if (choosed) {
                String shoppingName = bean.getRecordName();
                int count = bean.getPayNum();
                float price = bean.getRecordPrice();
                String attribute = bean.getRecordName();
                shopId += bean.getPkId() + ",";
                Log.d("ddd", shopId + "----ShopCartId---" + shoppingName + "---" + count + "---" + price + "--attr---" + attribute);
            }
        }
//        ToastUtil.showL(this,"总价："+totalPrice);

//        Log.d("hhh", "shopPageFragment====shopId===" + shopId);

        //跳转到确认订单界面
        getDingDanHaoInfo(shopId);

    }

    private void getDingDanHaoInfo(String shopId) {
        String url = URLS.gwcJieSuanShopShow(2, shopId, URLS.getUser_id());
//        Log.d("ddd", "购物车结算------url----" + url);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<GwcDingDanOne>() {
            @Override
            public void onSuccess(GwcDingDanOne response) {

                if(response.getStatus() == 0){
                    gwcDingDanThreeList = response.getData().getOrderDetailList();
                    String dingdan = response.getData().getPkId();

                        Intent intent = new Intent(shop.this, JieSuanActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("dingdan", dingdan);
                        bundle.putFloat("heji", totalPrice);
                        bundle.putString("address", address);
                        bundle.putString("addressname", addressname);
                        bundle.putInt("pikid", pikid);
                        intent.putExtras(bundle);
                        startActivity(intent);

                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    //增加商品
    @Override
    public void doIncrease(int position, final View showCountView, boolean isChecked) {
        final GouWuCheBean gouWuCheBean = gouWuCheBeanList.get(position);
        int currentCount = gouWuCheBean.getPayNum();
        int shangPinId = gouWuCheBean.getPkId();

        currentCount++;

//        Log.d("ddd", "-----增加商品-1------" + currentCount);
        //接购物车更改数量的接口
        changeShopNumInfo(currentCount, shangPinId);
//        Log.d("ddd", "-----修改商品数量-4-changeMsg-----" + changeMsg);

        final int finalCurrentCount = currentCount;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(changeMsg.equals("ok")){
                    gouWuCheBean.setPayNum(finalCurrentCount);
//            Log.d("ddd", "-----修改商品数量-5-gouWuCheBean.getPayNum()-----" + gouWuCheBean.getPayNum());
                    ((TextView) showCountView).setText(gouWuCheBean.getPayNum() + "");
                    shopListViewAdapter1.notifyDataSetChanged();
                    statistics();
                    Toast.makeText(shop.this, "修改成功!", Toast.LENGTH_SHORT).show();
                }else {
//            Log.d("ddd", "-----修改商品数量-6-gouWuCheBean.getPayNum()-----" + gouWuCheBean.getPayNum());
                    Toast.makeText(shop.this, "修改失败!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);

        */
/*if(changeMsg.equals("ok")){
            gouWuCheBean.setPayNum(currentCount);
//            Log.d("ddd", "-----修改商品数量-5-gouWuCheBean.getPayNum()-----" + gouWuCheBean.getPayNum());
            ((TextView) showCountView).setText(gouWuCheBean.getPayNum() + "");
            shopListViewAdapter1.notifyDataSetChanged();
            statistics();
            Toast.makeText(shop.this, "修改成功!", Toast.LENGTH_SHORT).show();
        }else {
//            Log.d("ddd", "-----修改商品数量-6-gouWuCheBean.getPayNum()-----" + gouWuCheBean.getPayNum());
            Toast.makeText(shop.this, "修改失败!", Toast.LENGTH_SHORT).show();
        }*//*

//        gouWuCheBean.setPayNum(currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shopListViewAdapter1.notifyDataSetChanged();
//        statistics();
    }

    //减少商品
    @Override
    public void doDecrease(int position, final View showCountView, boolean isChecked) {
        final GouWuCheBean gouWuCheBean = gouWuCheBeanList.get(position);
        int currentCount = gouWuCheBean.getPayNum();
        int shangPinId = gouWuCheBean.getPkId();
        int changeCount = currentCount;
        if (currentCount == 1) {
            return;
        }
        currentCount--;

//        gouWuCheBean.setPayNum(currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shopListViewAdapter1.notifyDataSetChanged();
//        statistics();

        // //接购物车更改数量的接口
        changeShopNumInfo(currentCount, shangPinId);

        final int finalCurrentCount = currentCount;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(changeMsg.equals("ok")){
                    gouWuCheBean.setPayNum(finalCurrentCount);
                    ((TextView) showCountView).setText(gouWuCheBean.getPayNum() + "");
                    shopListViewAdapter1.notifyDataSetChanged();
                    statistics();
                    Toast.makeText(shop.this, "修改成功!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(shop.this, "修改失败!", Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);

        */
/*if(changeMsg.equals("ok")){
            gouWuCheBean.setPayNum(currentCount);
            ((TextView) showCountView).setText(gouWuCheBean.getPayNum() + "");
            shopListViewAdapter1.notifyDataSetChanged();
            statistics();
            Toast.makeText(shop.this, "修改成功!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(shop.this, "修改失败!", Toast.LENGTH_SHORT).show();
        }*//*


    }

    private void changeShopNumInfo(int currentCount, int shangPinId) {
            String url = URLS.changeShopNumShow(URLS.getUser_id(), currentCount, shangPinId);
        Log.d("ddd", "-----修改商品数量-2------" + url);
            OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChangeShopNum>() {
                @Override
                public void onSuccess(ChangeShopNum response) {
                    if(response.getStatus() == 0){
//                        Toast.makeText(shop.this, "修改成功!", Toast.LENGTH_SHORT).show();
                      changeMsg = response.getMsg();
                        Log.d("ddd", "-----修改商品数量changeMsg-3------" + changeMsg);
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
    }


    @Override
    public void childDelete(final int position) {
        int shangPinId = gouWuCheBeanList.get(position).getPkId();

        //接购物车删除的接口
        changeGouWuCheDelete(shangPinId);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.cancel();
                if ("删除成功".equals(msg)) {
                    gouWuCheBeanList.remove(position);
                    shopListViewAdapter1.notifyDataSetChanged();
                    statistics();
                    Toast.makeText(shop.this, "删除成功！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(shop.this, "删除失败！", Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);

//        if ("删除成功".equals(msg)) {
//            gouWuCheBeanList.remove(position);
//            shopListViewAdapter1.notifyDataSetChanged();
//            statistics();
//            Toast.makeText(shop.this, "删除成功！", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(shop.this, "删除失败！", Toast.LENGTH_SHORT).show();
//        }
    }

    private void changeGouWuCheDelete(int shangPinId) {
        String url = URLS.deleteShopShow(shangPinId);
        OkHttpUtils.get(url, new OkHttpUtils.ResultCallback<ChangeShopNum>() {
            @Override
            public void onSuccess(ChangeShopNum response) {
                if(response.getStatus() == 0){
                    msg = response.getMsg();
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    */
/**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     *//*

    @Override
    public void checkGroup(int position, boolean isChecked) {
        gouWuCheBeanList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shopListViewAdapter1.notifyDataSetChanged();
        statistics();
    }


    */
/**
     * 遍历list集合
     *
     * @return
     *//*

    private boolean isAllCheck() {

        for (GouWuCheBean group : gouWuCheBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    */
/**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     *//*

    public void statistics() {
        totalCount = 0;
        totalPrice = 0;
        for (int i = 0; i < gouWuCheBeanList.size(); i++) {
            GouWuCheBean gouWuCheBean = gouWuCheBeanList.get(i);
            if (gouWuCheBean.isChoosed()) {
                totalCount++;
                totalPrice += gouWuCheBean.getRecordPrice() * gouWuCheBean.getPayNum();
            }
        }

        float a = totalPrice;
        DecimalFormat aa = new DecimalFormat("#0.00");
        tvShowPrice.setText("合计:" + aa.format(a));
        tvSettlement.setText("结算(" + totalCount + ")");
    }

    public void getgouwuche(){

    }
}
*/
