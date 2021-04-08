package com.example.kotlin_bible

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_bible.*
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.dialog_bible.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BibleActivity : AppCompatActivity(){
    var page = arrayListOf<String>()
    var row = arrayListOf<String>()
    var content = arrayListOf<String>()

    var contentt :String =""

    var page_s = arrayListOf<String>("1","2","3","4","5","6")
    var row_s = arrayListOf<String>("1","2","3","4","5","6")
    var page_e = arrayListOf<String>("1","2","3","4","5","6")
    var row_e = arrayListOf<String>("1","2","3","4","5","6")
    var contents = arrayListOf<String>()
    var page1: Int = 1
    var row1: Int = 1
    var page2: Int = 1
    var row2: Int = 1

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)
        val bititle = intent.getStringExtra("Bible").toString()
        val vallue = intent.getStringExtra("Value").toString()
        val bicontent = intent.getStringExtra("Content").toString()
        //spinnerinit()

        TWAPIstart(vallue)
        if(bicontent == "null"){//null을 문자열로 한이유가 Content는 값이 null인데 toString으로 문자열로 바꿈
            bible_content.setText("시작")
        }else{
            bible_content.setText(bicontent)
        }

        bible_title.setText(bititle)
        btn_sel.setOnClickListener {
            val nextIntent = Intent(this, ListActivity::class.java)
            startActivity(nextIntent)
//            val dialog = CustomDialog(this)
//            dialog.myDig()
        }
        btn_search.setOnClickListener {
            //scl_bible.smoothScrollTo(0,5000)
            Log.i("여기",scl_bible.scrollY.toString())

        }
        //스크롤 움직일때마다 y좌표값 로그 출력
//        scl_bible.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            Log.i("여기",scrollY.toString())
//        }

    }

fun TWAPIstart(key :String){
    val response = TWAPIConfig.getService().requestSearch(key)
    response.enqueue(object : Callback<List<BibleData>>{
        override fun onResponse(call: Call<List<BibleData>>, response: Response<List<BibleData>>) {
            var asd: List<BibleData>? = response.body()
            var rsize : Int
            if(asd?.size == null){
                var to = Toast.makeText(applicationContext,"값이 없습니다.",Toast.LENGTH_SHORT)
                to.show()
            }else{
                rsize = asd.size
                for(i in 0..rsize-1){
                    page.add(i,asd?.get(i)?.page)
                    row.add(i,asd?.get(i)?.row)
                    content.add(i,asd?.get(i)?.contents)
                    Log.i("여기",page[i]+","+row[i]+","+content[i])
                    contentt += row[i]+"절 :"+content[i]
                }
                bible_content.setText(contentt)
            }
        }

        override fun onFailure(call: Call<List<BibleData>>, t: Throwable) {
            Log.i("여기Error",t.message.toString())
        }

    })

}
fun spinnerinit(){
    spn_page_s.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,page_s)
    spn_page_s.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            Log.i("여기",page_s[position])
            page1 = page_s[position].toInt()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            Log.i("여기","앙골라")
        }
    }
    spn_row_s.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,row_s)
    spn_row_s.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            Log.i("여기",row_s[position])
            row1 = row_s[position].toInt()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            Log.i("여기","앙골라")
        }
    }
    spn_page_e.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,page_e)
    spn_page_e.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            Log.i("여기",page_e[position])
            page2 = page_e[position].toInt()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            Log.i("여기","앙골라")
        }
    }

    spn_row_e.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,row_e)
    spn_row_e.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            Log.i("여기",row_e[position])
            row2 = row_e[position].toInt()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            Log.i("여기","앙골라")
        }
    }
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