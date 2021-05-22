package com.example.responsible;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransactionListActivity extends AppCompatActivity {

    private ListView transactionListView;
    private FloatingActionButton transactionListFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        transactionListFloatingButton=findViewById(R.id.TransactionListFloatingButton);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();

        transactionListView=findViewById(R.id.TransactionListView);

        transactionListFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TransactionListActivity.this, AddNewTransactionActivity.class);
                startActivity(intent);
            }
        });

        TransactionListAdapter transactionListAdapter=new TransactionListAdapter(db.transactionDao().getAll(), this, db);
        transactionListView.setAdapter(transactionListAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Overview_menu:
                Intent intent = new Intent(TransactionListActivity.this, OverviewActivity.class);
                startActivity(intent);
                return true;
        }

        return true;
    }
}
