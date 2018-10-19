package com.futsalyuk.runup.Models;

public class CRUD_User {
    private static String uid;
    private static String email;
    private static String nama;
    private static String tgl_lahir;
    private static String address;
    private static String phone;
    private static String position;
    private static String squad_id;
    private static String tempat_id;

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        CRUD_User.uid = uid;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        CRUD_User.email = email;
    }

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        CRUD_User.nama = nama;
    }

    public static String getTgl_lahir() {
        return tgl_lahir;
    }

    public static void setTgl_lahir(String tgl_lahir) {
        CRUD_User.tgl_lahir = tgl_lahir;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        CRUD_User.address = address;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        CRUD_User.phone = phone;
    }

    public static String getPosition() {
        return position;
    }

    public static void setPosition(String position) {
        CRUD_User.position = position;
    }

    public static String getSquad_id() {
        return squad_id;
    }

    public static void setSquad_id(String squad_id) {
        CRUD_User.squad_id = squad_id;
    }

    public static String getTempat_id() {
        return tempat_id;
    }

    public static void setTempat_id(String tempat_id) {
        CRUD_User.tempat_id = tempat_id;
    }
}
