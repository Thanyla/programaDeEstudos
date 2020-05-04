package com.example.programadeestudo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.programadeestudo.model.Matter;

public class DetailsActivity extends AppCompatActivity {
    TextView textViewMatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        Matter matter = (Matter) getIntent().getSerializableExtra("matter");

        textViewMatter = findViewById(R.id.textViewEstudar);
        textViewMatter.setText(matter.getNameMatter());

    }
}
