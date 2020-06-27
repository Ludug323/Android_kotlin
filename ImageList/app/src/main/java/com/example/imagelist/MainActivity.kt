package com.example.imagelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchLayout(1)
        //recycleView.layoutManager = LinearLayoutManager(this)
        //LayoutManager 指給RecycleView以實現滾動列表，為Item的佈局管理工具，控制Item的位置、顯示、大小、滾動等等。
        val Adapter = MyAdapter()
        recycleView.adapter = Adapter
        //Adapter 提供數據和畫面綁定的工具
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_list ->{
                switchLayout(1)
                return true
            }
            R.id.menu_grid ->{
                switchLayout(2)
                return true
            }
            R.id.menu_staggered ->{
                switchLayout(3)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun switchLayout(mode : Int){
        when(mode){
            1 ->{
                recycleView.layoutManager = LinearLayoutManager(this)
            }
            2 ->{
                recycleView.layoutManager = GridLayoutManager(this,2)
            }
            3 ->{
                recycleView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }
}

