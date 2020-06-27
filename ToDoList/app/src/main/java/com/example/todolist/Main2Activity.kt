package com.example.todolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btn_accept.setOnClickListener {
            if (et_input.text.isBlank()) {
                Toast.makeText(this, "請輸入代辦事項", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putString("todo","${et_input.text}")
                val intent = Intent()
                intent.putExtras(bundle)
                setResult(Activity.RESULT_OK,intent)
                finish()

                 Toast.makeText(this,"${et_input.text}儲存成功",Toast.LENGTH_SHORT).show()
            }
        }
        btn_cannel.setOnClickListener {
            finish()
        }
    }

}

