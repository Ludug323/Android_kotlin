package com.example.imagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View,var viewType: Int) : RecyclerView.ViewHolder(itemView){
        //(itemView(參數名稱):(繼承) View)
        fun Bind(item : item){
                var image : ImageView? = null
            var text : TextView? = null
            when(viewType){
                1 ->{
                    image = itemView.findViewById(R.id.img_demo)
                    text = itemView.findViewById(R.id.text_demo)
                }else -> {
                    image = itemView.findViewById(R.id.dif_img)
                    text = itemView.findViewById(R.id.tv_portrait)
                }
            }
            image?.setImageResource(item.image)
            text?.setText(item.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            1 -> {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_landscape, parent, false)
                ViewHolder(view, viewType)
            }else ->{
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_portrait,parent,false)
                ViewHolder(view,viewType)
            }
        }
    }//創造一個新的 RecyclerView.ViewHolder，並初始化 RecyclerView 畫面佈局

    override fun getItemCount(): Int  = itemList.count()
    //取得Adpater持有的資料總數

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Bind(itemList[position])
    }
    //更新 RecyclerView.ViewHolder 內容

    override fun getItemViewType(position: Int): Int {
        return if(itemList[position].isLandscape) 1 else 2
    }//當item是isLandscape指定使用view1，反之使用view2
    //1,2 指的是view類型
}