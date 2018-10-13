package com.futsalyuk.runup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.futsalyuk.runup.futsalyuk.R;

public class reg2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);

        Spinner mSpinner = (Spinner) findViewById(R.id.spinner_pos);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String >(reg2Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner_pos));
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);
        
    }
}
