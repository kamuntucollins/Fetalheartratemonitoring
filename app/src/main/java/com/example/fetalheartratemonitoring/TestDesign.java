package com.example.fetalheartratemonitoring;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TestDesign extends AppCompatActivity {
    private TextInputLayout design;
    private TextInputLayout design2;
    private TextInputLayout design3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_design);
        design=findViewById(R.id.design);
        design2=findViewById(R.id.design2);
        design3=findViewById(R.id.design3);

    }
    private  boolean validateEmail(){
        String emailInput= design.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            design.setError("field cant be empty");
            return false;
        }else{
            design.setError(null);
            return true;
        }
    }
    private
     boolean validateUsername(){
        String usernameInput= design2.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            design2.setError("field cant be empty");
            return  false;

        } else if (usernameInput.length()>10) {
            design2.setError("username tooo long");
            return  false;
        }else{
            design2.setError(null);
            return  true;
        }
    }

    private  boolean validatepassword(){
        String passwordInput= design3.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            design3.setError("field cant be empty");
            return false;
        }else{
            design3.setError(null);
            return true;
        }
    }
    public void confirm(View v){
        if (!validateEmail() | !validateUsername() | !validatepassword()) {
            return;
        }
        String input= "Email:"+design.getEditText().getText().toString();
        input+="\n";
        input+="username:"+design2.getEditText().getText().toString();
        input+="\n";
        input+="password"+design3.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}
