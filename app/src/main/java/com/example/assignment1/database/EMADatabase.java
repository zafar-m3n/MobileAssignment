package com.example.assignment1.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.assignment1.database.dao.EventCategoryDao;
import com.example.assignment1.database.dao.EventDao;
import com.example.assignment1.database.entity.Event;
import com.example.assignment1.database.entity.EventCategory;

@Database(entities = {Event.class, EventCategory.class}, version = 2, exportSchema = false)
public abstract class EMADatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract EventCategoryDao eventCategoryDao();
    private static volatile EMADatabase INSTANCE;
    public static EMADatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EMADatabase.class) {
                if (INSTANCE == null) {
                    Log.d("DatabaseOperations", "Creating new database instance");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EMADatabase.class, "ema_database")
                            .fallbackToDestructiveMigration()
                            .build();
                    Log.d("DatabaseOperations", "Database instance created");
                }
            }
        }
        return INSTANCE;
    }

}