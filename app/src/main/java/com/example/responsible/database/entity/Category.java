package com.example.responsible.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    public int id;

    @ColumnInfo (name="category")
    public String category;
}
