package com.example.responsible;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Spending;
import com.example.responsible.database.entity.Transaction;
import com.example.responsible.model.SpendingPerCategory;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class OverviewActivity extends AppCompatActivity {
    private ListView transactionOverview;
    private ListView categoryList;
    private TextView spendingGoal;
    private TextView spent;
    private Button addTransaction;
    private Button addSpending;
    private Button addCategory;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "category_db").allowMainThreadQueries().build();


        transactionOverview=findViewById(R.id.TransactionOverviewListView);
        categoryList=findViewById(R.id.CategoryTransactionListView);
        spendingGoal=findViewById(R.id.SpendingGoalLabelTextView);
        spent=findViewById(R.id.SpendingGoalTextViewOverview);

        addTransaction=findViewById(R.id.AddTransactionOverview);
        addSpending=findViewById(R.id.AddSpendingOverview);
        addCategory=findViewById(R.id.AddCategoryOverview);

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OverviewActivity.this, AddNewTransactionActivity.class);
                startActivity(intent);
            }
        });

        addSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OverviewActivity.this, ChooseSpendingGoalActivity.class);
                startActivity(intent);
            }
        });

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OverviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        List<Transaction> transactions=db.transactionDao().getAll();
        DateTime date =new DateTime(DateTime.now().getYear(), DateTime.now().getMonthOfYear(),1,0,0);
        Spending spending=db.spendingDao().getForDate(date.getMillis());
        int totalSpent=transactions.stream()
                .map(x->x.amount)
                .reduce(0,(subtotal,element)-> subtotal + element);

        List<Transaction> limitedTransactions = transactions.stream()
                .limit(3)
                .collect(Collectors.toList());

        TransacationOverviewListAdapter transactionListAdapter=new TransacationOverviewListAdapter(limitedTransactions, this, db);
        transactionOverview.setAdapter(transactionListAdapter);

        SpendingPerCategoryAdapter categoryListAdapter=new SpendingPerCategoryAdapter(agregateTransactions(transactions), this, db);
        categoryList.setAdapter(categoryListAdapter);

        spendingGoal.setText("Spending goal for this month "+ spending.spendingAmount);
        spent.setText("Total spending this month "+ totalSpent);

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<SpendingPerCategory> agregateTransactions(List<Transaction> transactions){
        Map<String, List<Transaction>> transactionMap = transactions.stream()
                .collect(groupingBy(x -> x.category));

        List<SpendingPerCategory> spendingPerCategories = new ArrayList<>();
        transactionMap.keySet()
                .forEach(x -> {
                    int total = transactionMap.get(x).stream()
                            .map(t -> t.amount)
                            .reduce(0, (subtotal, el) -> subtotal + el);

                    SpendingPerCategory spendingPerCategory = new SpendingPerCategory();
                    spendingPerCategory.categoryName = x;
                    spendingPerCategory.totalAmount = total;
                    spendingPerCategories.add(spendingPerCategory);
                });

        return spendingPerCategories;
    }
}
