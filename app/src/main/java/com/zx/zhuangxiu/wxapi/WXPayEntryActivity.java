package com.zx.zhuangxiu.wxapi;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zx.zhuangxiu.Constants;
import com.zx.zhuangxiu.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI iwxapi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        iwxapi= WXAPIFactory.createWXAPI(this, Constants.APP_ID,false);
        iwxapi.handleIntent(getIntent(),this);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        iwxapi.handleIntent(intent,this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            switch (baseResp.errCode){
                case BaseResp.ErrCode.ERR_OK://成功
                    Toast.makeText(WXPayEntryActivity.this,"支付成功",Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://取消支付
                    Toast.makeText(WXPayEntryActivity.this,"取消支付",Toast.LENGTH_LONG).show();
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_COMM://-1
                    Toast.makeText(WXPayEntryActivity.this,"-1",Toast.LENGTH_LONG).show();
                    finish();
                    break;
            }
        }
    }
}
