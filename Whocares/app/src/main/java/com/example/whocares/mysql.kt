package com.example.whocares

import android.util.Log
import java.sql.DriverManager
import java.sql.SQLException


class mysql{
    // 資料庫定義
    var mysql_ip = "10.1.4.17"
    var mysql_port = 3306 // Port 預設為 3306
    var db_name = "project"
    var url = "jdbc:mysql://$mysql_ip:$mysql_port/$db_name"
    var db_user = "root"
    var db_password = "loge717764"
    fun run() {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            Log.v("DB", "加載驅動成功")
        } catch (e: ClassNotFoundException) {
            Log.e("DB", "加載驅動失敗")
            return
        }

        // 連接資料庫
        try {
            val con =
                DriverManager.getConnection(url, db_user, db_password)
            Log.v("DB", "遠端連接成功")
        } catch (e: SQLException) {
            Log.e("DB", "遠端連接失敗")
            Log.e("DB", e.toString())
        }
    }

    fun getdata() : String{
            var data = ""
            try {
                val con =
                    DriverManager.getConnection(url, db_user, db_password)
                val sql = "SELECT * FROM test"
                val st = con.createStatement()
                val rs = st.executeQuery(sql)
                while (rs.next()) {
                    val id = rs.getString("id")
                    val name = rs.getString("name")
                    data += "$id, $name\n"
                }
                st.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            return data
        }
}