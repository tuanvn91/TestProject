package com.tadfas.testproject

import android.app.Application
import androidx.recyclerview.widget.RecyclerView
import com.tadfas.testproject.SuggestionListAdapter.SuggestionItemHolder
import com.tadfas.testproject.SuggestionItem
import com.tadfas.testproject.SuggestionListAdapter.OnSuggestionItemSelected
import android.view.ViewGroup
import android.view.LayoutInflater
import com.tadfas.testproject.R
import android.view.View.OnLongClickListener
import android.widget.TextView

//import com.muicv.mutils.AdsManager;
class MyApplication : Application() {
    //    private AdsManager adsManager;
    override fun onCreate() {
        super.onCreate()
        //        AdsManager.DEBUG = BuildConfig.DEBUG;
//        AdsManager.PREFERENCE_NAME = "topvidieodownloader";
//        AdsManager.APPLICATION_ID = getPackageName();
//        adsManager = new AdsManager(this);
    }
}