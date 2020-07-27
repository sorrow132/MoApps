package yuresko.moapps.webview

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import yuresko.moapps.R
import yuresko.moapps.core.base.BaseActivity

class WebViewActivity : BaseActivity() {
    private lateinit var webView: WebView
    private var url: String =
        "https://html5.mo-apps.com//Content/2_3/ios/index.html#b7877550-862c-2f2b-7c24-897a541b5f82"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_activity)
        webView = findViewById(R.id.webViewAct)

        val webSettings = webView!!.settings
        webView.settings.javaScriptEnabled = true

        webView!!.webViewClient = WebViewClient()
        webView!!.webChromeClient = WebChromeClient()
        webView!!.loadUrl(url)

        webView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
    }
}