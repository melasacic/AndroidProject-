package com.example.responsible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewTransactionActivity extends AppCompatActivity {
    private EditText transactionNameEditText;
    private EditText transactionLocationEditText;
    private EditText transactionDateEditText;
    private EditText transactionAmountEditText;
    private EditText transactionCategoryEditText;

    private Button transactionAddButton;
    private Button addNewCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);

        transactionNameEditText=findViewById(R.id.TransactionNameEditText);
        transactionLocationEditText=findViewById(R.id.TransactionLocationEditText);
        transactionDateEditText=findViewById(R.id.TransactionDateEditText);
        transactionAmountEditText=findViewById(R.id.TransactionAmountEditText);
       /* transactionCategoryEditText=findViewById(R.id.TransactionCategoryEditText);*/

        transactionAddButton=findViewById(R.id.TransactionAddButton);
        addNewCategoryButton=findViewById(R.id.AddNewCategoryButton);

        transactionAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameEditTextValue=transactionNameEditText.getText().toString();
                String LocationEditTextValue=transactionLocationEditText.getText().toString();
                String DateEditTextValue=transactionDateEditText.getText().toString();
                String AmountEditTextValue=transactionAmountEditText.getText().toString();
              /*  String CategoryEditTextValue=transactionCategoryEditText.getText().toString();*/

                System.out.println(NameEditTextValue);
                System.out.println(LocationEditTextValue);
                System.out.println(DateEditTextValue);
                System.out.println(AmountEditTextValue);
               /* System.out.println(CategoryEditTextValue);*/

            }
        });





    }
}
