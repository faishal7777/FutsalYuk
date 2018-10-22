package com.futsalyuk.runup;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
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

public class progresMatching extends AppCompatActivity {

    ProgressBar progressBar;
    public int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_matching);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



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

        progressBar = findViewById(R.id.progres_bar);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progresAnimation();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // End
        stopExecutorClicked();
        finish();
    }


    private Timer timerExecutor = new Timer();
    private TimerTask doAsynchronousTaskExecutor;

    public void startBackgroundPerformExecutor() {
        final Handler handler = new Handler();
        doAsynchronousTaskExecutor = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            Log.i("Background Perform",
                                    "-------> Text from Background Perform");

                            RequestParams params = new RequestParams();
                            params.put("match_id", mId);

                            Helper.get("check_match", params, new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    try {
                                        if (response.getString("status").equals("matched")) {
                                            Toast.makeText(progresMatching.this, "Menemukan tim tandang!", Toast.LENGTH_SHORT).show();
                                            stopExecutorClicked();
                                        } else {
                                            Toast.makeText(progresMatching.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timerExecutor.schedule(doAsynchronousTaskExecutor, 1000, 1000);
    }

    public void stopExecutorClicked() {
        RequestParams params = new RequestParams();
        params.put("match_id", mId);

        Helper.post("delete_match", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        Toast.makeText(progresMatching.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        doAsynchronousTaskExecutor.cancel();
        timerExecutor.cancel();
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
                        Toast.makeText(progresMatching.this, "Anda bagian dari tim kandang!", Toast.LENGTH_SHORT).show();
                        mId = match_id;

                        startBackgroundPerformExecutor();
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
                        Toast.makeText(progresMatching.this, "Anda bagian dari tim tandang!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(progresMatching.this, temu_temanActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void progresAnimation(){
        progresBarAnimated anim = new progresBarAnimated(this, progressBar, 0f, 100f);
        anim.setDuration(60000);
        progressBar.setAnimation(anim);
    }

    class progresBarAnimated extends Animation {

        Context context;
        private ProgressBar progressBar;
        private float from;
        private float to;

        public progresBarAnimated(Context context, ProgressBar progressBar, float from, float to){
            this.context = context;
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int)value);
            //textView.setText((int)value+" %");

            if (value == to){
                startActivity(new Intent(progresMatching.this, temu_temanActivity.class));
            }
        }
    }
}
