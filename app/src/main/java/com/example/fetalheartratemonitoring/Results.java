package com.example.fetalheartratemonitoring;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;
    Button button;
    Button onBtn,offBtn;
    TextView text;
    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        onBtn=findViewById(R.id.start);
        offBtn=findViewById(R.id.stop);
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();
        text=findViewById(R.id.heading);
       text.setPaintFlags(text.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBlueAdapter.isEnabled()) {
                    mBlueAdapter.disable();
                    Toast.makeText(Results.this, "turning  bluetooth off", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Results.this, " bluetooth is already on", Toast.LENGTH_SHORT).show();
                }

            }
        });
        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()) {
                    Toast.makeText(Results.this, "turning on bluetooth...", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                 startActivityForResult(intent, REQUEST_ENABLE_BT);
                }else{
                    Toast.makeText(Results.this, " bluetooth is already on", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();



        button=findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(Results.this,Savings.class);
                startActivity(myintent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.majormenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int a = item.getItemId();
        switch (a) {

            case R.id.more:
                Intent myintent1 = new Intent(Results.this, MainActivity.class);
                startActivity(myintent1);
                break;

            case R.id.Visit:
                Intent myintent3 = new Intent(Results.this, Visitweb.class);
                startActivity(myintent3);
                break;
            case R.id.logout:
                exitMethod();
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
    public void exitMethod(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(Results.this);
        builder.setMessage("are you sure you want to Logout");
        builder.setCancelable(true);
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myintent=new Intent(Results.this,Login.class);
                startActivity(myintent);
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }



}
