package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.futsalyuk.runup.Models.CRUD_Squad;
import com.futsalyuk.runup.futsalyuk.R;

public class wantplayActivity extends AppCompatActivity {
    private Button mNextPlay;
    TextView squadName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantplay);

        squadName = findViewById(R.id.squad);
        mNextPlay = findViewById(R.id.next_play);
        mNextPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(wantplayActivity.this, progresMatching.class));
            }
        });

        squadName.setText(CRUD_Squad.getSquad_name());
    }
}
