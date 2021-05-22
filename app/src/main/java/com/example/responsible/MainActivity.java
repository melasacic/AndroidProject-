package com.example.responsible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText categoryNameEditText;
    private Button categoryNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();

        List<Category> categories=db.categoryDao().getAll();
        System.out.println(categories.size());

        categoryNameButton=findViewById(R.id.CategoryAddButton);
        categoryNameEditText=findViewById(R.id.CategoryNameEditText);

        categoryNameButton.setOnClickListener(new View.OnClickListener() {   //anonimna klasa
            @Override
            public void onClick(View v) {
                String editTextValue= categoryNameEditText.getText().toString();
                System.out.println(editTextValue);

                Category category=new Category();
                category.name =editTextValue; // value procitan iz input field; category object and category variable
                db.categoryDao().insertAll(category);

                Intent intent=new Intent(MainActivity.this, CategoryListActivity.class);
                startActivity(intent);
            }
        });
    }
}
