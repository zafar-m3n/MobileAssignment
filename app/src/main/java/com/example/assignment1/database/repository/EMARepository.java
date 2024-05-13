package com.example.assignment1.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.assignment1.database.EMADatabase;
import com.example.assignment1.database.dao.EventCategoryDao;
import com.example.assignment1.database.dao.EventDao;
import com.example.assignment1.database.entity.Event;
import com.example.assignment1.database.entity.EventCategory;

import java.util.List;

public class EMARepository {
    private EventDao eventDao;
    private EventCategoryDao eventCategoryDao;
    private LiveData<List<Event>> allEvents;
    private LiveData<List<EventCategory>> allCategories;

    // Constructor that gets a handle to the database and initializes member variables.
    public EMARepository(Application application) {
        EMADatabase db = EMADatabase.getDatabase(application);
        eventDao = db.eventDao();
        eventCategoryDao = db.eventCategoryDao();
        allEvents = eventDao.getAllEvents();
        allCategories = eventCategoryDao.getAllCategories();
    }

    // Wrapper methods for database operations
    public void insert(Event event) { eventDao.insert(event); }
    public void update(Event event) { eventDao.update(event); }
    public void delete(Event event) { eventDao.delete(event); }
    public LiveData<List<Event>> getAllEvents() { return allEvents; }
    public void deleteAllEvents() {
        eventDao.deleteAllEvents();
    }

    public void insert(EventCategory category) { eventCategoryDao.insert(category); }
    public void update(EventCategory category) { eventCategoryDao.update(category); }
    public void delete(EventCategory category) { eventCategoryDao.delete(category); }
    public void deleteAllCategories() {
        eventCategoryDao.deleteAll();
    }

    public LiveData<List<EventCategory>> getAllCategories() { return allCategories; }
}