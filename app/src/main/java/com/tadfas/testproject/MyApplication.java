package com.tadfas.testproject;

import android.app.Application;

import com.muicv.mutils.AdsManager;


public class MyApplication extends Application {
    private AdsManager adsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        AdsManager.DEBUG = BuildConfig.DEBUG;
        AdsManager.PREFERENCE_NAME = "topvidieodownloader";
        AdsManager.APPLICATION_ID = getPackageName();
        adsManager = new AdsManager(this);


    }
}
