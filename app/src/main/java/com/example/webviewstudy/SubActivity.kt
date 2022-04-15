package com.example.webviewstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        // 메인 화면에서 넘어온 인덱스, 기본 값 1
        val index = intent?.getIntExtra(SELECT_KEY, 1) ?: 1
        val imageView = findViewById<ImageView>(R.id.imageView)

        // index 의 값에 따라 이미지 표시
        imageView.setImageResource(
            when(index){
                1 -> {
                    R.drawable.result1
                }
                2 -> {
                    R.drawable.result2
                }
                else -> {
                    R.drawable.result3
                }
            }
        )

    }

    companion object {
        const val SELECT_KEY = "select_key"
    }
}