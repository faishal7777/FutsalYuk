package com.futsalyuk.runup.Models;

import android.util.Log;

import com.futsalyuk.runup.LOOPJ.Helper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CRUD_Squad {
    private static String squad_id;
    private static String squad_name;
    private static String squad_bio;

    public static String getSquad_id() {
        return squad_id;
    }

    public static void setSquad_id(String squad_id) {
        CRUD_Squad.squad_id = squad_id;
    }

    public static String getSquad_name() {
        return squad_name;
    }

    public static void setSquad_name(String squad_name) {
        CRUD_Squad.squad_name = squad_name;
    }

    public static String getSquad_bio() {
        return squad_bio;
    }

    public static void setSquad_bio(String squad_bio) {
        CRUD_Squad.squad_bio = squad_bio;
    }
}
