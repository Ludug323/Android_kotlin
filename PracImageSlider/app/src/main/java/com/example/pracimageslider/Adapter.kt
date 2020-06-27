package com.example.pracimageslider


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.imageview.view.*


class Adapter (val context : Context,val list : List<Int>) : PagerAdapter() {
    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view == any
    }//isViewFromObject 判斷頁面 View 和 instantiateItem 回傳物件是否一致

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.imageview,container,false)

        Glide.with(context).load(list[position]).into(view.imageView)
        container.addView(view)
        return view
    }
    override fun getCount(): Int {
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

}