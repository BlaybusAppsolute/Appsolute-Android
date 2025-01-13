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

import com.appsolute.tium.adpater.BoardAdapter;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment {

    private RecyclerView recyclerView;
    private BoardAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Example data
        List<String> boardItems = new ArrayList<>();
        boardItems.add("First Item");
        boardItems.add("Second Item");
        boardItems.add("Second Item");
        boardItems.add("Second Item");
        boardItems.add("Second Item");
        boardItems.add("Second Item");
        boardItems.add("Second Item");
        boardItems.add("7 Item");


        adapter = new BoardAdapter(boardItems);
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