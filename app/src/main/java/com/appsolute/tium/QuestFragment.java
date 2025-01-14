package com.appsolute.tium;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import com.appsolute.tium.adpater.QuestAdapter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class QuestFragment extends Fragment {

    private TextView currentMonthYear;
    private Calendar calendar;
    private RecyclerView recyclerView;
    private QuestAdapter adapter;

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
        setupWeeks();
        return inflater.inflate(R.layout.fragment_quest, container, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quest, container, false);

        // Initialize UI components
        calendar = Calendar.getInstance();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        currentMonthYear = view.findViewById(R.id.currentMonthYear);
        currentMonthYear.setOnClickListener(this::onMonthYearClicked);

        ImageButton leftArrowButton = view.findViewById(R.id.leftArrowButton);
        ImageButton rightArrowButton = view.findViewById(R.id.rightArrowButton);

        updateDateDisplay();

        leftArrowButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            updateDateDisplay();
        });

        rightArrowButton.setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            updateDateDisplay();
        });

        // Setup RecyclerView with dummy data
        List<String> questItems = new ArrayList<>();
        questItems.add("First Item");
        questItems.add("Second Item");
        questItems.add("Third Item");

        adapter = new QuestAdapter(questItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnMoreButtonClickListener(position -> showBottomSheet(questItems.get(position)));

        // Setup week selection
        selectedWeekBox = view.findViewById(R.id.selectedWeekBox);
        selectedWeek = view.findViewById(R.id.selectedWeek);
        weekDuration = view.findViewById(R.id.weekDuration);
        weekTextViews = new TextView[]{
                view.findViewById(R.id.week1),
                view.findViewById(R.id.week2),
                view.findViewById(R.id.week3),
                view.findViewById(R.id.week4),
                view.findViewById(R.id.week5)
        };
        setupWeeks();

        return view;
    }

    private void updateDateDisplay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM", Locale.getDefault());
        currentMonthYear.setText(dateFormat.format(calendar.getTime()));
    }

    private void setupWeeks() {
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = 0; i < 5; i++) {
            final int weekIndex = i + 1;
            weekTextViews[i].setText(weekIndex + "주차");

            // Set click listener for each week
            weekTextViews[i].setOnClickListener(v -> {
                updateSelectedWeek(weekIndex);
            });
        }

        // Set default selection to current week
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        updateSelectedWeek(currentWeek);
    }

    private void updateSelectedWeek(int weekIndex) {
        // Highlight selected week
        for (TextView weekTextView : weekTextViews) {
            weekTextView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            weekTextView.setTextColor(Color.parseColor("#093F86"));
        }
        weekTextViews[weekIndex - 1].setBackgroundResource(R.drawable.ic_highlight);
        weekTextViews[weekIndex - 1].setTextColor(Color.WHITE);

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

    private void showBottomSheet(String questDetails) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

        bottomSheetDialog.setContentView(bottomSheetView);

        // Set the fixed height and top margin
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // 이 수정에는 필요하지 않습니다.
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // 이 수정에는 필요하지 않습니다.
            }
        });

        ViewGroup.LayoutParams layoutParams = bottomSheetView.getLayoutParams();
        layoutParams.height = (int) (768 * getResources().getDisplayMetrics().density); // dp to pixels
        bottomSheetView.setLayoutParams(layoutParams);

        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int maxHeight = screenHeight - (int) (54 * getResources().getDisplayMetrics().density); // pixels
        behavior.setPeekHeight(maxHeight);

        // Prevent dragging
        behavior.setDraggable(false);

        TextView title = bottomSheetView.findViewById(R.id.bottomSheetTitle);
        TextView content = bottomSheetView.findViewById(R.id.bottomSheetContent);
        Button closeButton = bottomSheetView.findViewById(R.id.closeButton);

        title.setText("퀘스트 상세");
        content.setText(questDetails);

        closeButton.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(bottomSheetView);

        // Set window properties
        Window window = bottomSheetDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.y = 54; // Set top margin
            window.setAttributes(params);
        }

        bottomSheetDialog.show();
    }
    public void onMonthYearClicked(View view) {
        // Month picker dialog or related actions
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (datePicker, year, month, dayOfMonth) -> {
                    calendar.set(year, month, 1);
                    updateDateDisplay();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}