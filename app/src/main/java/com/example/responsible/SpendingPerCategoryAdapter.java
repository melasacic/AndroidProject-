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
import com.example.responsible.model.SpendingPerCategory;

import org.joda.time.DateTime;
import org.w3c.dom.Text;

import java.util.List;

public class SpendingPerCategoryAdapter extends BaseAdapter {

    private List<SpendingPerCategory> spendingPerCategories;
    private Context context;
    private View view;
    private ViewGroup viewGroup;
    private AppDatabase db;

    public SpendingPerCategoryAdapter(List<SpendingPerCategory> spendingPerCategories, Context context, AppDatabase db){
        this.spendingPerCategories=spendingPerCategories;
        this.context=context;
        this.db=db;
    }

    @Override
    public int getCount() {
        int size=spendingPerCategories.size();
        return  size;
    }

    @Override
    public Object getItem(int position) {
        return spendingPerCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView==null){
        convertView = LayoutInflater.from(context).inflate(R.layout.spending_per_category_item, parent, false);

        //}
        TextView categoryName=convertView.findViewById(R.id.SpendingPerCategoryName);
        TextView categoryTotal=convertView.findViewById(R.id.SpendingPerCategoryTotal);

        categoryName.setText(spendingPerCategories.get(position).categoryName);
        categoryTotal.setText(spendingPerCategories.get(position).totalAmount + " BAM");
        return convertView;
    }}

