package com.example.kotlin_bible

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.list_item.*

class BibleAdapter (val context: Context, val biblelist: ArrayList<Bible>) : BaseAdapter() {

    override fun getCount(): Int {
        return biblelist.size
    }

    override fun getItem(position: Int): Any {
        return biblelist[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item,null)

        val bibletext = view.findViewById<TextView>(R.id.txt_bible)
//        val item = view.findViewById<LinearLayout>(R.id.item_click)
        val bible = biblelist[position]
        bibletext.text = bible.bible
        return view
    }

}