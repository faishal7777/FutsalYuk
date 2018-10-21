package com.futsalyuk.runup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    public int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wantplay);

        // Get Match Available
        RequestParams params = new RequestParams();
        params.put("lapangan_id", 5);
        params.put("jam", 1);
        Helper.get("show_match_available", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        try {
                            JSONArray jsonarray = new JSONArray(response.getString("match_avail"));

                            Random r = new Random();
                            int id1 = r.nextInt(jsonarray.length());
                            int match_id = jsonarray.getJSONObject(id1).getInt("match_id");

                            joinMatch(match_id);
                        } catch (JSONException e) {
                            Log.d("Err:", String.valueOf(e));
                            e.printStackTrace();
                        }
                    } else {
                        createMatch();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Timer timer;
    private Handler handler = new Handler();

    //To stop timer
    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    //To start timer
    private void startTimer(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        RequestParams params = new RequestParams();
                        params.put("match_id", mId);

                        Helper.post("check_match", params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    if (response.getString("status").equals("matched")) {
                                        Toast.makeText(wantplayActivity.this, "Menemukan tim tandang!", Toast.LENGTH_SHORT).show();
                                        stopTimer();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        };
        timer.schedule(timerTask, 5000, 5000);
    }

    private void createMatch(){
        RequestParams params = new RequestParams();
        params.put("is_matchMaking", 1);
        params.put("squad_id_home", CRUD_User.getSquad_id());
        params.put("lapangan_id", 5);
        params.put("jam", 1);

        Helper.post("create_match", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        final int match_id = response.getInt("match_id");
                        Toast.makeText(wantplayActivity.this, "Anda bagian dari tim kandang!", Toast.LENGTH_SHORT).show();
                        mId = match_id;
                        startTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void joinMatch(int match_id){
        RequestParams params = new RequestParams();
        params.put("match_id", match_id);
        params.put("squad_id_away", CRUD_User.getSquad_id());
        params.put("lapangan_id", 5);
        params.put("jam", 1);

        Helper.post("join_match", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        Toast.makeText(wantplayActivity.this, "Anda bagian dari tim tandang!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
