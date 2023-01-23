package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText inputName, inputDescription;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inputName = findViewById(R.id.input_name);
        inputDescription = findViewById(R.id.input_description);
        btnSave = findViewById(R.id.save_button);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper db = new MyDatabaseHelper(AddActivity.this);
                db.addBook(inputName.getText().toString().trim(), inputDescription.getText().toString().trim());
                inputName.getText().clear();
                inputDescription.getText().clear();
            }
        });
    }
}