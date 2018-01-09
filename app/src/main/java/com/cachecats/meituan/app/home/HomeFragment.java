package com.cachecats.meituan.app.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cachecats.meituan.R;
import com.cachecats.meituan.base.BaseFragment;
import com.cachecats.meituan.utils.CommonUtils;
import com.cachecats.meituan.utils.GlideImageLoader;
import com.cachecats.meituan.widget.IconTitleView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by solo on 2018/1/8.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_banner)
    Banner banner;
    //大模块的LinearLayout布局
    @BindView(R.id.ll_big_module_fragment_home)
    LinearLayout llBigModule;

    private List<Drawable> mBannerImages;
    private Context mContext;

    private int[] bigModuleDrawables = {
            R.drawable.ic_vector_discover_normal,
            R.drawable.ic_vector_home_normal,
            R.drawable.ic_vector_mine_normal,
            R.drawable.ic_vector_order_normal,
            R.drawable.ic_vector_nearby_normal,
    };

    private String[] bigMudoleTitles = {
            "美食", "电影/演出", "酒店住宿", "休闲娱乐", "外卖"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        //绑定 ButterKnife
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initBanner();
        initBigModule();
    }

    @Override
    public void onStart() {
        super.onStart();
        //增加banner的体验
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //增加banner的体验
        banner.stopAutoPlay();
    }

    private void initBanner() {
        //设置图片集合
        mBannerImages = new ArrayList<>();
        mBannerImages.add(getResources().getDrawable(R.mipmap.banner1));
        mBannerImages.add(getResources().getDrawable(R.mipmap.banner2));
        mBannerImages.add(getResources().getDrawable(R.mipmap.banner3));
        mBannerImages.add(getResources().getDrawable(R.mipmap.banner4));
        mBannerImages.add(getResources().getDrawable(R.mipmap.banner5));
        mBannerImages.add(getResources().getDrawable(R.mipmap.banner6));

        //设置banner的各种属性
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .setImages(mBannerImages)
//                .setBannerAnimation(Transformer.Default)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }

    /**
     * 初始化banner下面的几个大模块入口
     */
    private void initBigModule() {

        // TODO 添加间距的逻辑有问题，待解决
        int screenWidth = CommonUtils.getScreenWidth(mContext);
        int marginLeft = screenWidth / (bigModuleDrawables.length + 1);
        for (int i = 0; i < 5; i++) {

            IconTitleView iconTitleView = IconTitleView.newInstance(mContext, bigModuleDrawables[i], bigMudoleTitles[i]);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(marginLeft, 0, 0, 0);
            iconTitleView.setLayoutParams(lp);

            llBigModule.addView(iconTitleView);

        }
    }
}