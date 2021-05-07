package com.hajni.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hajni.join.data.DatabaseHelper;
import com.hajni.join.model.User;

public class JoinActivity extends AppCompatActivity {

    EditText joinId;
    EditText joinPass;
    EditText joinPassCheck;
    Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        viewInit();
        onClick();

    }

    private void viewInit() {
        joinId = findViewById(R.id.joinId);
        joinPass = findViewById(R.id.joinPass);
        joinPassCheck = findViewById(R.id.joinPasscheck);
        joinBtn = findViewById(R.id.joinBtn);

    }

    private void onClick() {

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = joinId.getText().toString().trim();
                String passWord = joinPass.getText().toString().trim();
                String passWordCheck = joinPassCheck.getText().toString().trim();
                DatabaseHelper dh = new DatabaseHelper(JoinActivity.this);


                if (id.isEmpty() || passWord.isEmpty()) {
                    Toast.makeText(JoinActivity.this, "입력해", Toast.LENGTH_SHORT).show();
                } else if (!passWord.equals(passWordCheck)) {
                    Toast.makeText(JoinActivity.this, "비밀번호확", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User();
                    user.setUserId(id);
                    user.setPassWord(passWord);
                    dh.addUser(user);
                    Log.i("zzz", "입력완");
                }
                Log.i("zzz", "" + id);
            }
        });

    }

}