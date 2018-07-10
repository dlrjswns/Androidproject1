package com.example.leeky.myschoolalarm;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import android.util.Log;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity {
public static final String TAG="MainActivity";
    TextView textview1;
    TextView textview2;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview2 = (TextView)findViewById(R.id.textview2);
        textview1 = (TextView)findViewById(R.id.textview1);
        Button requestbutton=(Button)findViewById(R.id.button);
        requestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      RequestThread thread=new RequestThread();
                      thread.start();
            }
        });
        handler=new Handler();
    }
    class RequestThread extends Thread{
        public void run(){
            request();
        }
    }
    private void request(){
        try {
            Socket socket=new Socket("172.30.1.55", 5000);
            appendText("client started:172.30.1.55, 5001");

            ObjectOutputStream outstream=new ObjectOutputStream(socket.getOutputStream());
            outstream.writeUTF("Hello");
            outstream.flush();

            appendText("Hello sent.");


            ObjectInputStream instream=new ObjectInputStream(socket.getInputStream());
            String inStr=instream.readUTF();

            appendText("inStr from client:"+inStr);

            socket.close();


        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void appendText(String msg){
        final String inMsg=msg;
        handler.post(new Runnable(){
            public void run(){
                textview1.append(inMsg + "\n");
            }
        });

    }
    }


