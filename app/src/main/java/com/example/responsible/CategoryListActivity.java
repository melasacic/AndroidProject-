package com.example.responsible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {
    private ListView categoryListView;
    private FloatingActionButton categoryListFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryListFloatingButton=findViewById(R.id.CategoryListFloatingButton);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();

        categoryListView=findViewById(R.id.CategoryListView);

        categoryListFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        CategoryListAdapter categoryListAdapter=new CategoryListAdapter(db.categoryDao().getAll(), this, db);
        categoryListView.setAdapter(categoryListAdapter);


    }
}
