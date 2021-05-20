package com.example.responsible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.responsible.database.entity.Category;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {
    private ListView categoryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryListView=findViewById(R.id.CategoryListView);

        Category food=new Category();
        food.category="Food";
        Category gas=new Category();
        gas.category="Gas";
        Category utilities=new Category();
        utilities.category="Utilities";

        ArrayList<Category> categories=new ArrayList<>();
        categories.add(food);
        categories.add(gas);
        categories.add(utilities);

        CategoryListAdapter categoryListAdapter=new CategoryListAdapter(categories, this);
        categoryListView.setAdapter(categoryListAdapter);


    }
}
