package com.example.webviewstudy

import android.content.Context
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog

// WebChromeClient Custom
class MyWebChromeClient(private val context: Context) : WebChromeClient() {

    // 웹에서 사용한 콘솔 로그를 앱의 log 로 표기하기 위한 함수
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Log.e("WebViewLog", "${consoleMessage?.message()}")
        return super.onConsoleMessage(consoleMessage)
    }

    // 웹에서 사용한 알럿을 앱의 알럿으로 표시하기 위한 함수
    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        AlertDialog.Builder(context)
            .setTitle("알림")
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()

        result?.cancel()
        return true
    }
}