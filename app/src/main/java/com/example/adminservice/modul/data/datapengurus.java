package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class datapengurus {

    @SerializedName("id_pengurus")
    private String id_pengurus;

    @SerializedName("nama_pengurus")
    private String nama_pengurus;

    @SerializedName("jabatan")
    private String jabatan;

    @SerializedName("no_hp")
    private String no_hp;





    public String getId_pengurus() {
        return id_pengurus;
    }

    public String getNama_pengurus() {
        return nama_pengurus;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getNo_hp() {
        return no_hp;
    }



}
