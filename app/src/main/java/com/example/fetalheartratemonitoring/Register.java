package com.example.fetalheartratemonitoring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    TextView mtext;
    Button but1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DataBaseHelper(this);

        mtext1 = findViewById(R.id.edittext_username);
        mtext2 = findViewById(R.id.edittext_password);
        cmtext = findViewById(R.id.edittext_confirm);
        but1 = findViewById(R.id.register);
        mtext = findViewById(R.id.textview_login);

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
                String user = mtext1.getText().toString().trim();
                String pwd = mtext2.getText().toString().trim();
                String cpwd = cmtext.getText().toString().trim();
                if ((pwd.equals(cpwd))) {

                    long val = db.addUser(user, pwd);

                    if (val>0){
                        Toast.makeText(Register.this, "you have registered", Toast.LENGTH_LONG).show();
                        Intent moToLogin = new Intent(Register.this, Login.class);
                        startActivity(moToLogin);

                    }else{
                        Toast.makeText(Register.this, "registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "password not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
