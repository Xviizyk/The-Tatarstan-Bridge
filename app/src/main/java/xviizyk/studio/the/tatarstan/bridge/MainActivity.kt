package xviizyk.studio.the.tatarstan.bridge

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint

@SuppressLint("SetJavaScriptEnabled")
class MainActivity : AppCompatActivity() {
    var server: LocalHost? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
            android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        server = LocalHost(this, 1488)
        server?.start()
        val webView = WebView(this)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://127.0.0.1:1488/")
        setContentView(webView)
    }
    override fun onDestroy() {
        super.onDestroy()
        server?.stop()
    }
}