package com.futsalyuk.runup;

import android.content.Intent;
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

    private int checkMatch(final int match_id){
        final int[] matched = new int[1];
        RequestParams params = new RequestParams();
        params.put("match_id", match_id);

        Helper.post("check_match", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("matched")) {
                        matched[0] = 1;
                    } else {
                        matched[0] = 0;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return matched[0];
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

                        final Timer timer = new Timer();

                        final TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                if(checkMatch(match_id) == 1){
                                    Toast.makeText(wantplayActivity.this, "Menemukan tim tandang!", Toast.LENGTH_SHORT).show();
                                    timer.cancel();
                                    timer.purge();
                                }
                            }
                        };

                        timer.schedule(task, 0, 1000);
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
                        Toast.makeText(wantplayActivity.this, "Anda bagian dari tim kandang!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
