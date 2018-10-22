package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.futsalyuk.runup.futsalyuk.R;

public class timkawan extends AppCompatActivity {

    String Namatim[]={
            "Soap King's",
            "Lionheart",
            "Geronimo",
            "Lord Futsale",
            "Looprince"
    };

    String Biotim[]={
            "Play clean with us!",
            "Work Hard, Play Hard",
            "Pla like a jet",
            "555",
            "Crazy Rich Footballer",
    };

    int Imgtim[]={
            R.drawable.linee,
            R.drawable.blank_img,
            R.drawable.blank_img,
            R.drawable.blank_img,
            R.drawable.blank_img
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkawan);

        ListView list1 = findViewById(R.id.lv_timkawan);

        list1.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        startActivity(new Intent(timkawan.this, temu_temanActivity.class));
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.timkawan_menu, menu);
        return true;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Imgtim.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.timkawan_single, null);

            ImageView mImgtimkawan = (ImageView)view.findViewById(R.id.img_timkawan);
            TextView mNamatim = (TextView)view.findViewById(R.id.namatimkawan);
            TextView mBiotim = (TextView)view.findViewById(R.id.biotimkawan);

            mImgtimkawan.setImageResource(Imgtim[position]);
            mNamatim.setText(Namatim[position]);
            mBiotim.setText(Biotim[position]);

            return view;
        }
    }
}
