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
                disparar();
                Toast.makeText(getApplicationContext(), "Alarme cadastrado", Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        matter.setHourAlarm(hour);
        matter.setMinuteAlarm(minute);
        System.out.println("HORAS"+matter.getHourAlarm());
    }

    private void disparar() {
        Intent i = new Intent(this, AlarmReceiver.class);
        i.putExtra("teste", matter);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(),
                0,
                i,
                0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, matter.getHourAlarm());
        calendar.set(Calendar.MINUTE, matter.getMinuteAlarm());

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis()+2,
                pendingIntent);
    }
}
