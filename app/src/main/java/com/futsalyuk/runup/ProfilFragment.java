package com.futsalyuk.runup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;

public class ProfilFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Left", "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // -- inflate the layout for this fragment
        final View myInflatedView = inflater.inflate(R.layout.fragment_profil, container,false);

        // Set the Text to try this out
        TextView t_Name = myInflatedView.findViewById(R.id.namaUser);
        TextView t_Pos = myInflatedView.findViewById(R.id.statUser);
        t_Name.setText(CRUD_User.getNama());
        t_Pos.setText(CRUD_User.getPosition());

        return myInflatedView;
    }
}
