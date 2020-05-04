package com.example.programadeestudo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.programadeestudo.model.Matter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private EditText editTextMatter, editTextAbstract;
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
        buttonSalve = findViewById(R.id.buttonSalve);

        Button buttonHour = findViewById(R.id.buttonHour);
        buttonHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        buttonSalve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matter.setNameMatter(editTextMatter.getText().toString());
                matter.setSummary(editTextAbstract.getText().toString());
                criarCanalNotificacao();
                disparar();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //disparar();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        matter.setHourAlarm(hour);
        matter.setMinuteAlarm(minute);
        System.out.println("HORAS"+matter.getHourAlarm());
    }

    private void disparar() {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                0, i, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, matter.getHourAlarm());
        calendar.set(Calendar.MINUTE, matter.getMinuteAlarm());

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis()+2,
                pendingIntent);
        //gerar();
    }

    public void gerar() {
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("matter", matter);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(i);
        PendingIntent pi = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

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
