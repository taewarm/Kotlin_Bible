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
import kotlin.coroutines.coroutineContext

open class BibleActivity : AppCompatActivity(){
    var page = arrayListOf<String>()
    var row = arrayListOf<String>()
    var content = arrayListOf<String>()

    var contentt :String =""


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)
        val bititle = intent.getStringExtra("Bible").toString()
        val vallue = intent.getStringExtra("Value").toString()
        val bicontent = intent.getStringExtra("Content").toString()
        val dialog = CustomDialog(this)
        TWAPIstart(vallue)
        if(bicontent == "null"){//null을 문자열로 한이유가 Content는 값이 null인데 toString으로 문자열로 바꿈
            bible_content1.setText("시작")
        }else{
            bible_content1.setText(bicontent)
        }

        bible_title.setText(bititle)
        btn_sel.setOnClickListener {
            val nextIntent = Intent(this, ListActivity::class.java)
            startActivity(nextIntent)

        }
        btn_search.setOnClickListener {
//            scl_bible.smoothScrollTo(0,bible_content2.top);
            Log.i("여기",scl_bible.scrollY.toString())
            dialog.myDig()
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
                bible_content1.setText(page[0]+"ㅈ"+row[0]+"ㅈ"+content[0])
                bible_content2.setText(page[1]+"ㅈ"+row[1]+"ㅈ"+content[1])
                bible_content3.setText(page[2]+"ㅈ"+row[2]+"ㅈ"+content[2])
                bible_content4.setText(page[3]+"ㅈ"+row[3]+"ㅈ"+content[3])
                bible_content5.setText(page[4]+"ㅈ"+row[4]+"ㅈ"+content[4])
            }
        }

        override fun onFailure(call: Call<List<BibleData>>, t: Throwable) {
            Log.i("여기Error",t.message.toString())
        }

    })

}
}
class CustomDialog(context: Context){
    private val dialog = Dialog(context)
    var tae1 : String =""
    var tae2 : String =""
    val list = arrayListOf<String>("1","2","3")
    val list1 = arrayListOf<String>("1","2","3")
    val myAdapter = ArrayAdapter(dialog.context,android.R.layout.simple_spinner_dropdown_item,list)
    val myAdapter1 = ArrayAdapter(dialog.context,android.R.layout.simple_spinner_dropdown_item,list1)
    fun myDig(){
        dialog.setContentView(R.layout.dialog_bible)

        val spp_list = dialog.sp_list
        val spp_list1 = dialog.sp_list1
        spp_list.adapter = myAdapter
        spp_list1.adapter = myAdapter1
        val asd = BibleActivity()
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
        val btn_Ok = dialog.btn_ok
        btn_Ok.setOnClickListener {
            Log.i("여기","실행")
            val sel = spp_list.selectedItemPosition
            val sel1 = spp_list1.selectedItemPosition
            tae1 = spp_list.getItemAtPosition(sel).toString()
            tae2 = spp_list1.getItemAtPosition(sel1).toString()
            dialog.hide()
        }
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()
    }
}