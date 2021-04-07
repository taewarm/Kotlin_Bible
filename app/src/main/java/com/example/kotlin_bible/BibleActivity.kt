package com.example.kotlin_bible

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bible.*
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.dialog_bible.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BibleActivity : AppCompatActivity(){
    var page : String? = ""
    var row : String? = ""
    var content : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)
        TWAPIstart()
        val bititle = intent.getStringExtra("Bible").toString()
        val bicontent = intent.getStringExtra("Content").toString()
        if(bicontent == "null"){//null을 문자열로 한이유가 Content는 값이 null인데 toString으로 문자열로 바꿈
            bible_content.setText("시작")
        }else{
            bible_content.setText(bicontent)
        }

        bible_title.setText(bititle)
        btn_sel.setOnClickListener {
            var t1 = Toast.makeText(this,"btn_sel",Toast.LENGTH_LONG)
            t1.show()
            val nextIntent = Intent(this, ListActivity::class.java)
            startActivity(nextIntent)
//            val dialog = CustomDialog(this)
//            dialog.myDig()
        }
        btn_search.setOnClickListener {
            var tt = Toast.makeText(this,"btn_search",Toast.LENGTH_LONG)
            tt.show()
        }


    }

fun TWAPIstart(){
    val response = TWAPIConfig.getService().requestSearch("tae")
    response.enqueue(object : Callback<List<BibleData>>{
        override fun onResponse(call: Call<List<BibleData>>, response: Response<List<BibleData>>) {
            var asd: List<BibleData>? = response.body()
            page = asd?.get(0)?.page
            row = asd?.get(0)?.row
            content = asd?.get(0)?.contents
            Log.i("여기",page+","+row+","+content)
            Log.i("여기",response.toString())

        }

        override fun onFailure(call: Call<List<BibleData>>, t: Throwable) {
            Log.i("여기Error",t.message.toString())
        }

    })

}

}
//class CustomDialog(context: Context): BibleActivity() {
//    var list = arrayListOf<Bible>(
//        Bible("마복"),Bible("출굽")
//    )
//
//
//    val dialog = Dialog(context)
//    fun myDig(){
//
//        dialog.setContentView(R.layout.dialog_bible)
//        lsv_bible.adapter = BibleAdapter(dialog.context,list)
//        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
//        dialog.setCanceledOnTouchOutside(true)
//        dialog.setCancelable(true)
//        dialog.show()
//    }
//}