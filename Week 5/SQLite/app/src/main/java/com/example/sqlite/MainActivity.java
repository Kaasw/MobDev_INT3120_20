package com.example.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEdit, hobbyEdit;
    private Button addButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEdit = findViewById(R.id.editName);
        hobbyEdit = findViewById(R.id.editHobby);
        addButton = findViewById(R.id.add);

        dbHandler = new DBHandler(MainActivity.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String hobby = hobbyEdit.getText().toString();
                if (name.isEmpty() && hobby.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNew(name, hobby);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Added.", Toast.LENGTH_SHORT).show();
                nameEdit.setText("");
                hobbyEdit.setText("");
            }
        });
    }
}
