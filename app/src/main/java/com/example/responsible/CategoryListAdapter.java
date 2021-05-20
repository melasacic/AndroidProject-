package com.example.responsible;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.responsible.database.entity.Category;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {
    private ArrayList<Category> categories;
    private Context context;
    private View view;
    private ViewGroup viewGroup;

    public CategoryListAdapter (ArrayList<Category> categories, Context context){
        this.categories=categories;
        this.context=context;
    }


    @Override
    public int getCount() {
        int size=categories.size();
        return  size;
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       //if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);

       //}

        TextView categoryText=convertView.findViewById(R.id.categoryInList);

        Category category=(Category)getItem(position);
        categoryText.setText(category.category);

        return convertView;

    }
}
