package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.database.entity.EventCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<EventCategory> categories;
    private Context context; // To use for starting the activity

    public CategoryAdapter(List<EventCategory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventCategory category = categories.get(position);
        holder.textViewId.setText(String.valueOf(category.getCategoryId()));
        holder.textViewName.setText(category.getName());
        holder.textViewEventCount.setText(String.valueOf(category.getEventCount()));
        holder.textViewIsActive.setText(category.getIsActive() ? "Yes" : "No");
        holder.textViewLocation.setText(category.getEventLocation());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GoogleMapActivity.class);
            intent.putExtra("location", category.getEventLocation());
            intent.putExtra("categoryName", category.getName());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewName, textViewEventCount, textViewIsActive, textViewLocation;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewEventCount = itemView.findViewById(R.id.textViewEventCount);
            textViewIsActive = itemView.findViewById(R.id.textViewIsActive);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);
        }
    }

    public void setCategories(List<EventCategory> newCategories) {
        this.categories = newCategories;
        notifyDataSetChanged();
    }
}