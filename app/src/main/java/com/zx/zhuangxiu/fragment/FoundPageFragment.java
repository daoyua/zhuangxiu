package com.zx.zhuangxiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zx.zhuangxiu.R;
import com.zx.zhuangxiu.activity.SouSuoActivity;

public class FoundPageFragment extends Fragment implements OnClickListener{

    public static final int ITEM_WORKER = 1;   //找工人
    public static final int ITEM_WORK = 2; // 找工作
    public static final int ITEM_CHANPIN = 3; // 找产品
    public static final int ITEM_FUWU = 4; // 找服务

    private WorkerFragment mWorkerFragment;
    private WorkFragment mWorkFragment;
    private ChanPinFragment mChanPinFragment;
    private FuWuFragment mFuWuFragment;

    private RadioGroup mRadioGroup;
    private RadioButton mRb1, mRb2, mRb3, mRb4;
    private TextView found_search;

    private int mCurrentItem;
    private long mExitToastTime;
    private Fragment frag = new WorkerFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_foundpage, container, false);

        showHomeFragment(savedInstanceState);

        initView(view);
        // 显示首页
        switchItem(ITEM_WORKER);


        return view;
    }

    private void initView(View view) {

        mRb1 = (RadioButton) view.findViewById(R.id.found_rb1);
        mRb2 = (RadioButton) view.findViewById(R.id.found_rb2);
        mRb3= (RadioButton) view.findViewById(R.id.found_rb3);
        mRb4 = (RadioButton) view.findViewById(R.id.found_rb4);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.found_rg);
        found_search = (TextView)view.findViewById(R.id.found_search);

        found_search.setOnClickListener(this);
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);
        mRb3.setOnClickListener(this);
        mRb4.setOnClickListener(this);

    }

    private void switchItem(int itemId) {
        switch (itemId) {
            case ITEM_FUWU:
                mCurrentItem = ITEM_FUWU;
                if (mFuWuFragment == null) {
                    mFuWuFragment = new FuWuFragment();
                }
                switchContent(frag, mFuWuFragment, "fuwu");
                frag = mFuWuFragment;
                break;
            case ITEM_WORKER:
                mCurrentItem = ITEM_WORKER;
                if (mWorkerFragment == null) {
                    mWorkerFragment = new WorkerFragment();
                }
                switchContent(frag, mWorkerFragment, "worker");
                frag = mWorkerFragment;
                break;
            case ITEM_WORK:
                mCurrentItem = ITEM_WORK;
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                }
                switchContent(frag, mWorkFragment, "work");
                frag = mWorkFragment;
                break;
            case ITEM_CHANPIN:
                mCurrentItem = ITEM_CHANPIN;
                if (mChanPinFragment == null) {
                    mChanPinFragment = new ChanPinFragment();
                }
                switchContent(frag, mChanPinFragment, "chanpin");
                frag = mChanPinFragment;
                break;
        }

    }

    public void switchContent(Fragment from, Fragment to, String tag) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (to != null && from != null){
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.found_container, to, tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }

    }

    private void showHomeFragment(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (savedInstanceState != null) {
            mWorkerFragment = (WorkerFragment) fragmentManager.findFragmentByTag("worker");
            mWorkFragment = (WorkFragment) fragmentManager.findFragmentByTag("work");
            mChanPinFragment = (ChanPinFragment) fragmentManager.findFragmentByTag("chanpin");
            mFuWuFragment = (FuWuFragment) fragmentManager.findFragmentByTag("fuwu");
            fragmentManager.beginTransaction()
                    .show(mWorkerFragment)
                    .hide(mWorkFragment)
                    .hide(mChanPinFragment)
                    .hide(mFuWuFragment)
                    .commit();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.found_rb1:
                switchItem(ITEM_WORKER);
                break;
            case R.id.found_rb2:
                switchItem(ITEM_WORK);
                break;
            case R.id.found_rb3:
                switchItem(ITEM_CHANPIN);
                break;
            case R.id.found_rb4:
                switchItem(ITEM_FUWU);
                break;
            case R.id.found_search:
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
        Fragment person = getActivity().getSupportFragmentManager().findFragmentByTag("fuwu");
        /*然后在碎片中调用重写的onActivityResult方法*/
        person.onActivityResult(requestCode, resultCode, data);
    }
}
