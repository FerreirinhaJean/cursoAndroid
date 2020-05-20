package com.example.calendarview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class MainActivity extends AppCompatActivity {

    //link github https://github.com/prolificinteractive/material-calendarview

    //private CalendarView calendarView;

    private MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);

       /* calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2015, 1, 1))
                .setMaximumDate(CalendarDay.from(2020, 12, 31))
                .commit();*/

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.i("DATA", String.valueOf(date.getMonth()));
            }
        });

      /*  calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.i("DATA", String.valueOf(date));
            }
        });*/

      /*  calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.i("DATA", "Ano" + year + " Mês: " + month + " Dia: " + dayOfMonth);
                Log.i("DATA", dayOfMonth + "/" + month + "/" + year);
            }
        });*/

    }
}
