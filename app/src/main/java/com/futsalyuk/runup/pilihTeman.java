package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;

import com.futsalyuk.runup.futsalyuk.R;

public class pilihTeman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_teman);

        ListView listView = (ListView) findViewById(R.id.lv_timkawanku);
        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(pilihTeman.this, temu_temanActivity.class));
            }
        });
    }
}
