package com.example.whocares

import android.R
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_updata.setOnClickListener {
            Toast.makeText(this,"已刷新資料",Toast.LENGTH_SHORT).show()
        }
    }
    fun aletbut(){
        val tView : String? = null
        AlertDialog.Builder(this)
            .setTitle("")
            .setMessage("${tView}")
            .setPositiveButton("確認"){
                    dialog,whith -> dialog.cancel()
            }
            .create()
            .show()
    }
}

