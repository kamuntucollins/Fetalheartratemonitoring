package com.example.fetalheartratemonitoring;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Savings extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;
    ListView listView;
    DataBaseHelper2 dataBaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);
          textView=findViewById(R.id.title);
        editText=findViewById(R.id.et_text);
        button=findViewById(R.id.save);
   listView=findViewById(R.id.list_view);
        textView.setPaintFlags(textView.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
   //initialising databasehelper
        dataBaseHelper = new DataBaseHelper2(Savings.this);
        //adding vslues to the arraylist
        arrayList = dataBaseHelper.getAllText();
        //initialise arrayadapter
         arrayAdapter = new ArrayAdapter(Savings.this,android.R.layout.simple_list_item_1,arrayList);
         //set arrayadapter to list view
        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get text from edittext
                String text=editText.getText().toString();
                if(!text.isEmpty()){
                    if(dataBaseHelper.addText(text)){
                      editText.setText("");
                      //display message
                        Toast.makeText(Savings.this, "saved successfully...", Toast.LENGTH_SHORT).show();
                        //cleae arraylist
                        arrayList.clear();
                        arrayList.addAll(dataBaseHelper.getAllText());
                        //refresh list view data
                        arrayAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();
                    }
                }
            }
        });
    }
}
