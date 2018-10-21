package com.futsalyuk.runup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.ImageViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.futsalyuk.runup.futsalyuk.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater LayoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    public int[] slide_images = {
            R.drawable.ball1,
            R.drawable.ball2,
            R.drawable.ball3
    };

    public String [] slide_headings = {
            "FUTSAL KUY",
            "VERSUS",
            "TERJANGKAU"
    };

    public String [] slide_descs = {
            "Temukan tim futsalmu dengan mudah",
            "Kamu juga bisa mencari lawan bermain untuk kamu",
            "Dapatkan lapangan terbaik dengan harga terjangkau"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
