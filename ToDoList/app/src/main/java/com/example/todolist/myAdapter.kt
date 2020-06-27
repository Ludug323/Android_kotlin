package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class myAdapter(private val things : ArrayList<Thing>) : RecyclerView.Adapter<myAdapter.ViewHolder>(){

    inner class ViewHolder(v : View) :RecyclerView.ViewHolder(v){
        val tv_todo = v.findViewById<TextView>(R.id.tv_todo)

        fun bind(todo:String){
            tv_todo.setText(todo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_myadapter,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = things.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(things[position].todo)
        //把 function bind 指給 holder
    }
}