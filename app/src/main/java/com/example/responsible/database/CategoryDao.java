package com.example.responsible.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.responsible.database.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> getAll();

//    @Query("SELECT * FROM category WHERE category LIKE :first ")
//    Category findByName(String first);

    @Insert
    void insertAll(Category... categories);

    @Delete
    void delete(Category category);

}
