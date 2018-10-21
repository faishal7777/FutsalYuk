package com.futsalyuk.runup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.futsalyuk.runup.futsalyuk.R;

public class PlayFragment extends Fragment {

    private ImageButton mbtnRandom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_play, null);

        mbtnRandom = (ImageButton) view.findViewById(R.id.btnRandom);
        mbtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), wantplayActivity.class));
            }
        });

        return view;
    }
}
