package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class datakeuangan {

    @SerializedName("id_keuangan")
    private String id_keuangan;

    @SerializedName("nama_pemberi")
    private String nama_pemberi;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("alamat")
    private String alamat;
    @SerializedName("jumlah")
    private String jumlah;
    @SerializedName("harian")
    private String harian;


    public String getId_keuangan() {
        return id_keuangan;
    }

    public String getNama_pemberi() {
        return nama_pemberi;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getAlamat() {
        return alamat;
    }


    public String getJumlah() {
        return jumlah;
    }

    public String getHarian() {
        return harian;
    }
}
