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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.futsalyuk.runup.LOOPJ.Helper;
import com.futsalyuk.runup.Models.CRUD_Squad;
import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimFragment extends Fragment {

    String Namas[]={
            "Faishal Rusydan",
            "Sukantoro",
            "Mercell",
            "Barry Liku",
            "Manatodo Siahaan"
    };

    String Posisis[]={
            "Flank",
            "Keeper",
            "Mid",
            "Flank",
            "Keeper",
    };

    int Imgs[]={
            R.drawable.linee,
            R.drawable.linee,
            R.drawable.linee,
            R.drawable.linee,
            R.drawable.linee
    };

    private Button mTimkawan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Left", "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        // -- inflate the layout for this fragment
        final View myInflatedView = inflater.inflate(R.layout.fragment_tim, container,false);

        RequestParams params = new RequestParams();
        params.put("type", "squad_info");
        params.put("squad_id", CRUD_User.getSquad_id());
        Helper.get("show_squad", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if(response.getString("status").equals("success")) {
                        try {
                            JSONArray jsonarray = new JSONArray(response.getString("data"));
                            TextView t_Id = myInflatedView.findViewById(R.id.squadId);
                            TextView t_Name = myInflatedView.findViewById(R.id.squadName);
                            TextView t_Bio = myInflatedView.findViewById(R.id.squadBio);
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                if(CRUD_Squad.getSquad_name() == null || CRUD_Squad.getSquad_bio() == null || CRUD_Squad.getSquad_id() == null){
                                    t_Id.setText(jsonobject.getString("squad_id"));
                                    t_Name.setText(jsonobject.getString("nama"));
                                    t_Bio.setText(jsonobject.getString("bio"));
                                }
                                CRUD_Squad.setSquad_id(jsonobject.getString("squad_id"));
                                CRUD_Squad.setSquad_name(jsonobject.getString("nama"));
                                CRUD_Squad.setSquad_bio(jsonobject.getString("bio"));
                            }
                        } catch (JSONException e) {
                            Log.d("Err:", String.valueOf(e));
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mTimkawan = myInflatedView.findViewById(R.id.btntimkawan);
        mTimkawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), timkawan.class));
            }
        });

        ListView listView = myInflatedView.findViewById(R.id.lv_tim);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        TextView t_Id = myInflatedView.findViewById(R.id.squadId);
        TextView t_Name = myInflatedView.findViewById(R.id.squadName);
        TextView t_Bio = myInflatedView.findViewById(R.id.squadBio);
        if(CRUD_Squad.getSquad_name() != null && CRUD_Squad.getSquad_bio() != null && CRUD_Squad.getSquad_id() != null){
            t_Id.setText(CRUD_Squad.getSquad_id());
            t_Name.setText(CRUD_Squad.getSquad_name());
            t_Bio.setText(CRUD_Squad.getSquad_bio());
        }

        // Set the Text to try this out

        return myInflatedView;

        //listViewAnggota_Adapter = new listViewAnggota_Adapter(getActivity());
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Imgs.length;
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
            view = getLayoutInflater().inflate(R.layout.anggota_tim, null);

            ImageView mImageview = (ImageView)view.findViewById(R.id.img_person);
            TextView mNama = (TextView)view.findViewById(R.id.nama);
            TextView mPosisi = (TextView)view.findViewById(R.id.posisi);

            mImageview.setImageResource(Imgs[position]);
            mNama.setText(Namas[position]);
            mPosisi.setText(Posisis[position]);

            return view;
        }
    }

}
