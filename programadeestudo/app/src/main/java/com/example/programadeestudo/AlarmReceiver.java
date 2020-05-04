package com.example.programadeestudo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.programadeestudo.model.Matter;

import java.io.Serializable;

public class AlarmReceiver extends BroadcastReceiver{
    private int notificationId = 1;
    private static final String CHANNELID = "2";
    public static final int REQUEST_CODE = 123456;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hora de estudar", Toast.LENGTH_LONG).show();

        //Criar canal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "canal2";
            String description = "Descrição";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNELID, name, importance);
            channel.setDescription(description);
            NotificationManager nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }

        //Gerar Notificação
        Bundle bundle = intent.getExtras();
        System.out.println(bundle.getParcelable("teste"));

        Intent i = new Intent(context, DetailsActivity.class);
        //i.putExtra("matter", matter);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(i);
        PendingIntent pi = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification builder = new NotificationCompat.Builder(context, CHANNELID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Hora de estudar")
                .setContentText("Estudar sobre "/*+matter.getSummary()*/)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .build();

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(notificationId, builder);
    }
}
