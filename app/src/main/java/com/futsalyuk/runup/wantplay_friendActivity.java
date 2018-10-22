package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.futsalyuk.runup.futsalyuk.R;

public class wantplay_friendActivity extends AppCompatActivity {

    private Button mNextFriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantplay_friend);

        mNextFriend =  findViewById(R.id.next_play_friend);
        mNextFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(wantplay_friendActivity.this, timkawan.class));
            }
        });
    }
}
