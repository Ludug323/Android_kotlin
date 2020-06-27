package com.example.order

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

    private var iceVolume : String = ""
    private var sugarVolume : String = "I dont know.."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra("nameKey")
        tv_name.text  = "訂購人姓名 : $name "

        RadioGroup_ice.setOnCheckedChangeListener{_, i->
            iceVolume = RadioGroup_ice.findViewById<RadioButton>(i).text.toString()
            Toast.makeText(this,iceVolume,Toast.LENGTH_SHORT).show()
        }       //直接綁RadioButton裡面的id，不用一個一個指定


        RadioGroup_sugar.setOnCheckedChangeListener{_, i->
            sugarVolume = RadioGroup_sugar.findViewById<RadioButton>(i).text.toString()
            Toast.makeText(this,sugarVolume,Toast.LENGTH_SHORT).show()
        }       //直接綁RadioButton裡面的id，不用一個一個指定


        bt_output.setOnClickListener {
            var drinks = ed_drinks.text.toString()
            if(ed_drinks.text.isBlank()){
                Toast.makeText(this,"請輸入飲料名稱!!",Toast.LENGTH_SHORT).show()
                //設if條件，提醒一定要輸入飲料名稱
                //Toast用法之後會介紹
            }else {
                    alertDL(drinks)
            }
        }
    }

    fun back(drinks: String?) {
        //val sugar = RadioGroup_sugar.findViewById<RadioButton>(
        //    RadioGroup_sugar.checkedRadioButtonId
        //).text

        //val ice = RadioGroup_ice.findViewById<RadioButton>(
        //   RadioGroup_ice.checkedRadioButtonId
        //).text
        //RadioGroup裡面的RadioButton
        //不知道是哪個button被選取，用checkedRadioButtonId來辨識

        val bundle = Bundle()
        bundle.putString("drinks",drinks)
        bundle.putString("sugar",sugarVolume)
        bundle.putString("ice",iceVolume)
                //putString("Key", value)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    fun alertDL(drinks : String){
        AlertDialog.Builder(this)
                            //AlertDialog.Builder (context: Context)
                            //參數放要傳入的 MainActivity Context
            .setTitle("確認餐點")
            .setMessage("飲料: ${drinks}\n" +
                    "甜度 : ${sugarVolume}\n"+
                    "冰塊 : ${iceVolume}")
            .setNegativeButton("取消",null)
            .setPositiveButton("確認"){_,_ ->back(drinks)}
            .create()
            .show()
    }
}

