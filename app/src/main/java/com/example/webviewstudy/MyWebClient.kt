package com.example.webviewstudy

import android.webkit.WebView
import android.webkit.WebViewClient

// WebViewClient Custom
class MyWebClient : WebViewClient() {

    // 페이지 로드가 완료 되어 있을 때 처리하기 위한 함수
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        view?: return

        // 주소가 로그인 화면에서만 유효하도록 설정
        if (url?.contains("login.html") == true) {
            val id = MyPreference(view.context).getString(MyPreference.ID, "")
            val pw = MyPreference(view.context).getString(MyPreference.PW, "")

            if (id.isNotEmpty() && pw.isNotEmpty()) {
                // 웹의 setLoginInfo 함수 호출
                view.evaluateJavascript("javascript:setLoginInfo('$id', '$pw');") {}
            }
        }
    }
}