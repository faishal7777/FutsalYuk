package com.futsalyuk.runup;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.futsalyuk.runup.LOOPJ.Helper;
import com.futsalyuk.runup.Models.CRUD_Squad;
import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class temu_temanActivity extends AppCompatActivity {
    TextView timHome, timAway;
    int match_id, home_id, away_id;

    public Button mNextnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temu_teman);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            match_id = (int) b.get("match_id");
        }

        timHome = findViewById(R.id.tim_home);
        timAway = findViewById(R.id.tim_away);

        RequestParams params = new RequestParams();
        params.put("match_id", match_id);
        Helper.get("show_match_details", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        try {
                            home_id = response.getInt("squad_id_home");
                            away_id = response.getInt("squad_id_away");
                            setSquadHome();
                            setSquadAway();
                        } catch (JSONException e) {
                            Log.d("Err:", String.valueOf(e));
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mNextnext = findViewById(R.id.nextnext);
        mNextnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(temu_temanActivity.this, pilih_tempat.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Apa anda serius akan meninggalkan game?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        temu_temanActivity.super.onBackPressed();
                        startActivity(new Intent(temu_temanActivity.this, embo.class));
                    }
                }).create().show();
    }

    public void setSquadHome(){
        RequestParams params = new RequestParams();
        params.put("type", "squad_info");
        params.put("squad_id", home_id);
        Helper.get("show_squad", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        try {
                            JSONArray jsonarray = new JSONArray(response.getString("data"));
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                timHome.setText(jsonobject.getString("nama"));
                            }
                        } catch (JSONException e) {
                            Log.d("Err:", String.valueOf(e));
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setSquadAway(){
        RequestParams params = new RequestParams();
        params.put("type", "squad_info");
        params.put("squad_id", away_id);
        Helper.get("show_squad", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        try {
                            JSONArray jsonarray = new JSONArray(response.getString("data"));
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                timAway.setText(jsonobject.getString("nama"));
                            }
                        } catch (JSONException e) {
                            Log.d("Err:", String.valueOf(e));
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
