package com.example.assignment1.database.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment1.database.entity.Event;
import com.example.assignment1.database.entity.EventCategory;
import com.example.assignment1.database.repository.EMARepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EMAViewModel extends AndroidViewModel {
    private EMARepository repository;
    private LiveData<List<Event>> allEvents;
    private LiveData<List<EventCategory>> allCategories;
    private Executor executor; // Executor for background tasks

    public EMAViewModel(Application application) {
        super(application);
        repository = new EMARepository(application);
        allEvents = repository.getAllEvents();
        allCategories = repository.getAllCategories();
        executor = Executors.newSingleThreadExecutor(); // Initialize the executor
    }

    // Event operations with background execution
    public void insertEvent(final Event event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.insert(event);
            }
        });
    }

    public void updateEvent(final Event event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.update(event);
            }
        });
    }

    public void deleteEvent(final Event event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.delete(event);
            }
        });
    }

    public void deleteAllEvents() {
        executor.execute(() -> {
            repository.deleteAllEvents(); // Assuming this method exists in your repository
        });
    }


    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    // Category operations with background execution
    public void insertCategory(final EventCategory category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.insert(category);
            }
        });
    }

    public void updateCategory(final EventCategory category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.update(category);
            }
        });
    }

    public void deleteCategory(final EventCategory category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.delete(category);
            }
        });
    }

    public void deleteAllCategories() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                repository.deleteAllCategories(); // Assuming this method exists in your repository
            }
        });
    }

    public LiveData<List<EventCategory>> getAllCategories() {
        return allCategories;
    }
}