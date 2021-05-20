package com.example.responsible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChooseSpendingGoalActivity extends AppCompatActivity {
    private TextView chooseMonthTextView;
    private TextView chooseAmountTextView;

    private EditText chooseMonthEditText;
    private EditText chooseAmountEditText;

    private Button saveSpendingGoalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_spending_goal);

        chooseMonthTextView=findViewById(R.id.ChooseMonthTextView);
        chooseAmountTextView=findViewById(R.id.ChooseAmountTextView);
        chooseMonthEditText=findViewById(R.id.ChooseMonthEditText);
        chooseAmountEditText=findViewById(R.id.ChooseAmountEditText);
        saveSpendingGoalButton=findViewById(R.id.SaveSpendingGoalButton);

        saveSpendingGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chooseMonthEditTextValue=chooseMonthEditText.getText().toString();
                String chooseAmountEditTextValue=chooseAmountEditText.getText().toString();

                System.out.println(chooseMonthEditTextValue);
                System.out.println(chooseAmountEditTextValue);
            }
        });
    }
}
