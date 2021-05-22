package com.example.responsible.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.responsible.database.entity.Category;
import com.example.responsible.database.entity.Spending;

import java.util.List;

@Dao
public interface SpendingDao {
    @Query("SELECT * FROM spending")
    List<Spending> getAll();

   @Query("SELECT * FROM spending WHERE spendingDate = :date")
    Spending getForDate(long date);


    @Insert
    void insertAll(Spending... spending);

    @Delete
    void delete(Spending spending);



}
