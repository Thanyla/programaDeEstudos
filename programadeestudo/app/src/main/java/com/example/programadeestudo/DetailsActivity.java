package com.example.programadeestudo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.programadeestudo.model.Matter;

public class DetailsActivity extends AppCompatActivity {
    TextView textViewMatter, textViewAbstract, textViewHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        Matter matter = (Matter) bundle.get("matter");

        textViewMatter = findViewById(R.id.textViewExibiMatter);
        textViewMatter.setText("Matéria: "+matter.getNameMatter());

        textViewAbstract = findViewById(R.id.textViewExibiAbstract);
        textViewAbstract.setText("Conteúdo a ser estudado: "+matter.getSummary());

        textViewHour=findViewById(R.id.textViewExibirHour);
        textViewHour.setText("Horario de estudo a partir de:  "+matter.getHourAlarm()+":"+matter.getMinuteAlarm());

    }
}
