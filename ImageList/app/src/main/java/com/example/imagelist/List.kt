package com.example.imagelist


data class  item(val image :Int, val text : String,val isLandscape : Boolean = true)//建立data source

val itemList = mutableListOf(
    //宣告一個mutableList裡面放好item們
    item(R.drawable.a,"First item"),
    item(R.drawable.b,"2 item",false),
    item(R.drawable.c,"3 item"),
    item(R.drawable.d,"4 item",false),
    item(R.drawable.e,"5 item"),
    item(R.drawable.f,"6 item",false),
    item(R.drawable.g,"7 item")
)