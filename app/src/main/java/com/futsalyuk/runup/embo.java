package com.futsalyuk.runup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.futsalyuk.runup.LOOPJ.Helper;
import com.futsalyuk.runup.Models.CRUD_Squad;
import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class embo extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{
    String nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embo);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        RequestParams params1 = new RequestParams("uid", uid);
        Helper.get("show_userInfo", params1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        try {
                            JSONArray jsonarray = new JSONArray(response.getString("data"));
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                CRUD_User.setUid(jsonobject.getString("uid"));
                                CRUD_User.setEmail(jsonobject.getString("email"));
                                CRUD_User.setNama(jsonobject.getString("nama"));
                                CRUD_User.setTgl_lahir(jsonobject.getString("tgl_lahir"));
                                CRUD_User.setAddress(jsonobject.getString("address"));
                                CRUD_User.setPhone(jsonobject.getString("phone"));
                                CRUD_User.setPosition(jsonobject.getString("position"));
                                CRUD_User.setSquad_id(jsonobject.getString("squad_id"));
                                CRUD_User.setTempat_id(jsonobject.getString("tempat_id"));
                            }
                        } catch (JSONException e) {
                            Log.d("Err:", String.valueOf(e));
                            e.printStackTrace();
                        }
                    } else {
                        startActivity(new Intent(embo.this, reg2Activity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Toast.makeText(embo.this, "Hi "+CRUD_Squad.getSquad_name(), Toast.LENGTH_SHORT).show();

        loadFragment(new PlayFragment());
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return false;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.nav_tim:
                fragment = new TimFragment();
                break;

            case R.id.nav_play:
                fragment = new PlayFragment();
                break;

            case R.id.nav_profil:
                fragment = new ProfilFragment();
                break;

        }
        return loadFragment(fragment);
    }
}
