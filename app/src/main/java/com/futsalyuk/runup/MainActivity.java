package com.futsalyuk.runup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalyuk.runup.futsalyuk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;

    private Button mNextBtn;
    private Button mBackBtn;

    private int mCurrentPage;

    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(MainActivity.this, embo.class);
                    startActivity(I);
                }
            }
        };

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nxtBtn);
        mBackBtn = (Button) findViewById(R.id.bckBtn);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

    }

    public void addDotsIndicator(int position){

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++ ){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;

            if (i == 0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("");
                mNextBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        mSlideViewPager.setCurrentItem(mCurrentPage+1);
                    }
                });
                mBackBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        mSlideViewPager.setCurrentItem(mCurrentPage-1);
                    }
                });
            }   else if(i == mDots.length-1){
                    mNextBtn.setEnabled(true);
                    mBackBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);
                    mNextBtn.setText("Finish");
                    mBackBtn.setText("Back");
                    mNextBtn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            startActivity(new Intent(MainActivity.this, login_activity.class));
                        }
                    });
                    mBackBtn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            mSlideViewPager.setCurrentItem(mCurrentPage-1);
                        }
                    });
                }   else{
                       mNextBtn.setEnabled(true);
                       mBackBtn.setEnabled(true);
                       mBackBtn.setVisibility(View.VISIBLE);
                       mNextBtn.setText("Next");
                       mBackBtn.setText("Back");
                       mNextBtn.setOnClickListener(new View.OnClickListener(){
                           @Override
                           public void onClick(View view){
                               mSlideViewPager.setCurrentItem(mCurrentPage+1);
                           }
                       });
                       mBackBtn.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                mSlideViewPager.setCurrentItem(mCurrentPage-1);
                            }
                       });
                    }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
