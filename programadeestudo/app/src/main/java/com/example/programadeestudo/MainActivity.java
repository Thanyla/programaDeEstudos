package com.example.programadeestudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.programadeestudo.model.Matter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMatter, editTextAbstract, editTextHour;
    private Button buttonSalve;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static final String CHANNELID = "2";
    private int notificationId = 1;
    private Matter matter = new Matter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMatter = findViewById(R.id.editTextMatter);
        editTextAbstract = findViewById(R.id.editTextAbstract);
        editTextHour = findViewById(R.id.editTextHour);
        buttonSalve = findViewById(R.id.buttonSalve);



        buttonSalve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matter.setNameMatter(editTextMatter.getText().toString());
                criarCanalNotificacao();
                disparar();
            }
        });
    }

    private void disparar() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                0, i, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime()+2*1000,
                pendingIntent);
        gerar();
    }

    public void gerar() {
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("matter", matter);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        Notification builder = new NotificationCompat.Builder(this, CHANNELID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Hora de estudar")
                .setContentText("Estudar sobre "+ editTextAbstract.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pi)
                .build();

        NotificationManagerCompat nm = NotificationManagerCompat.from(this);
        nm.notify(notificationId, builder);
    }//method

    private void criarCanalNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "canal2";
            String description = "Descrição";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNELID, name, importance);
            channel.setDescription(description);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }
}
