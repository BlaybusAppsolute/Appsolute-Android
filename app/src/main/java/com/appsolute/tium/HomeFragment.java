package com.appsolute.tium;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsolute.tium.adpater.HomeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private TextView currentMonthYear;
    private Calendar calendar;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    private ConstraintLayout selectedWeekBox;
    private TextView selectedWeek, weekDuration;
    private TextView[] weekTextViews;

    @Nullable
    public View onCreateView(@NonNull View view,@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        calendar = Calendar.getInstance();

        // UI references
        selectedWeekBox = view.findViewById(R.id.selectedWeekBox);
        selectedWeek = view.findViewById(R.id.selectedWeek);
        weekDuration = view.findViewById(R.id.weekDuration);
        weekTextViews = new TextView[]{
                view.findViewById(R.id.week1),
                view.findViewById(R.id.week2),
                // Initialize week3, week4, week5 similarly
        };
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ImageView imageView2 = view.findViewById(R.id.imageView2);

        // imageView2 클릭 이벤트 설정
        imageView2.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), EditProfile.class);
            startActivity(intent);
        });

        // Initialize calendar
        calendar = Calendar.getInstance();


        // Example data
        List<String> HomeItems = new ArrayList<>();
        HomeItems.add("First Item");
        HomeItems.add(" Item");
        HomeItems.add("Second Item");
        HomeItems.add("7 Item");


        adapter = new HomeAdapter(HomeItems);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void updateDateDisplay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM", Locale.getDefault());
        currentMonthYear.setText(dateFormat.format(calendar.getTime()));
    }

    private void updateSelectedWeek(int weekIndex) {
        // Highlight selected week
        for (TextView weekTextView : weekTextViews) {
            weekTextView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        weekTextViews[weekIndex - 1].setBackgroundResource(R.drawable.rounded_background);

        // Calculate and display week duration
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.WEEK_OF_MONTH, weekIndex);
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        String startDate = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(tempCalendar.getTime());
        tempCalendar.add(Calendar.DAY_OF_MONTH, 6);
        String endDate = new SimpleDateFormat("MM.dd", Locale.getDefault()).format(tempCalendar.getTime());

        selectedWeek.setText(weekIndex + "주차");
        weekDuration.setText(startDate + " - " + endDate);

        selectedWeekBox.setVisibility(View.VISIBLE);
    }
}