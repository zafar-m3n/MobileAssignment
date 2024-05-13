package com.example.assignment1;

public class Category {
    private String id;
    private String name;
    private int eventCount;
    private boolean isActive;

    public Category(String id, String name, int eventCount, boolean isActive){
        this.id = id;
        this.name = name;
        this.eventCount = eventCount;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getEventCount() { return eventCount; }
    public boolean getIsActive() { return isActive; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEventCount(int eventCount) { this.eventCount = eventCount; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}