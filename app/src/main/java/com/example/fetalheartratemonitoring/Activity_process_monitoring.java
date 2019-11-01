package com.example.fetalheartratemonitoring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Activity_process_monitoring extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_monitoring);

        imageView=findViewById(R.id.fet);
        imageView=findViewById(R.id.mat);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }
    public  void  openDialog(){
        Dialog dialog= new Dialog();
        dialog.show(getSupportFragmentManager(),"example dialog");
    }
}


