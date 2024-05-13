package com.example.assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment1.database.entity.EventCategory;
import com.example.assignment1.database.viewmodel.EMAViewModel;


public class NewEventCategoryActivity extends AppCompatActivity{

    EditText editCategoryName, editTextNumber, editCategoryID, editEventLocation;
    Switch isActiveSwitch;
    Button saveButton;

    private EMAViewModel emaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize ViewModel
        emaViewModel = new ViewModelProvider(this).get(EMAViewModel.class);

        // Get references to UI components
        editCategoryName = findViewById(R.id.editCategoryName);
        editCategoryID = findViewById(R.id.editCategoryID);
        editTextNumber = findViewById(R.id.editTextNumber);
        editEventLocation = findViewById(R.id.editEventLocation);
        isActiveSwitch = findViewById(R.id.switchIsActive);
        saveButton = findViewById(R.id.btnSaveCategory);

        saveButton.setOnClickListener(this::OnSaveCategoryClick);
    }

    public void OnSaveCategoryClick(View view) {
        String categoryName = editCategoryName.getText().toString();
        String categoryId = generateCategoryId();
        String eventCountString = editTextNumber.getText().toString();
        String eventLocation = editEventLocation.getText().toString();
        boolean isActive = isActiveSwitch.isChecked();

        if (categoryName.isEmpty() || eventCountString.isEmpty() || eventLocation.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        int eventCount;
        try {
            eventCount = Integer.parseInt(eventCountString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number for event count", Toast.LENGTH_SHORT).show();
            return;
        }

        EventCategory newCategory = new EventCategory(categoryId, categoryName, eventCount, isActive, eventLocation);
        emaViewModel.insertCategory(newCategory);

        Toast.makeText(this, "Category saved successfully: " + categoryId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    private String generateCategoryId() {
        StringBuilder categoryIdBuilder = new StringBuilder();
        categoryIdBuilder.append("C");
        categoryIdBuilder.append(randomAlphaNumeric());
        categoryIdBuilder.append("-");
        categoryIdBuilder.append(randomNumeric());
        return categoryIdBuilder.toString();
    }

    private String randomAlphaNumeric() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int index = (int) (Math.random() * characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();
    }

    private String randomNumeric() {
        String digits = "0123456789";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = (int) (Math.random() * digits.length());
            builder.append(digits.charAt(index));
        }
        return builder.toString();
    }
}