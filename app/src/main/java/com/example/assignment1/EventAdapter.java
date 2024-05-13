package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.database.entity.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> events;
    private Context context; // Context to start Activity

    public EventAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.tvEventIdName.setText("Event ID: " + event.getEventId() + " - " + event.getName());
        holder.tvEventDetails.setText("Category: " + event.getCategoryId() + " | Tickets: " + event.getTicketsAvailable() + " | Active: " + (event.getIsActive() ? "Yes" : "No"));

        // Set click listener to launch EventGoogleResult
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EventGoogleResult.class);
            intent.putExtra("eventName", event.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventIdName, tvEventDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEventIdName = itemView.findViewById(R.id.tvEventIdName);
            tvEventDetails = itemView.findViewById(R.id.tvEventDetails);
        }
    }
}