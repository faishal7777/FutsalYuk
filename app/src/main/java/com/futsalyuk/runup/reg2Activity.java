package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.futsalyuk.runup.LOOPJ.Helper;
import com.futsalyuk.runup.futsalyuk.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class reg2Activity extends AppCompatActivity {
    String email, uid;
    public EditText nama, phone, alamat;
    Button btnDaftars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);
        nama = findViewById(R.id.nama);
        phone = findViewById(R.id.phone);
        alamat = findViewById(R.id.alamat);
        btnDaftars = findViewById(R.id.btnDaftar);

        final Spinner mSpinner = (Spinner) findViewById(R.id.spinner_pos);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String >(reg2Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner_pos));
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
            uid = user.getUid();
        }

        btnDaftars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String szPos = mSpinner.getSelectedItem().toString();

                RequestParams params = new RequestParams();
                params.put("uid", uid);
                params.put("email", email);
                params.put("nama", nama.getText().toString());
                params.put("alamat", alamat.getText().toString());
                params.put("phone", phone.getText().toString());
                params.put("position", szPos);

                Helper.post("register", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            if(response.getString("status").equals("success")) {
                                try {
                                    Toast.makeText(reg2Activity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(reg2Activity.this, embo.class));
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
            }
        });
    }
}
