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

import com.futsalyuk.runup.LOOPJ.Helper;
import com.futsalyuk.runup.Models.CRUD_Squad;
import com.futsalyuk.runup.Models.CRUD_User;
import com.futsalyuk.runup.futsalyuk.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TimFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Left", "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
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

        // Set the Text to try this out
        TextView t_Id = myInflatedView.findViewById(R.id.squadId);
        TextView t_Name = myInflatedView.findViewById(R.id.squadName);
        TextView t_Bio = myInflatedView.findViewById(R.id.squadBio);
        t_Id.setText(CRUD_Squad.getSquad_id());
        t_Name.setText(CRUD_Squad.getSquad_name());
        t_Bio.setText(CRUD_Squad.getSquad_bio());

        return myInflatedView;
    }
}
