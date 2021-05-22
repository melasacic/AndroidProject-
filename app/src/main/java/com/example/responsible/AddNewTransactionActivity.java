package com.example.responsible;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Transaction;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class AddNewTransactionActivity extends AppCompatActivity {
    private EditText transactionNameEditText;
    private EditText transactionLocationEditText;
    private EditText transactionAmountEditText;

    private Button transactionAddButton;
    private Button addNewCategoryButton;
    private Button chooseDateButton;

    private String transactionName;
    private String transactionLocation;
    private int transactionAmount;
    private long transactionDate;
    private String transactionCategory;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();

        List<String> categoriesList = db.categoryDao().getAll().stream()
                .map(x->x.name)
                .collect(toList());

        String[] categories = new String[categoriesList.size()];
        categoriesList.toArray(categories);

        transactionNameEditText=findViewById(R.id.TransactionNameEditText);
        transactionLocationEditText=findViewById(R.id.TransactionLocationEditText);
        transactionAmountEditText=findViewById(R.id.TransactionAmountEditText);

        transactionAddButton=findViewById(R.id.TransactionAddButton);
        addNewCategoryButton=findViewById(R.id.AddNewCategoryButton);
        chooseDateButton=findViewById(R.id.PickADateButton);

        transactionAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             transactionName=transactionNameEditText.getText().toString();
             transactionLocation=transactionLocationEditText.getText().toString();
             transactionAmount=Integer.valueOf(transactionAmountEditText.getText().toString());

                Transaction transaction=new Transaction();
                transaction.amount=transactionAmount;
                transaction.location=transactionLocation;
                transaction.date=transactionDate;
                transaction.name=transactionName;
                transaction.category=transactionCategory;

               db.transactionDao().insertAll(transaction);
                Intent intent=new Intent(AddNewTransactionActivity.this, TransactionListActivity.class);
                startActivity(intent);


            }
        });

        addNewCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(AddNewTransactionActivity.this);
                builder.setTitle("Choose category")
                        .setItems(categories, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                transactionCategory=categories[which];
                                dialog.cancel();
                            }
                        });

                dialog = builder.create();
                dialog.show();
            }
        });

        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTime dateNow=DateTime.now();
                DatePickerDialog dialog=new DatePickerDialog(AddNewTransactionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DateTime dateTime=new DateTime(year,month,dayOfMonth,dateNow.getHourOfDay(),dateNow.getMinuteOfHour());
                        transactionDate=dateTime.getMillis();
                    }
                }, dateNow.year().get(),dateNow.getMonthOfYear(),dateNow.getDayOfMonth());

                dialog.show();
            }

        });






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
                Intent intent = new Intent(AddNewTransactionActivity.this, OverviewActivity.class);
                startActivity(intent);
                return true;
        }

        return true;
    }
}
