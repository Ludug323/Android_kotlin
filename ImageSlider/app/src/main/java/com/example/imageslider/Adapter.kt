package com.example.imageslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.imageview.view.*

class Adapter(val context : Context,val list : List<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.imageview,container,false)

        Glide.with(context).load(list[position]).into(view.imageView)
        //用Glide加載圖片view

        container.addView(view) //將 view 加進 container 裡面
        return view
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any
    }//isViewFromObject 判斷頁面 View 和 instantiateItem 回傳物件是否一致

    override fun getCount(): Int {
        return list.size
    }//getCount() 回傳 ViewPager 的項   目個數

    override fun destroyItem(container: ViewGroup, position: Int, view : Any) {
            container.removeView(view as View)
    }
}