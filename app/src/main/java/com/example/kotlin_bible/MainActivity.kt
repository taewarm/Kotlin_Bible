package com.example.kotlin_bible

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val title = "ㅅㄱㅂㄱ"
        btn_bible.setOnClickListener {
            val nextIntent = Intent(this, BibleActivity::class.java)
            nextIntent.putExtra("Bible",title)
            startActivity(nextIntent)
        }
        //테스트할때마다 쓸것
        btn_song.setOnClickListener {
//            val nextIntent = Intent(this, ListActivity::class.java)
//            startActivity(nextIntent)
        }
    }
}
