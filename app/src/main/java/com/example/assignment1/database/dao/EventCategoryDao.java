package com.example.assignment1.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment1.database.entity.EventCategory;

import java.util.List;

@Dao
public interface EventCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EventCategory category);

    @Update
    void update(EventCategory category);

    @Delete
    void delete(EventCategory category);

    @Query("SELECT * FROM categories")
    LiveData<List<EventCategory>> getAllCategories();

    @Query("DELETE FROM categories")
    void deleteAll();

}