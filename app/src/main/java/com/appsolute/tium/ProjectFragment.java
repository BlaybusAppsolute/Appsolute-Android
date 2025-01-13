package com.appsolute.tium;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsolute.tium.adpater.ProjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProjectFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProjectAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Example data
        List<String> projectItems = new ArrayList<>();
        projectItems.add("First Item");
        projectItems.add(" Item");
        projectItems.add("Second Item");
        projectItems.add("7 Item");


        adapter = new ProjectAdapter(projectItems);
        recyclerView.setAdapter(adapter);

        // Update data dynamically (optional)
        // Uncomment below to simulate adding more items
        /*
        boardItems.add("Third Item");
        boardItems.add("Fourth Item");
        adapter.updateData(boardItems);
        */
    }
}