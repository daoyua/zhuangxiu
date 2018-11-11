package com.zx.zhuangxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.zx.zhuangxiu.adapter.ShoppingCartAdapter;
import com.zx.zhuangxiu.model.BaseBean;
import com.zx.zhuangxiu.model.ChakangouwucheBean;
import com.zx.zhuangxiu.model.OrderBean;
import com.zx.zhuangxiu.model.OrderEntity;
import com.zx.zhuangxiu.utils.ToastUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class ShopCarActicity extends AppCompatActivity implements View.OnClickListener
        , ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {

    private TextView mBack;
    private ListView mListiview;
    private CheckBox ckAll;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private ShoppingCartAdapter shoppingCartAdapter;
    private List<ChakangouwucheBean.DataBean> shoppingCartBeanList = new ArrayList<>();
    private TextView mPrice;
    private Button btn_jiesuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        mBack = findViewById(R.id.back);
        mPrice = findViewById(R.id.sc_price);
        mListiview = findViewById(R.id.shopcar_listview);
        ckAll = findViewById(R.id.all_chose);
        btn_jiesuan = findViewById(R.id.jiesuan_button);
        btn_jiesuan.setOnClickListener(this);


        shoppingCartAdapter = new ShoppingCartAdapter(this);
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        mListiview.setAdapter(shoppingCartAdapter);

        getGouWuCheList();
        ckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingCartBeanList.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 购物车列表
     */
    private void getGouWuCheList() {
        String userid = String.valueOf(URLS.getUser_id());
        String url = URLS.chaXunShopShow();
        FormBody formBody = new FormBody.Builder().add("userId", userid).build();

        OkHttpUtils.post(url, formBody, new OkHttpUtils.ResultCallback<ChakangouwucheBean>() {
            @Override
            public void onSuccess(ChakangouwucheBean response) {
                if (response.getResult() == 1) {
                    shoppingCartBeanList.addAll(response.getData());
                    shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
                }
            }

            @Override
            public void onFailure(Exception e) {
                ToastUtil.showShort(getApplicationContext(), "请求失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.jiesuan_button:
                lementOnder();
                break;
        }
    }

    /*
     *  结算
     * */

    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        //选中的需要提交的商品清单
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ChakangouwucheBean.DataBean dataBean = shoppingCartBeanList.get(i);
            if (dataBean.getChoosed()) {
                if (i == shoppingCartBeanList.size() - 1)//当循环到最后一个的时候 就不添加逗号,
                {
                    sb.append(shoppingCartBeanList.get(i).getShopCartId());
                } else {
                    sb.append(shoppingCartBeanList.get(i).getShopCartId());
                    sb.append(",");
                }
            }
        }
        String id = sb.toString();
        Log.e("TAG", "shopCarID" + id);
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, "您还未选中任何商品", Toast.LENGTH_SHORT).show();
            return;
        }

//        shopOrder(id, 165);

        qujiesuan(id);
    }

    private void qujiesuan(final String id) {
        String qujiesuan = URLS.qujiesuan(URLS.getUser_id(), id);
        OkHttpUtils.get(qujiesuan, new OkHttpUtils.ResultCallback<OrderEntity>() {
            @Override
            public void onSuccess(OrderEntity response) {
                if (response.getResult()==1){
                    Intent intent = new Intent(ShopCarActicity.this,Settlement.class);
                    intent.putExtra("id",id);
                    intent.putExtra("order",response);
                    startActivity(intent);
                }else {
                    Toast.makeText(ShopCarActicity.this, "生成订单失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }



    /*
     *  单选
     * */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        shoppingCartBeanList.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /*
     *  增加
     * */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ChakangouwucheBean.DataBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
        ShangpinNum(shoppingCartBean.getShopCartId(), currentCount);
    }

    /*
     *   删减
     * */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ChakangouwucheBean.DataBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
        ShangpinNum(shoppingCartBean.getShopCartId(), currentCount);
    }

    /*
     *  刪除
     * */
    @Override
    public void childDelete(int position) {
        deleteShangpin(shoppingCartBeanList.get(position).getShopCartId());
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();

    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ChakangouwucheBean.DataBean group : shoppingCartBeanList) {
            if (!group.getChoosed())
                return false;
        }
        return true;
    }

    /*
     *  合计
     * */

    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ChakangouwucheBean.DataBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.getChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        mPrice.setText("合计:" + new DecimalFormat("0.00").format(totalPrice));
    }

    /*
     *  修改数量
     * */
    public void ShangpinNum(int id, int num) {
        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("shopCartId", id + "")
                .add("number", num + "")
                .build();

        OkHttpUtils.post(URLS.shangpinNum(), formBody, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }

    /*
     *  删除商品
     * */
    public void deleteShangpin(int id) {
        FormBody formBody = new FormBody.Builder().add("userId", URLS.getUser_id() + "")
                .add("shopCartId", id + "")
                .build();

        OkHttpUtils.post(URLS.deleteshangpin(), formBody, new OkHttpUtils.ResultCallback<BaseBean>() {
            @Override
            public void onSuccess(BaseBean response) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
