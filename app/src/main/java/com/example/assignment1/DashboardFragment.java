package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment1.database.entity.Event;
import com.example.assignment1.database.entity.EventCategory;
import com.example.assignment1.database.viewmodel.EMAViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.List;

public class DashboardFragment extends Fragment implements DashboardActivity.DashboardActions, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    EditText editEventID, editCategoryID, editEventName, editTicketsAvailable;
    Switch isActiveSwitch;
    FloatingActionButton fab;
    EMAViewModel emaViewModel;
    private GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // Initialize GestureDetector
        gestureDetector = new GestureDetector(view.getContext(), this);
        // Setup touchpad
        FrameLayout touchpad = view.findViewById(R.id.touchpad_area);
        touchpad.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editEventID = view.findViewById(R.id.editEventID);
        editCategoryID = view.findViewById(R.id.editCategoryID);
        editEventName = view.findViewById(R.id.editEventName);
        editTicketsAvailable = view.findViewById(R.id.editTicketsAvailable);
        isActiveSwitch = view.findViewById(R.id.switchIsActive);
        fab = view.findViewById(R.id.fab);

        emaViewModel = new ViewModelProvider(requireActivity()).get(EMAViewModel.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });
    }

    private void saveEvent() {
        String categoryId = editCategoryID.getText().toString();
        String eventName = editEventName.getText().toString();
        String ticketsAvailableStr = editTicketsAvailable.getText().toString();
        String eventId = generateEventId();
        boolean isActive = isActiveSwitch.isChecked();

        if (eventName.isEmpty() || ticketsAvailableStr.isEmpty() || categoryId.isEmpty()) {
            Toast.makeText(getActivity(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Define an observer that removes itself after receiving data
        Observer<List<EventCategory>> categoryObserver = new Observer<List<EventCategory>>() {
            @Override
            public void onChanged(List<EventCategory> categories) {
                EventCategory matchingCategory = null;
                for (EventCategory category : categories) {
                    if (category.getCategoryId().equals(categoryId)) {
                        matchingCategory = category;
                        break;
                    }
                }

                if (matchingCategory == null) {
                    Toast.makeText(getActivity(), "Category ID does not exist", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int ticketsAvailable = Integer.parseInt(ticketsAvailableStr);
                        Event newEvent = new Event(eventId, categoryId, eventName, ticketsAvailable, isActive);
                        emaViewModel.insertEvent(newEvent);

                        matchingCategory.setEventCount(matchingCategory.getEventCount() + 1);
                        emaViewModel.updateCategory(matchingCategory);

                        Toast.makeText(getActivity(), "Event saved successfully", Toast.LENGTH_SHORT).show();
                        clearTextFields();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity(), "Invalid number for tickets available", Toast.LENGTH_SHORT).show();
                    }
                }
                // Remove observer after it's used
                emaViewModel.getAllCategories().removeObserver(this);
            }
        };
        // Add the observer
        emaViewModel.getAllCategories().observe(getViewLifecycleOwner(), categoryObserver);
    }

    @Override
    public void clearTextFields() {
        editEventID.setText("");
        editCategoryID.setText("");
        editEventName.setText("");
        editTicketsAvailable.setText("");
        isActiveSwitch.setChecked(false);
    }
    private String generateEventId() {
        StringBuilder eventIdBuilder = new StringBuilder();
        eventIdBuilder.append("E");
        eventIdBuilder.append(randomAlphaNumeric());
        eventIdBuilder.append("-");
        eventIdBuilder.append(randomNumeric());
        return eventIdBuilder.toString();
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
        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * digits.length());
            builder.append(digits.charAt(index));
        }
        return builder.toString();
    }


    @Override
    public boolean onDoubleTap(MotionEvent e) {
        saveEvent();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        clearTextFields();
    }

    @Override
    public boolean onDown(MotionEvent e) { return true; }
    @Override
    public void onShowPress(MotionEvent e) {}
    @Override
    public boolean onSingleTapUp(MotionEvent e) { return true; }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return true; }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { return true; }
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) { return true; }
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) { return true; }
}