package com.hajni.join.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.hajni.join.HomeActivity;
import com.hajni.join.model.User;
import com.hajni.join.util.DbInformation;

import org.mindrot.jbcrypt.BCrypt;

public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getWritableDatabase();

    public DatabaseHelper(Context context) {
        super(context, DbInformation.DATABASE_NAME, null, DbInformation.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "create table " +
                DbInformation.TABLE_NAME + "(" +
                DbInformation.KEY_ID + " integer not null primary key autoincrement," +
                DbInformation.KEY_USERID + " text not null, " +
                DbInformation.KEY_USERPASSWORD + " text not null)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "drop table " + DbInformation.TABLE_NAME;
        db.execSQL(DROP_TABLE);

        onCreate(db);
    }

    public boolean addUser(User user, Context context) {
        boolean check = checkedUser(user.getUserId(), context);
        if (check) {
            return false;
        } else {
            ContentValues values = new ContentValues();
            values.put(DbInformation.KEY_USERID, user.getUserId());
            values.put(DbInformation.KEY_USERPASSWORD, hashPassword(user.getPassWord()));
            db.insert(DbInformation.TABLE_NAME, null, values);
            db.close();
            Log.i("myDB", "" + db);
            return true;
        }
    }

    public boolean checkedUser(String id, Context context) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbInformation.TABLE_NAME + " WHERE userid='" + id + "';", null);
        boolean isSetUser = cursor.getCount() > 0;
        cursor.close();
        if (isSetUser) {
            Toast.makeText(context, "이미 존재하는 아이입니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
        }
        return isSetUser;
    }

    public void login(String id, String pwd, Context context) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbInformation.TABLE_NAME + " WHERE userid='" + id + "' ;", null);
//        String a = cursor.getColumnName(1);

        String hashPwd = "";
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                hashPwd = cursor.getString(2);
                break;
            }
            if (checkPass(pwd, hashPwd)) {
                Intent i = new Intent(context, HomeActivity.class);
                context.startActivity(i);
                return;
                //이스터에그에서 람다로 사용했던방식 sql인데
            }
        }

        Toast.makeText(context, "아이디 혹은 비밀번호확인", Toast.LENGTH_SHORT).show();
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private boolean checkPass(String plainPassword, String hasedPassword) {
        return BCrypt.checkpw(plainPassword, hasedPassword);
    }
}