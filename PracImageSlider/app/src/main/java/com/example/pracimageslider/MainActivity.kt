package com.example.pracimageslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val list = mutableListOf<Int>()
    val mlistener = object : ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {
        }
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            val page_position = position + 1
            textview.setText("$page_position / ${list.size}")
        }

        override fun onPageSelected(position: Int) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addToList()
        viewpager.adapter = Adapter(this,list)
        viewpager.addOnPageChangeListener(mlistener)

    }

    fun addToList(){
        for (i in 1..7){
            val name_id = "cat_$i"
            val id = resources.getIdentifier(name_id,"drawable",packageName)
            list.add(id)
        }
    }
}
