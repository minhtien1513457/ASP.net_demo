package com.example.student.testc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText send;
    TextView recieve;
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = findViewById(R.id.send);
        recieve = findViewById(R.id.recieve);
        btn = findViewById(R.id.btn);
        //lien ket voi server
        try {
            mSocket= IO.socket("http://192.168.1.105:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        mSocket.on("server-send-data",onRecieveData);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg_send = send.getText().toString();
                recieve.setText(msg_send);
                //Message message = new Message("1",msg_send);
                //Toast.makeText(MainActivity.this, message.getId()+" "+message.getData() , Toast.LENGTH_SHORT).show();
                mSocket.emit("client-send-data",msg_send);
            }
            });
        }
        private Emitter.Listener onRecieveData=new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject object=(JSONObject) args[0];
                        try {
                            String ten=object.getString("noidung");
                            Toast.makeText(MainActivity.this,ten, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
}

