package com.hajni.join.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hajni.join.JoinActivity;
import com.hajni.join.model.User;
import com.hajni.join.util.Util;

public class DatabaseHelper extends SQLiteOpenHelper{
    SQLiteDatabase db = this.getWritableDatabase();

    public DatabaseHelper(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "create table " +
                Util.TABLE_NAME + "(" +
                Util.KEY_ID + " integer not null primary key autoincrement," +
                Util.KEY_USERID + " text not null, " +
                Util.KEY_USERPASSWORD + " text not null)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "drop table " + Util.TABLE_NAME;
        db.execSQL(DROP_TABLE);

        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(Util.KEY_USERID, user.getUserId());
        values.put(Util.KEY_USERPASSWORD, user.getPassWord());
        db.insert(Util.TABLE_NAME, null, values);
        db.close();
        Log.i("myDB", ""+db);
    }

    public void duplicateUser(String id,Context context){
        Cursor cursor = db.rawQuery("SELECT * FROM "+Util.TABLE_NAME+" WHERE Util.KEY_USERID='"+id+"';", null);
        if (cursor.getCount()==1) {
            Toast.makeText(context, "이미 존재하는 아이입니다.", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(context, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
        }
        Log.i("aa",""+cursor);
        cursor.close();
    }

    public void getUser(String id, String pwd){

    }



}
