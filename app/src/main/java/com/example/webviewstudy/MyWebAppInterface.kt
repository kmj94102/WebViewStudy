package com.example.webviewstudy

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

// 웹과 앱사이의 인터페이스
class MyWebAppInterface(
    private val mContext: Context,
    private val moveActivity : (Int) -> Unit
) {

    // 토스트 메시지를 표시하기 위한 함수
    @JavascriptInterface
    fun showToast(message: String) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    // 로그인에 사용한 id, pw 를 저장하기 위한 함수
    @JavascriptInterface
    fun currentLoginInfo(id: String, pw: String) {
        MyPreference(mContext).setString(MyPreference.ID, id)
        MyPreference(mContext).setString(MyPreference.PW, pw)
    }

    // 선택한 캐릭터의 index 를 받아 페이지 이동을 하기 위한 함수
    @JavascriptInterface
    fun selectCharacter(index: Int) {
        moveActivity(index)
    }
}