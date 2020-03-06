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

public class Register extends AppCompatActivity {
    DataBaseHelper db;
    EditText mtext1;
    EditText mtext2;
    EditText cmtext;
    TextView mtext,tvpass;
    Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DataBaseHelper(this);
        tvpass=findViewById(R.id.tv_4);

        mtext1 = findViewById(R.id.edittext_username);
        mtext2 = findViewById(R.id.edittext_password);
        cmtext = findViewById(R.id.edittext_confirm);
        but1 = findViewById(R.id.register);
        mtext = findViewById(R.id.textview_login);

       mtext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass=mtext2.getText().toString();
                validatePassword(pass);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mtext.setPaintFlags(mtext.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        mtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Register.this, Login.class);
                startActivity(myintent);
            }
        });

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mtext1.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "field is empty!", Toast.LENGTH_SHORT).show();
                    mtext1.requestFocus();
                }else if (mtext2.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "field is empty!", Toast.LENGTH_SHORT).show();
                    mtext2.requestFocus();
                }else if (cmtext.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "field is empty", Toast.LENGTH_SHORT).show();
                    cmtext.requestFocus();
                }else {
                    String user = mtext1.getText().toString().trim();
                    String pwd = mtext2.getText().toString().trim();
                    String cpwd = cmtext.getText().toString().trim();

                    if ((pwd.equals(cpwd))) {


                        long val = db.addUser(user, pwd);

                        if (val > 0) {
                            Toast.makeText(Register.this, "you have registered", Toast.LENGTH_LONG).show();
                            Intent moToLogin = new Intent(Register.this, Login.class);
                            startActivity(moToLogin);

                        } else {
                            Toast.makeText(Register.this, "registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void validatePassword(String password ){
        if(password.length()< 8){
            tvpass.setTextColor(Color.RED);
        }else{
            tvpass.setTextColor(Color.GREEN);
        }

    }
}
