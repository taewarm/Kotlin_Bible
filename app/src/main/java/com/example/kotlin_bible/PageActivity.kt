package com.example.kotlin_bible

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_page.*
import kotlinx.android.synthetic.main.list_item.*

class PageActivity : AppCompatActivity(){

    val sp_page_item = arrayListOf<String>("1","2","3","4","5")
    val sp_row_item = arrayListOf<String>("1","2","3","4","5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        val mypageAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sp_page_item)
        ppage.adapter = mypageAdapter
        val myrowAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,sp_row_item)
        rrow.adapter = myrowAdapter

        btn_page_ok.setOnClickListener {
            val nextIntent = Intent(this, BibleActivity::class.java)
            nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//
            nextIntent.putExtra("Ppage",ppage.selectedItem.toString())
            nextIntent.putExtra("Rrow",rrow.selectedItem.toString())
            startActivity(nextIntent)
        }
    }
}

