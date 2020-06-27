package com.example.todolist

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter
    private var things = ArrayList<Thing>()
    lateinit var itemDelete : MenuItem //宣告性質為MenuItem的itemDelete

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MyAdapter(things)
        recycleview.layoutManager = LinearLayoutManager(this)
        recycleview.adapter = adapter
        getData()//初始畫面要執行
                                        //object(類)實現interface(接口)
                                        //要求實作interface的抽象方法
                                        //呼叫內部方法必須加override來使用它
        adapter.setToEditClickListener(object : MyAdapter.ItemClickListener{
            override fun toEdit(edit : Thing) {//override將toEdit實行出來
                EditEvent(edit)                //並且執行EditEvent函式
            }
            override fun toSelect(edit: Thing) {//chk_delete 按下去要做的事件
                SelectEvent(edit)
            }
            override fun toShow(edit: Thing) {
                showEvent(edit)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        itemDelete = menu!!.findItem(R.id.item_delete)
        itemDelete.isEnabled = false // 上一行會使itemDelete.isEnabled = true
        //透過findItem獲取具體的item,並且賦值itemDelete,才可從外部取得
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId) {
           R.id.item_add -> {
               startActivityForResult(Intent(this, Main2Activity::class.java), 1)
           }
           R.id.item_delete ->{
               val sharedPreferences = getSharedPreferences("todo1",Activity.MODE_PRIVATE)
               val editor = sharedPreferences.edit()
               val deleteList = things.filter { it.isSelected }
               deleteList.forEach{editor.remove(it.key)}
               editor.apply()
               itemDelete.isEnabled = false
               getData()
           }
       }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            //val todo =data?.extras?.getString("todo")
            //things.add(Thing(todo!!))

            //adapter.notifyDataSetChanged()
            getData() //返回後也要執行畫面更新
        }

    }

    fun getData(){
        things.clear()
        val sharedPreferences = getSharedPreferences("todo1",Activity.MODE_PRIVATE)
        val todolist  = sharedPreferences.all.keys.sorted()
        // for(todo in todolist){
        //    val thing = Thing(sharedPreferences.getString(todo,"")!!)
        //    things.add(thing)
        // }
        /*for(key in todolist) {
            val thing = Thing(key,sharedPreferences.getString(key,"")!!)
            things.add(thing)   //將Thing兩個性質都取得
        }*/
        for(key in todolist){
            val todoJson = sharedPreferences.getString(key,"")
            val todo = Gson().fromJson(todoJson,Thing::class.java)
            things.add(todo)
        }
        adapter.notifyDataSetChanged()
    }
            //用於切換頁面並且夾帶資料(使用Gson)
    fun EditEvent(Event : Thing){
        val intent = Intent(this,Main2Activity::class.java)
        val eventString = Gson().toJson(Event)//將資料用程序化轉成Json字串
        intent.putExtra("event",eventString) //putExtra(key,value)
        startActivityForResult(intent,1)
    }

    fun SelectEvent(event : Thing){ //方法SelectEvent為改變垃圾桶enable狀態
        event.isSelected = !event.isSelected //使用者可能點了之後不想刪除,會再點一次取消勾選
                                             //所以利用 !運算子來反向Boolean值
        Toast.makeText(this,"< ${event.title} >被選取",Toast.LENGTH_SHORT).show()

                //當資料的 CheckBox 被點擊時，垃圾桶狀態 itemDelete.isEnabled,
                //因為沒有限定是哪一筆資料的 CheckBox 被點擊,
                //所以使用陣列方法 list.find()來檢查
        itemDelete.isEnabled = things.find { it.isSelected }?.isSelected?:false
        //當這個資料狀態 .isSelected時,垃圾桶狀態就是 .isEnabled

        //如果?:左側表達式非空,elvis操作符就返回其左側表達式,否則返回右側表達式,
        // 注意僅當左側為空時,才會對右側表達式求值
    }

    fun showEvent(event : Thing){
        AlertDialog.Builder(this)
            .setTitle("待辦事項 : ${event.title}")
            .setMessage("內容 : ${event.content}\n日期 : ${event.date}")
            .setPositiveButton("確認"){ dialog, which ->
                dialog.cancel()
            }.show()
    }
}
