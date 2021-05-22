package com.example.responsible.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey (autoGenerate = true) // sam generise id
    public int id;

    @ColumnInfo (name="name")
    public String name;
}
