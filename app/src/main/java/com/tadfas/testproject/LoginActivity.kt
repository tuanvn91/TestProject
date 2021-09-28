package com.tadfas.testproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.*
import com.muicv.mutils.AdOverlayActivity
import com.tadfas.testproject.databinding.ActivityLoginBinding

class LoginActivity : AdOverlayActivity() {
    private lateinit var binding: ActivityLoginBinding

    val USERID = "user_id"
    val COOKIES = "Cookies"
    val SESSIONID = "session_id"
    val ISINSTALOGIN = "IsInstaLogin"
    private var urlVideo: String? = ""


    private var cookies: String? = null
    private val client :WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onLoadResource(webView: WebView, str: String) {
            super.onLoadResource(webView, str)
        }

        override fun onPageFinished(webView: WebView, str: String) {
            super.onPageFinished(webView, str)
            cookies = CookieManager.getInstance().getCookie(str)
            try {
                val session_id = getCookie(str, "sessionid")
                val csrftoken = getCookie(str, "csrftoken")
                val userid = getCookie(str, "ds_user_id")
                if (session_id != null && csrftoken != null && userid != null) {
                    mPrefs.edit().putString(COOKIES, cookies).apply()
                    mPrefs.edit().putString(SESSIONID, session_id).apply()
                    mPrefs.edit().putString(USERID, userid).apply()
                    mPrefs.edit().putInt(ISINSTALOGIN, 1).apply()
                    webView.destroy()
                    val intent = Intent()
                    intent.putExtra("url_video", urlVideo)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onReceivedError(webView: WebView, i: Int, str: String, str2: String) {
            super.onReceivedError(webView, i, str, str2)
        }

        override fun shouldInterceptRequest(
            webView: WebView,
            webResourceRequest: WebResourceRequest
        ): WebResourceResponse? {
            return super.shouldInterceptRequest(webView, webResourceRequest)
        }

        override fun shouldOverrideUrlLoading(
            webView: WebView,
            webResourceRequest: WebResourceRequest
        ): Boolean {
            return super.shouldOverrideUrlLoading(webView, webResourceRequest)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Login Instagram"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        urlVideo = intent.getStringExtra("url_video")
        Log.d("url", urlVideo + "")

        initViews()
        binding.swipeRefreshLayout.setOnRefreshListener { initViews() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.clearCache(true)
        binding.webView.webViewClient = client

//        clearCookies(this@LoginActivity)

        binding.webView.loadUrl("https://www.instagram.com/accounts/login/")
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.swipeRefreshLayout.isRefreshing = progress != 100
            }
        }
    }

    fun getCookie(siteName: String?, CookieName: String?): String? {
        var CookieValue: String? = null
        val cookieManager = CookieManager.getInstance()
        val cookies = cookieManager.getCookie(siteName)
        if (cookies != null && cookies.isNotEmpty()) {
            val temp = cookies.split(";".toRegex()).toTypedArray()
            for (ar1 in temp) {
                if (ar1.contains(CookieName!!)) {
                    val temp1 = ar1.split("=".toRegex()).toTypedArray()
                    CookieValue = temp1[1]
                    break
                }
            }
        }
        return CookieValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}