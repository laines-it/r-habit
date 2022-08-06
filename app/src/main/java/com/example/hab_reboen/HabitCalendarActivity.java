package com.example.hab_reboen;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class HabitCalendarActivity extends AppCompatActivity {
    FrameLayout framecal;
    CalendarView calv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_calendar);
        framecal = findViewById(R.id.frame_cal);
        calv = findViewById(R.id.calendarview);
    }
}