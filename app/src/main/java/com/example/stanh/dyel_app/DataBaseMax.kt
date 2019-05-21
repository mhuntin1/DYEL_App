package com.example.stanh.dyel_app


import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import java.security.AccessControlContext

/**
 * Created by Mark on 2/27/2018.
 */


val Database_Name = "ColorDB"
val Table_Name = "Max"
val Col_ID = "id"
val Col_Bench = "Bench"
val Col_Curl = "Curl"
val Col_Shoulder = "Shoulder"

class DataBaseMax (var context: Context) : SQLiteOpenHelper(context, Database_Name, null, 1) {
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + Table_Name + "( " +
                Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Bench + " INTEGER," +
                Col_Curl + " INTEGER, " +
                Col_Shoulder + " INTEGER)";

        db?.execSQL(createTable)
    }

    fun insertData(txt1: Int, txt2: Int, txt3: Int) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(Col_Bench, txt1)
        cv.put(Col_Curl, txt2)
        cv.put(Col_Shoulder, txt3)
        var result = db.insert(Table_Name, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Did not save", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Your Gainz Have Been Recorded!", Toast.LENGTH_SHORT).show()
    }

    fun readData(): MutableList<User> {
        var list: MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + Table_Name
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var color = User()
                color.bench = result.getString(result.getColumnIndex(Col_Bench)).toInt()
                color.curl = result.getString(result.getColumnIndex(Col_Curl)).toInt()
                color.shoulder = result.getString(result.getColumnIndex(Col_Shoulder)).toInt()
                list.add(color)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun clearTable() {
        val db = this.writableDatabase
        db.delete(Table_Name, null, null)
        //db.execSQL("delete from " + Table_Name)
        db.close()
    }

}





/*import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.util.ArrayList

val DataBase_Name = "MyDB"
val Table_Name = "Maxes"
val Col_ID = "id"
val Col_Bench = "Bench"
val Col_Curl = "Curl"
val Col_Shoulder = "Shoulder_Press"


class DataBaseMax(var context: Context) : SQLiteOpenHelper(context, DataBase_Name, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + Table_Name +
                "( " +
                Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Col_Bench + " INTEGER," +
                Col_Curl + " INTEGER, " +
                Col_Shoulder + " INTEGER)";

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(user: User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(Col_Bench, user.bench)
        cv.put(Col_Curl, user.curl)
        cv.put(Col_Shoulder, user.shoulder)
        var result = db.insert(Table_Name, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "failed to save", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Congrats", Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun readData() : MutableList<User>{
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + Table_Name
        val result = db.rawQuery(query,null)
        if (result.moveToFirst()){
            do {
                var user = User()
                user.id = result.getString(result.getColumnIndex(Col_ID)).toInt()
                user.bench = result.getString(result.getColumnIndex(Col_Bench)).toInt()
                user.curl = result.getString(result.getColumnIndex(Col_Curl)).toInt()
                user.shoulder = result.getString(result.getColumnIndex(Col_Shoulder)).toInt()
                list.add(user)


            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

}


class DatabaseMax (context: Context) : SQLiteOpenHelper (context, DataBase_Name, factory, version){

    companion object {

        internal val factory = null
        internal val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE " + Table_Name + " (" +
                Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Col_Bench + " INTEGER, " +
                Col_Curl + " INTEGER, " +
                Col_Shoulder + " INTEGER)"
        )
    }

    fun insertData

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}*/



