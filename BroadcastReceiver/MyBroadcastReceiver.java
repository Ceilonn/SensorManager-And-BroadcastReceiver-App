package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private int status;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        status = intent.getIntExtra("Status", 0);

        if(status == 0) {

        }

    }
}