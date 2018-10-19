package com.futsalyuk.runup;

import android.app.DatePickerDialog;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tempat);

        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*calendar = Calendar.getInstance();
                year = Calendar.get(Calendar.YEAR);
                month = Calendar.get(Calendar.MONTH);
                dayofmonth = Calendar.get(Calendar.DAY_OF_MONTH);*/
                datePickerDialog = new DatePickerDialog(pilih_tempat.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "/" + month + "/" + year);
                            }
                        }, 0, 0, 0);
                datePickerDialog.show();
            }
        });
    }
}
