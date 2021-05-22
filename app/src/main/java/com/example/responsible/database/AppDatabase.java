package com.example.responsible.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.responsible.database.entity.Category;
import com.example.responsible.database.entity.Spending;
import com.example.responsible.database.entity.Transaction;

@Database(entities = {Category.class, Spending.class, Transaction.class},version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract SpendingDao spendingDao();
    public abstract  TransactionDao transactionDao();
}
