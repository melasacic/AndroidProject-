package com.example.responsible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Spending;

import org.joda.time.DateTime;

import java.util.Date;

public class ChooseSpendingGoalActivity extends AppCompatActivity {
    private TextView chooseMonthTextView;
    private TextView chooseAmountTextView;

    private EditText chooseMonthEditText;
    private EditText chooseAmountEditText;

    private Button chooseDateButton;
    private Button saveSpendingGoalButton;

    private DateTime spendingDate; // sta je user odabrao kad spending date
    private int spendingAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_spending_goal);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();

        chooseMonthTextView = findViewById(R.id.ChooseMonthTextView);
        chooseAmountTextView = findViewById(R.id.ChooseAmountTextView);
        chooseDateButton = findViewById(R.id.CalendarButton);
        chooseAmountEditText = findViewById(R.id.ChooseAmountEditText);
        saveSpendingGoalButton = findViewById(R.id.SaveSpendingGoalButton);

        saveSpendingGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String chooseAmountEditTextValue = chooseAmountEditText.getText().toString();
                System.out.println(chooseAmountEditTextValue);
                spendingAmount=Integer.valueOf(chooseAmountEditTextValue);

                Spending spending=new Spending();
                spending.spendingAmount=spendingAmount;
                spending.spendingDate=spendingDate.getMillis();
                db.spendingDao().insertAll(spending);

                Intent intent=new Intent(ChooseSpendingGoalActivity.this, SpendingOverwievActivity.class);
                startActivity(intent);
            }
        });

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


        chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseSpendingGoalActivity.this);
                builder.setTitle("Choose month")
                        .setItems(months, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                int month=which+1;

                                DateTime date =new DateTime(DateTime.now().getYear(), month,1,0,0);
                                spendingDate=date;
                                dialog.cancel();
                            }
                        });

                dialog = builder.create();
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
                Intent intent = new Intent(ChooseSpendingGoalActivity.this, OverviewActivity.class);
                startActivity(intent);
                return true;
        }

        return true;
    }
}