package com.example.programadeestudo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.programadeestudo.model.Matter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editTextMatter, editTextAbstract,
            editTextSummary, editTextNote;
    Matter matter;
    List<String> abstracts = new ArrayList<>();
    Button buttonSalve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextMatter = findViewById(R.id.editTextMatter);
        editTextAbstract = findViewById(R.id.editTextAbstract);
        abstracts.add(editTextAbstract.getText().toString());
        editTextSummary = findViewById(R.id.editTextSummary);
        abstracts.add(editTextSummary.getText().toString());
        editTextNote = findViewById(R.id.editTextNote);
        abstracts.add(editTextNote.getText().toString());

        buttonSalve = findViewById(R.id.buttonSalve);

        buttonSalve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matter.setName(editTextMatter.getText().toString());
               // matter.setAbstracts(abstracts);

                System.out.println(matter.getName());
            }
        });



    }
}
