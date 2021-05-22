package com.example.responsible.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.responsible.database.entity.Category;
import com.example.responsible.database.entity.Spending;

@Database(entities = {Category.class, Spending.class},version=4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract SpendingDao spendingDao();
}
