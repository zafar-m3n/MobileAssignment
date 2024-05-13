package com.example.assignment1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.database.viewmodel.EMAViewModel;

public class FragmentListEvent extends Fragment {

    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private EMAViewModel emaViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_event, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        emaViewModel = new ViewModelProvider(requireActivity()).get(EMAViewModel.class);
        emaViewModel.getAllEvents().observe(getViewLifecycleOwner(), events -> {
            adapter = new EventAdapter(events, getContext());
            recyclerView.setAdapter(adapter);
        });
    }
}