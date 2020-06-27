package com.example.order

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_order.setOnClickListener {
            val intent = Intent(this,Main2Activity::class.java)
            if(ed_name.text.toString().isBlank()){//isBlank() 判斷字串是否為空或是為空白組成
                val toast = Toast(this)
                toast.view = layoutInflater.inflate(R.layout.custom_toast,null)
                toast.duration = Toast.LENGTH_SHORT
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
            }else {
                val name = ed_name.text.toString()      //將ed_name文字轉成String字串
                intent.putExtra("nameKey", name)   //putExtra("Key", value)
                startActivityForResult(intent, 1)//傳遞intent值
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let {
            if(requestCode==1 && resultCode == Activity.RESULT_OK)
                tv_other.text = "飲料 : ${it.getString("drinks")}\n\n甜度 : ${it.getString("sugar")}\n\n冰度 :　${it.getString("ice")}"
        }
    }
}
