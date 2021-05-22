package com.example.responsible.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transaction {
    @PrimaryKey (autoGenerate = true) // sam generise id
    public int id;

    @ColumnInfo (name="name")
    public String name;

    @ColumnInfo (name="location")
    public String location;

    @ColumnInfo (name="date")
    public long date;

    @ColumnInfo (name="amount")
    public int amount;

    @ColumnInfo (name="category")
    public String category;




}
