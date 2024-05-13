package com.example.assignment1;

public class Event {
    private String eventId;
    private String categoryId;
    private String name;
    private int ticketsAvailable;
    private boolean isActive;

    public Event(String eventId, String categoryId, String name, int ticketsAvailable, boolean isActive){
        this.eventId = eventId;
        this.categoryId = categoryId;
        this.name = name;
        this.ticketsAvailable = ticketsAvailable;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getEventId() { return eventId; }
    public String getCategoryId() { return categoryId; }
    public String getName() { return name; }
    public int getTicketsAvailable() { return ticketsAvailable; }
    public boolean getIsActive() { return isActive; }

    public void setEventId(String id) { this.eventId = id; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public void setName(String name) { this.name = name; }
    public void setTicketsAvailable(int ticketsAvailable) { this.ticketsAvailable = ticketsAvailable; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
}