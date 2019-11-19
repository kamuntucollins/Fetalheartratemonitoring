package com.example.fetalheartratemonitoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DataBaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataBaseHelper(this);

        mTextViewRegister = findViewById(R.id.textview_register);
        mTextPassword = findViewById(R.id.edittext_password);
        mTextUsername = findViewById(R.id.edittext_username);
        mButtonLogin = findViewById(R.id.button_login);

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

                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if(res == true){
                    Intent homescreen=new Intent(Login.this,MainActivity.class);
                    startActivity(homescreen);
                    Toast.makeText(Login.this, "logged in successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Login.this, "failed to login", Toast.LENGTH_SHORT).show();
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
