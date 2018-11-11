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
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zx.zhuangxiu.R;

public class BusinessPageFragment extends Fragment implements View.OnClickListener {

    public static final int ITEM_SHANGPIN = 1;   //采购商品
    public static final int ITEM_GOUFUWU = 2; // 购买服务
    public static final int ITEM_HEZUO = 3; // 加盟合作

    private BusinessCgspFragment mBusinessCgspFragment;
    private BusinessGmfwFragment mBusinessGmfwFragment;
    private BusinessJmhzFragment mBusinessJmhzFragment;

    private RadioGroup mRadioGroup;
    private RadioButton mRb1, mRb2, mRb3;

    private int mCurrentItem;
    private long mExitToastTime;
    private Fragment frag = new BusinessCgspFragment();
    private FragmentTransaction transaction;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_business, container, false);
        showHomeFragment(savedInstanceState);
        // 显示首页
        switchItem(ITEM_SHANGPIN);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRb1 = (RadioButton) view.findViewById(R.id.business_rb1);
        mRb2 = (RadioButton) view.findViewById(R.id.business_rb2);
        mRb3 = (RadioButton) view.findViewById(R.id.business_rb3);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.business_rg);
        mRb1.setOnClickListener(this);
        mRb2.setOnClickListener(this);
        mRb3.setOnClickListener(this);

    }

    private void switchItem(int itemId) {
        switch (itemId) {
            case ITEM_SHANGPIN:
                mCurrentItem = ITEM_SHANGPIN;
                if (mBusinessCgspFragment == null) {
                    mBusinessCgspFragment = new BusinessCgspFragment();
                }
                switchContent(frag, mBusinessCgspFragment, "cgxp");
                frag = mBusinessCgspFragment;
                break;
            case ITEM_GOUFUWU:
                mCurrentItem = ITEM_GOUFUWU;
                if (mBusinessGmfwFragment == null) {
                    mBusinessGmfwFragment = new BusinessGmfwFragment();
                }
                switchContent(frag, mBusinessGmfwFragment, "gmfw");
                frag = mBusinessGmfwFragment;
                break;
            case ITEM_HEZUO:
                mCurrentItem = ITEM_HEZUO;
                if (mBusinessJmhzFragment == null) {
                    mBusinessJmhzFragment = new BusinessJmhzFragment();
                }
                switchContent(frag, mBusinessJmhzFragment, "jmhz");
                frag = mBusinessJmhzFragment;
                break;
        }

    }

    public void switchContent(Fragment from, Fragment to, String tag) {
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (to != null && from != null) {
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.business_container, to, tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }

    }

    private void showHomeFragment(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if (savedInstanceState != null) {
            mBusinessCgspFragment = (BusinessCgspFragment) fragmentManager.findFragmentByTag("cgxp");
            mBusinessGmfwFragment = (BusinessGmfwFragment) fragmentManager.findFragmentByTag("gmfw");
            mBusinessJmhzFragment = (BusinessJmhzFragment) fragmentManager.findFragmentByTag("jmhz");
            fragmentManager.beginTransaction()
                    .show(mBusinessCgspFragment)
                    .hide(mBusinessGmfwFragment)
                    .hide(mBusinessJmhzFragment)
                    .commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.business_rb1:
                switchItem(ITEM_SHANGPIN);
                break;
            case R.id.business_rb2:
                switchItem(ITEM_GOUFUWU);
                break;
            case R.id.business_rb3:
                switchItem(ITEM_HEZUO);
                break;
            default:
                break;
        }
    }

}
