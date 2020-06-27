package com.example.todolist

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main2.*
import java.text.SimpleDateFormat
import java.util.*

class Main2Activity : AppCompatActivity() {
    private var isEventMode = false //用來區分新增或是編輯模式
    private var eventKey = ""       //儲存Thing(key)值
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        title = "待辦事項"
        var calendar = Calendar.getInstance()   //getInstance會返回一個Calendar,
                                                // 該Calendar初始化使用到當前日期時間

        if(!intent.getStringExtra("event").isNullOrEmpty()){//若intent夾帶 "event"資料 不為空值 則執行此條件
            isEventMode = true
            title = "編輯模式"
            val eventString = intent.getStringExtra("event")
                                            //intent取得 夾帶資料 "event"的資料
            val event = Gson().fromJson(eventString,Thing::class.java)
            eventKey = event.key            //取得 key值
            ed_input.setText(event.title)   //取得標題
            edContent.setText(event.content)//取得內容
            tvDate.text = event.date        //取得日期
        }

        val listener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //系統預設
                calendar.set(year,month,dayOfMonth) //calendar 設定使用者選到的年月日
                val myFormat = "yyyy - MM - dd" //定義輸入的日期格式
                val sdf = SimpleDateFormat(myFormat,Locale.TAIWAN)//填入(格式,時區)
                tvDate.text = sdf.format(calendar.time)           //calendar.time 顯示設定時間
            }

        imgCalender.setOnClickListener {
            DatePickerDialog(
                this,
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        but_accept.setOnClickListener {
            if (ed_input.text.isBlank()) {
                Toast.makeText(this, "請輸入待辦事項!!", Toast.LENGTH_SHORT).show()
            } else {
                save()
                setResult(Activity.RESULT_OK)//設定resultcode = Acitivity.RESULT_OK
                finish() //完成並返回MainActivity
            }
        }
        but_cannel.setOnClickListener {
            finish()
        }
    }

    fun save() {                    //找到或建立一個"save"的sharedPreferences文件
        val sharedPreferences = getSharedPreferences("todo1", Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()//取得Editor物件

        if(isEventMode == true){
            val todo = Thing(eventKey,"${ed_input.text}","${edContent.text}","${tvDate.text}")
            val todoJson = Gson().toJson(todo) //包成 Json()字串符

            editor.putString(eventKey,todoJson).apply()
        }else {
            var i = 0
            //getString 檢視資料，不需要做內容更動
            while (!sharedPreferences.getString("todo-${i}", "").isNullOrEmpty()) {
                i++
            }//若todo-${i}對應的value不為空,則執行i++

            val todo = Thing("todo-${i}","${ed_input.text}","${edContent.text}","${tvDate.text}")
            val todoJson = Gson().toJson(todo) //包成 Json()字串符
            editor.putString("todo-${i}",todoJson).apply()
            Toast.makeText(this, "[${ed_input.text}]儲存成功", Toast.LENGTH_SHORT).show()
        }
    }
}