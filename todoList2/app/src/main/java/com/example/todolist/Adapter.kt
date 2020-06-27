package com.example.todolist

data class Thing(val key : String,//原本的 Key值
                 var title: String,
                 var content : String, //新增待辦事項-內容
                 var date : String,    //新增待辦事項-日期
                 var isSelected : Boolean = false)//原本的 isSelected 布林值
                                                  //判斷 CheckBox是否選取