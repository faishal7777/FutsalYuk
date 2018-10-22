package com.futsalyuk.runup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfilFragment extends Fragment implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

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
        Button btnLogout = myInflatedView.findViewById(R.id.logoutBtn);
        btnLogout.setOnClickListener(this);
        Button btnSettings = myInflatedView.findViewById(R.id.settingsBtn);
        btnSettings.setOnClickListener(this);

        // Set the Text to try this out
        TextView t_Name = myInflatedView.findViewById(R.id.namaUser);
        TextView t_Pos = myInflatedView.findViewById(R.id.statUser);
        t_Name.setText(CRUD_User.getNama());
        t_Pos.setText(CRUD_User.getPosition());

        return myInflatedView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.logoutBtn:
                FirebaseAuth.getInstance().signOut();
                Intent I1 = new Intent(new Intent(v.getContext(), login_activity.class));
                startActivity(I1);
                break;

            case R.id.settingsBtn:
                FirebaseAuth.getInstance().signOut();
                Intent I2 = new Intent(new Intent(v.getContext(), reg2Activity.class));
                startActivity(I2);
                break;

            default:
                break;
        }

    }
}
