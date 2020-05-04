package com.example.programadeestudo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarme cadastrado", Toast.LENGTH_LONG).show();
        MainActivity mainActivity  = (MainActivity) context;
        mainActivity.gerar();
    }
}
