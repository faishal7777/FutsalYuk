package com.futsalyuk.runup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.futsalyuk.runup.LOOPJ.Helper;
import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class wantplayActivity extends AppCompatActivity {
    private Button mNextPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantplay);

        mNextPlay = findViewById(R.id.next_play);
        mNextPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(wantplayActivity.this, progresMatching.class));
            }
        });
    }
}
