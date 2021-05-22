package com.example.responsible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Category;
import com.example.responsible.database.entity.Spending;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

public class SpendingGoalAdapter extends BaseAdapter {

    private List<Spending> spendings;
    private Context context;
    private View view;
    private ViewGroup viewGroup;
    private AppDatabase db;

    public SpendingGoalAdapter(List<Spending> spending, Context context, AppDatabase db){
        this.spendings=spending;
        this.context=context;
        this.db=db;
    }


    @Override
    public int getCount() {
        int size=spendings.size();
        return  size;
    }

    @Override
    public Object getItem(int position) {
        return spendings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.activity_spending_overwiev_item, parent, false);

        TextView spendingText=convertView.findViewById(R.id.spendingInList);
        ImageView deleteImageView=convertView.findViewById(R.id.DeleteSpendingImageView);

        Spending spending=(Spending) getItem(position);
        String dateFormated=new DateTime (spending.spendingDate).toString("MMM, yyyy");
        spendingText.setText(dateFormated+" - Amount "+spending.spendingAmount);



        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.spendingDao().delete(spending);
                spendings=db.spendingDao().getAll();
                notifyDataSetChanged();
            }
        });


        return convertView;

    }
}
