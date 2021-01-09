package com.example.myapplicationclass

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.CookieManager
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_web.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class Web : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        setSupportActionBar(toolbar1)
        webview.settings.javaScriptEnabled=true
        webview.webViewClient= WebViewClient()
        webview.loadUrl("https://jwxt.scnu.edu.cn/")
//        StrictMode.setThreadPolicy(
//            StrictMode.ThreadPolicy.Builder()
//                .detectDiskReads().detectDiskWrites().detectNetwork()
//                .penaltyLog().build()
//        )
//        StrictMode.setVmPolicy(
//            VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
//                .penaltyLog().penaltyDeath().build()
//        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.done -> {
//                val url = URL(webview.url)
//                val cookieManager: CookieManager = CookieManager.getInstance()
//                val CookieStr = cookieManager.getCookie(webview.url)
//                Log.e("cookie", "  " + CookieStr)

//                val connection = url.openConnection() as HttpURLConnection
//                val document: Document = Jsoup.connect("https://jwxt.scnu.edu.cn").get()
//                val links: Elements = document.select("#footerID")
//                val str: String = links.get(0).text().toString()#footerID

//                val document: Document = Jsoup.connect(webview.url)
//                    .cookie("","")
//                    .post()
//                val links: Elements = document.select("head > title")
//                val str = links.text().toString()
                Toast.makeText(this, "未成功", Toast.LENGTH_LONG).show()
//                Log.d("jousp",)
            }
        }

        return true
    }
}