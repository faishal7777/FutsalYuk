package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.futsalyuk.runup.futsalyuk.R;

public class list_lapangan extends AppCompatActivity {

    GridLayout mainGrid;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lapangan);
        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            type = (String) b.get("type");
        } else {
            type = "random";
        }

        mainGrid = findViewById(R.id.gridLayout);

        //setevent
        setSingleView(mainGrid);
    }

    private void setSingleView(GridLayout mainGrid) {
        //Loop
        for(int i=0; i<mainGrid.getChildCount(); i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(type.equals("friendly")){
                        startActivity(new Intent(list_lapangan.this, wantplay_friendActivity.class));
                    } else {
                        startActivity(new Intent(list_lapangan.this, wantplayActivity.class));
                    }
                }
            });

        }
    }
}
