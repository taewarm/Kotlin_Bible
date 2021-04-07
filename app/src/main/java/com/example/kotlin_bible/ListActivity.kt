package com.example.kotlin_bible

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.list_item.*

class ListActivity : AppCompatActivity(){

    var list = arrayListOf<Bible>(
        Bible("마복"),Bible("출굽")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val bibleadapter = BibleAdapter(this,list)
        lsv_bible1.adapter = bibleadapter

        lsv_bible1.setOnItemClickListener { parent, view, position, id ->
            val nextIntent = Intent(this, BibleActivity::class.java)
            nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//
            nextIntent.putExtra("Bible",list[position].bible)
            nextIntent.putExtra("Content",list[position].bible+"내용들...")
            startActivity(nextIntent)
        }

    }
}

