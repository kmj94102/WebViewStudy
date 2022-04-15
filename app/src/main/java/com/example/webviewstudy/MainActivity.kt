package com.example.webviewstudy

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        webView.apply {
            // WebView 에 불러올 url 주소
            // 방법 1 : 실제 인터넷 주소를 이용하는 방법
             loadUrl("https://m.naver.com")

            // 방법 2 : html 태그를 이용하는 방법
            // val document = "<!DOCTYPE html><head>웹뷰 테스트</head>" +
            //                "<body><h1>웹뷰 테스트!!</h1>직접 넣는 방식으로 만들어 봤습니다.</body></html>"
            // webView.loadData(document, "text/html; charset=utf-8", "UTF-8")

            // 방법 3 : app > assets 하위에 파일들 생성 후 로드하는 방법
            loadUrl("file:///android_asset/login.html")

            // 앱과 웹에 인터페이스를 이용하여 제어하는 방식
            addJavascriptInterface(
                MyWebAppInterface(this@MainActivity) { moveActivity(it) },
                "Android"
            )

            // 프롬프트, 알럿, 로그 등을 제어, 커스텀할 수 있습니다.
            webChromeClient = MyWebChromeClient(this@MainActivity)
            // 웹뷰 상태 변화에 따라 웹뷰를 제어, 추가 행동 등을 할 수 있습니다.
            webViewClient = MyWebClient()
            // 자료 출처 : https://stackoverflow.com/questions/3103132/android-listview-scrollbarstyle
            View.SCROLLBARS_INSIDE_INSET
            View.SCROLLBARS_INSIDE_OVERLAY
            View.SCROLLBARS_OUTSIDE_INSET
            View.SCROLLBARS_OUTSIDE_OVERLAY
            scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY

            setNetworkAvailable(true)
            // 웹 페이지 로드 전에 캐시와 히스토리를 삭제합니다.
            clearCache(true)
            clearHistory()
        }

        webView.settings.apply {
            // 자바스크립트 사용 허가여부 : false 로 설정하게 되면 javascript 로 작성한 부분이 작동하지 않습니다.
            javaScriptEnabled = true
            // 웹뷰 내 컨텐츠를 줌 기능 가능 여부. 두 개 모두 true 해야 줌이 가능합니다.
            setSupportZoom(true)
            builtInZoomControls = true
            // true 로 설정 시 WebView 에서 제공하는 시스템 컨트롤러가 나옵니다.
            displayZoomControls = false

            // 캐시 모드 설정. Deprecated 된 LOAD_NORMAL 을 제외하고
            // [LOAD_DEFAULT | LOAD_CACHE_ELSE_NETWORK | LOAD_CACHE_ONLY | LOAD_NO_CACHE] 4 종류가 있습니다.
            // LOAD_DEFAULT : 기본 모드, 캐시 사용, 기간 만료 시 네트워크 사용
            // LOAD_CACHE_ELSE_NETWORK : 캐시 기간 만료 시 네트워크 접속
            // LOAD_CACHE_ONLY : 캐시만 사용 (네트워크 사용x)
            // LOAD_NO_CACHE : 캐시 모드 사용 x
            cacheMode = WebSettings.LOAD_NO_CACHE

            // 폰의 system 글꼴 크기에 의해 변하는 것 방지. 글꼴의 크기를 비율(%)로 고정.
            textZoom = 100

            // localStorage 를 사용하여 dom 을 가져올 수 있도록 함
            domStorageEnabled = true
            // WebView 에서 앱에 등록되어있는 이미지 리소스를 사용해야 할 경우 자동으로 로드 여부
            loadsImagesAutomatically = true
            // WebView 를 통해 content Url 에 접근 가능 여부
            allowContentAccess = false
            // 기본 인코딩을 설정
            defaultTextEncodingName = "UTF-8"
            // 미디어 자동 재생 가능 여부
            mediaPlaybackRequiresUserGesture = false
            // javascript 의 window.open() 동작 허용 여부
            javaScriptCanOpenWindowsAutomatically = true
            // 여러개의 윈도우를 사용할 수 있도록 설정
            setSupportMultipleWindows(true)
        }

    }

    // 뒤로 가기 제어
    override fun onBackPressed() {
        // 웹뷰가 뒤로 가기가 가능한지 여부를 체크하여 웹뷰 뒤로가기 또는 액티비티 뒤로가기를 실행합니다.
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun moveActivity(index : Int) {
        // 두 번째 화면으로 이동합니다. 이동 시에 선택한 index 값을 전달합니다.
        val intent = Intent(this, SubActivity::class.java).also {
            it.putExtra(SubActivity.SELECT_KEY, index)
        }
        startActivity(intent)
    }

}
/**
 * 참고한 자료
 * 구글 공식 문서 : https://developer.android.com/guide/webapps/webview?hl=ko
 * 블로그 1 : https://www.blueswt.com/117
 * 블로그 2 : https://kyome.tistory.com/149
 * 블로그 3 : https://seosh817.tistory.com/67
 * */