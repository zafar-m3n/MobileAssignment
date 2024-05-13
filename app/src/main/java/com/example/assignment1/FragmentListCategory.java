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

import com.example.assignment1.database.entity.EventCategory;
import com.example.assignment1.database.viewmodel.EMAViewModel;

import java.util.List;

public class FragmentListCategory extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private EMAViewModel emaViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_category, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        emaViewModel = new ViewModelProvider(this).get(EMAViewModel.class);
        emaViewModel.getAllCategories().observe(getViewLifecycleOwner(), categories -> {
            if (adapter == null) {
                adapter = new CategoryAdapter(categories, getContext());
                recyclerView.setAdapter(adapter);
            } else {
                adapter.setCategories(categories);
            }
        });

        return view;
    }
}