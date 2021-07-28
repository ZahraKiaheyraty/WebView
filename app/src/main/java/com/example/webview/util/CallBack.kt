package com.example.webview.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Message
import android.view.KeyEvent
import android.webkit.*
import androidx.core.content.ContextCompat.startActivity

class CallBack (val ctx:Context): WebViewClient() {
    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return false

    }






}