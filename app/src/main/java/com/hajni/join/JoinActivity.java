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
    Button duplicateBtn;
    DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        dh = new DatabaseHelper(JoinActivity.this);

        initView();
        onClick();

    }

    private void initView() {
        joinId = findViewById(R.id.joinId);
        joinPass = findViewById(R.id.joinPass);
        joinPassCheck = findViewById(R.id.joinPasscheck);
        joinBtn = findViewById(R.id.joinBtn);
        duplicateBtn = findViewById(R.id.duplicateBtn);
    }

    private void onClick() {

        duplicateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = joinId.getText().toString().trim();
                dh.checkedUser(id,JoinActivity.this);
                // 중복확인이 아닌 복사하는 느낌이 듦 수정 확인하는 느낌이 들면 과거형을 쓰세여
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = joinId.getText().toString().trim();
                String passWord = joinPass.getText().toString().trim();
                String passWordCheck = joinPassCheck.getText().toString().trim();
                //변수상으로 비밀번호를 들고있으면안돼여


                if (id.isEmpty() || passWord.isEmpty()) {
                    Toast.makeText(JoinActivity.this, "입력해", Toast.LENGTH_SHORT).show();
                } else if (!passWord.equals(passWordCheck)) {
                    Toast.makeText(JoinActivity.this, "비밀번호확", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setUserId(id);
                    user.setPassWord(passWord);
                    boolean joinCheck = dh.addUser(user,JoinActivity.this);
                    if (joinCheck){
                        finish();
                    }else {
                        return;
                    }
                    Log.i("zzz", "입력완");
                }
                Log.i("zzz",""+id +" "+passWord);
            }
        });

    }

}