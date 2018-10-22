package com.futsalyuk.runup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

import com.futsalyuk.runup.futsalyuk.R;

public class progresMatching extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_matching);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progres_bar);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progresAnimation();
    }

    public void progresAnimation(){
        progresBarAnimated anim = new progresBarAnimated(this, progressBar, 0f, 100f);
        anim.setDuration(8000);
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
                startActivity(new Intent(progresMatching.this, timkawan.class));
            }
        }
    }
}
