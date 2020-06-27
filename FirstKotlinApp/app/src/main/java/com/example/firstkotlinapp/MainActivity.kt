package com.example.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var bucketList = mutableListOf("Go skiing", "Go diving", "Horse riding")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addBtn.setOnClickListener { addBtnOnClick() }
        pickBtn.setOnClickListener {
            pickBtnOnClick()
        }
    }
    private fun addBtnOnClick(){
        val newWish = wishTxt.text.toString()
        bucketList.add(newWish)
        wishTxt.text.clear()
    }

    private fun pickBtnOnClick(){
        var count = bucketList.count()
        if(count > 0){
            val random = (Math.random()*count).toInt()
            toDoTxt.text = bucketList[random]
            bucketList.removeAt(random)
            toast("再來一次")
        } else{
                toDoTxt.text = getString((R.string.noMorePlan))
                toast("沒有計畫了!")
        }
    }
}