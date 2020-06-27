package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val things :ArrayList<Thing>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    //修飾詞private只可在此類別中　更改且可見
    //宣告itemClickListener來繼承 interface接口
    private var itemClickListener : ItemClickListener? = null

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tv_todo = v.findViewById<TextView>(R.id.tv_todo)
        val img_edit = v.findViewById<ImageView>(R.id.img_edit)
        val chk_delete = v.findViewById<CheckBox>(R.id.chk_delete)
            //綁定畫面元件
        fun bind(thing : Thing) {
            tv_todo.setText(thing.title)
                itemView.setOnClickListener{
                    itemClickListener?.toShow(thing)
                }
            img_edit.setOnClickListener {//img_edit 按鍵監聽器
                itemClickListener?.toEdit(thing)
            }
            chk_delete.isChecked = thing.isSelected //畫面狀態 = 資料狀態
            chk_delete.setOnClickListener{//確認刪除　按鍵監聽器
                itemClickListener?.toSelect(thing)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_myadapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = things.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(things[position])
    }
    interface ItemClickListener{//自定義一個ItemClickListener介面
        fun toEdit(edit : Thing)  //抽象　編輯方法
        fun toSelect(edit : Thing)//給chk_delete的抽象方法
        fun toShow(edit : Thing)
    }
    fun setToEditClickListener(listener: ItemClickListener){//用以呼叫的
        itemClickListener = listener
    }
   //set方法,可以供Activity或Fragment呼叫

}