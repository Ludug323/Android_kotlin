package com.example.mycpcococococo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_GO.setOnClickListener {
            var intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item : MenuItem): Boolean {
        if(item.itemId == R.id.menu_go){
            val toast = Toast(this)
            toast.view = layoutInflater.inflate(R.layout.toast_cus,null)
            toast.duration = Toast.LENGTH_SHORT
            toast.setGravity(Gravity.TOP,0,0)
            toast.show()
        }
        return super.onOptionsItemSelected(item)
    }


}
