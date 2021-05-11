package com.hajni.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hajni.join.data.DatabaseHelper;

import org.mindrot.jbcrypt.BCrypt;

public class LoginActivity extends AppCompatActivity {

    EditText editTextLoginId;
    EditText editTextLoginPass;
    Button loginBtn;
    Button joinBtn;
    DatabaseHelper dh;//dh줄이는거 고민해보세

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dh = new DatabaseHelper(LoginActivity.this);
        initView();
        onClick();
    }

    private void initView() {
        editTextLoginId = findViewById(R.id.editTextLoginId);
        editTextLoginPass = findViewById(R.id.editTextLoginPass);
        loginBtn = findViewById(R.id.loginBtn);
        joinBtn = findViewById(R.id.joinBtn);
    }
    private void onClick() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextLoginId.getText().toString().trim();
                String passWord = editTextLoginPass.getText().toString().trim();
                dh.login(id,passWord,LoginActivity.this);
            }
        });
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(i);
            }
        });

    }
}