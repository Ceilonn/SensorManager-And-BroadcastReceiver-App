package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText("");
        IntentFilter intentFilter = new IntentFilter("com.example.sensoractivity2");
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        MyBroadcastReceiver receiver = new MyBroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra("Status", 0);

                if(status == 0) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Toast.makeText(getApplicationContext(),"TELEFON CEPTE, SESSIZE ALINDI!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Toast.makeText(getApplicationContext(), "TELEFON MASADA, SES AÇILDI!!", Toast.LENGTH_SHORT).show();
                }

            }
        };

        registerReceiver(receiver,intentFilter);

    }
}