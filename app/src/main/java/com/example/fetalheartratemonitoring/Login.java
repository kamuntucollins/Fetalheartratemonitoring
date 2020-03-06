package com.example.fetalheartratemonitoring;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister,tvpass;
    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvpass=findViewById(R.id.tv_4);

        db = new DataBaseHelper(this);

        mTextViewRegister = findViewById(R.id.textview_register);
        mTextPassword = findViewById(R.id.edittext_password);
        mTextUsername = findViewById(R.id.edittext_username);
        mButtonLogin = findViewById(R.id.button_login);

        mTextViewRegister.setPaintFlags(mTextViewRegister.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);


            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTextUsername.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "username is missing!", Toast.LENGTH_SHORT).show();
                   mTextUsername.requestFocus();
                }else if (mTextPassword.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "password is missing please!", Toast.LENGTH_SHORT).show();
                    mTextPassword.requestFocus();
                }else {

                    String user = mTextUsername.getText().toString().trim();
                    String pwd = mTextPassword.getText().toString().trim();
                    Boolean res = db.checkUser(user, pwd);
                    if (res == true) {
                        Intent homescreen = new Intent(Login.this, Results.class);
                        startActivity(homescreen);
                        Toast.makeText(Login.this, "logged in successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login.this, "failed to login", Toast.LENGTH_SHORT).show();
                    }
                }
                reset();
            }
        });
    }
    public void reset(){
        mTextUsername.setText("");
        mTextPassword.setText("");
    }

}
