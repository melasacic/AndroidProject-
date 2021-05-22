package com.example.responsible.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.responsible.database.entity.Category;
import com.example.responsible.database.entity.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM `transaction` order by date desc")
    List<Transaction> getAll();

//    @Query("SELECT * FROM category WHERE category LIKE :first ")
//    Category findByName(String first);

    @Insert
    void insertAll(Transaction... transaction);

    @Delete
    void delete(Transaction transaction);

}
