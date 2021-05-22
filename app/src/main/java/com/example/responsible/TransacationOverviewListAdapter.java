package com.example.responsible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.responsible.database.AppDatabase;
import com.example.responsible.database.entity.Transaction;

import org.joda.time.DateTime;

import java.util.List;

public class TransacationOverviewListAdapter extends BaseAdapter {

    private List<Transaction> transactions;
    private Context context;
    private View view;
    private ViewGroup viewGroup;
    private AppDatabase db;

    public TransacationOverviewListAdapter(List<Transaction> transaction, Context context, AppDatabase db){
        this.transactions=transaction;
        this.context=context;
        this.db=db;
    }

    @Override
    public int getCount() {
        int size=transactions.size();
        return  size;
    }

    @Override
    public Object getItem(int position) {
        return transactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView==null){
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_transaction_list_item, parent, false);

        //}

        TextView transactionText = convertView.findViewById(R.id.transactionInList);
        ImageView deleteImageView = convertView.findViewById(R.id.TransactionDeleteImageView);
        deleteImageView.setVisibility(View.GONE);

        Transaction transaction = (Transaction) getItem(position);
        transactionText.setText(transaction.name);

        String dateFormated=new DateTime(transaction.date).toString("MMM-dd, yyyy");
        transactionText.setText(dateFormated+" - Amount "+transaction.amount);



        return convertView;
    }}

