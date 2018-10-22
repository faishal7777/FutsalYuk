package com.futsalyuk.runup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import android.widget.TimePicker;
import android.widget.DatePicker;

import com.futsalyuk.runup.futsalyuk.R;


public class pilih_tempat extends AppCompatActivity {
    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;

    //int year;
    //int month;
    //int dayOfMonth;
    //Calendar calendar;

    private Button mainkann;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tempat);

        /*selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(pilih_tempat.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "-" + (month+1) + "-"    + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });*/

        mainkann = findViewById(R.id.mainkann);
        mainkann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pilih_tempat.this, embo.class));
            }
        });
    }
}