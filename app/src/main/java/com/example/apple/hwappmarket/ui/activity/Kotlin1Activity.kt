package com.example.apple.hwappmarket.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.apple.hwappmarket.R
import kotlinx.android.synthetic.main.activity_kotlin1.*

class Kotlin1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin1)
        tv.text="jkdlajfk"
    }
}
