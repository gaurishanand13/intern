package com.example.internapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class webVieww : AppCompatActivity() {


    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back)

       var webview = findViewById<WebView>(R.id.webview)
        //intent.getStringExtra("url")!!
        webview.loadUrl("https://www.androidauthority.com/working-with-webview-736873/")
    }
}