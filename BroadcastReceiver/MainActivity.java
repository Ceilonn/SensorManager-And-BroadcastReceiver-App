package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("com.example.sensoractivity2");
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        registerReceiver(receiver,intentFilter);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if(receiver.getStatus() == 0){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            Toast.makeText(getApplicationContext(),"TELEFON CEPTE, SESSIZE ALINDI!!", Toast.LENGTH_SHORT).show();
        }
        else{
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Toast.makeText(getApplicationContext(), "TELEFON MASADA, SES AÇILDI!!", Toast.LENGTH_SHORT).show();
        }
    }
}