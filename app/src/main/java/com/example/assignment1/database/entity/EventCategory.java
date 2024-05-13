package com.example.assignment1.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "categories")
public class EventCategory {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String categoryId;
    private String name;
    private int eventCount;
    private boolean isActive;
    private String eventLocation;

    public EventCategory(@NonNull String categoryId, String name, int eventCount, boolean isActive, String eventLocation){
        this.categoryId = categoryId;
        this.name = name;
        this.eventCount = eventCount;
        this.isActive = isActive;
        this.eventLocation = eventLocation;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEventCount() { return eventCount; }
    public void setEventCount(int eventCount) { this.eventCount = eventCount; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public String getEventLocation() { return eventLocation; }
    public void setEventLocation(String eventLocation) { this.eventLocation = eventLocation; }
}