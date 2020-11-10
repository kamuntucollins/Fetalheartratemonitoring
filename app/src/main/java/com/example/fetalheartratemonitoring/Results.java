package com.example.fetalheartratemonitoring;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class Results extends AppCompatActivity {
    BluetoothAdapter bluetoothadapter;
    BluetoothDevice[]  btarray;
    static  final int STATE_LISTENING = 1;
    static  final int STATE_CONNECTING = 2;
    static  final int STATE_CONNECTED = 3;
    static  final int STATE_CONNECTION_FAILED = 4;
    static  final int STATE_MESSAGE_RECEIVED = 5;

    int REQUEST_ENABLE_BLUETOOTH = 1;

    Button sbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        if(!bluetoothadapter.isEnabled()){
            Intent enableintent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableintent,REQUEST_ENABLE_BLUETOOTH);
        }else{
//            BTe bt =  new BTe();
//            bt.start();
            getDevices ();
        }


        setContentView(R.layout.activity_results);
        sbutton=findViewById(R.id.save);
        sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results.this,Savings.class);
                startActivity(intent);
            }
        });


    }
    private void getDevices (){

        Set<BluetoothDevice> but =  bluetoothadapter.getBondedDevices();
        String [] strings = new String[but.size()];
        int index = 0;

        if(but.size()>0){
            for (BluetoothDevice device :but){
                /// Log.w("we", "getDevices: "+"we are in business" );
                if(device.getName().equals("mcuhq.com")){
                    ///Log.d();
                    Log.w("we", "getDevices: "+"we are in business2" );
                    BTe bt=new BTe(device);
                    bt.start();
                }
            }

//            ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,strings);
//            listView.setAdapter(arrayAdapter);
        }
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            TextView xt = findViewById(R.id.view2);
            TextView xt5 = findViewById(R.id.view);


            switch (msg.what){
                case STATE_LISTENING:
                    //handle this state
                    ////  xt.setText("listening...");
                    xt.setText("Waiting for connection");
                    break;
                case STATE_CONNECTING:
                    /// xt.setText("connecting...");
                    xt.setText("Waiting for connection");
                    break;
                case STATE_CONNECTED:
                    /// xt.setText("connected...");
                    break;
                case STATE_CONNECTION_FAILED:
                    //handle this state
                    break;
                case STATE_MESSAGE_RECEIVED:
                    //handle this state
                    byte[] readBuff =(byte[]) msg.obj;
                    String tempMsg = new String(readBuff,0,msg.arg1);
                    /////Log.w("hohoh0", "handleMessage: "+tempMsg );

                    if(tempMsg.contains("#") ){
                        String[] parts = tempMsg.split("#");
                        int a =Integer.parseInt(parts[0]);
                        int b=Integer.parseInt(parts[1]);

                        String  y =  Integer.toString(a);

                        String  z =  Integer.toString(b);

                        if(a>100){
                             y= "non";
                        }

                        if(b>170){
                            z="non";
                        }


                        if(y.length() > 1 && z.length()>2 ){



                            xt.setText("BPM "+ y );


                            xt5.setText("Baby "+z);

                        }



//                if(parts[1].contains("@")){
//                    xt5.setText("Baby "+Integer.parseInt(parts[1]));
//                }else {
//                    xt5.setText("Baby "+Integer.parseInt(parts[1]));
//                }


                    }else {


                    }


                    break;
            }
            return true;
        }
    });

    public class BTe extends Thread{
        private BluetoothDevice device;
        private BluetoothSocket socket;

        public BTe(BluetoothDevice device1){
            device =device1;
            try {
                socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run(){
            Log.w("we", "run: connecting to "+device);

            try {
                socket.connect();
                Message message = Message.obtain();
                message.what = STATE_CONNECTED;
                handler.sendMessage(message);
                SendRecive sn =  new SendRecive(socket);
                sn.readData();
            } catch (IOException e) {
                e.printStackTrace();
                Message message = Message.obtain();
                message.what = STATE_CONNECTION_FAILED;
                handler.sendMessage(message);
            }
        }

    }


    private class SendRecive{
        private final BluetoothSocket socket;
        private final OutputStream output;
        private final InputStream input;

        public SendRecive (BluetoothSocket BTsocket){
            socket = BTsocket;
            OutputStream tempOut =  null;
            InputStream tempIn = null;
            try {
                tempIn = socket.getInputStream();
                tempOut = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            output = tempOut;
            input = tempIn;
        }


        public void readData() {
            Log.w("we", "readData: trying to read data");
            byte [] buffer = new byte[2048];
            int bytes;


            while(true){
                try{
                    bytes= input.read(buffer);

                    handler.obtainMessage(STATE_MESSAGE_RECEIVED,bytes,-1,buffer).sendToTarget();
                    String msx =   new String(buffer,0,bytes);
                    Log.w("we", "run: data: "+msx );

                }catch (IOException e){

                    Log.w("we", "readData: no data found ");

                }
            }


        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.majormenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int a=item.getItemId();
        switch (a){
            case R.id.savings:
                Intent myintent1 =new Intent(Results.this,Savings.class);
                startActivity(myintent1);
                break;
            case R.id.Visit:
                Intent myintent2 =new Intent(Results.this,Visitweb.class);
                startActivity(myintent2);
                break;
            case R.id.logout:
                Intent myintent3 =new Intent(Results.this,Login.class);
                startActivity(myintent3);
                break;

            default:
                return false;

        }

        return super.onOptionsItemSelected(item);
    }
   }