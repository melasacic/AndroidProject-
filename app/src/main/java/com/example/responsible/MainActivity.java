package com.example.responsible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText categoryNameEditText;
    private Button categoryNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryNameButton=findViewById(R.id.CategoryAddButton);
        categoryNameEditText=findViewById(R.id.CategoryNameEditText);

        categoryNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextValue= categoryNameEditText.getText().toString();
                System.out.println(editTextValue);
            }
        });
    }
}
