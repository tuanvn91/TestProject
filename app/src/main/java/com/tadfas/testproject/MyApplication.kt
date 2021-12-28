package com.tadfas.testproject

import android.app.Application
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.localization.ContentCountry
import org.schabi.newpipe.extractor.localization.Localization

//import com.muicv.mutils.AdsManager;
open class MyApplication : Application() {
    //    private AdsManager adsManager;
    override fun onCreate() {
        super.onCreate()
        //        AdsManager.DEBUG = BuildConfig.DEBUG;
//        AdsManager.PREFERENCE_NAME = "topvidieodownloader";
//        AdsManager.APPLICATION_ID = getPackageName();
//        adsManager = new AdsManager(this);

        NewPipe.init(
            DownloaderImpl.init(null),
            Localization
                .fromLocalizationCode("system"),
            ContentCountry("system")
        )
    }
}