package com.example.responsible.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Spending {

    @PrimaryKey(autoGenerate = true) // sam generise id
    public int id;

    @ColumnInfo(name="spendingDate")
    public long spendingDate;

    @ColumnInfo(name="spendingAmount")
    public int spendingAmount;


}
