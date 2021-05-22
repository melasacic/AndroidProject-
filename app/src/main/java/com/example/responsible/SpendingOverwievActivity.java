package com.example.responsible;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.responsible.database.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SpendingOverwievActivity extends AppCompatActivity {
    private ListView spendingListView;
    private FloatingActionButton spendingListFloatingButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_overwiev);

        spendingListFloatingButton = findViewById(R.id.SpendingListFloatingButton);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();

        spendingListView = findViewById(R.id.SpendingListView);

        spendingListFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpendingOverwievActivity.this, ChooseSpendingGoalActivity.class);
                startActivity(intent);
            }
        });

        SpendingGoalAdapter spendingGoalAdapter = new SpendingGoalAdapter(db.spendingDao().getAll(), this, db);
        spendingListView.setAdapter(spendingGoalAdapter);


    }
}
