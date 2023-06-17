package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class data_kegiatan {

    @SerializedName("id_kegiatan")
    private String id_kegiatan;

    @SerializedName("hari")
    private String hari ;

//    @SerializedName("waktu")
//    private String waktu;

    @SerializedName("foto")
    private String foto;

    @SerializedName("kegiatan")
    private String kegiatan;

    @SerializedName("penangungjwb")
    private String penangungjwb;

    public String getId_kegiatan() {
        return id_kegiatan;
    }

    public String getHari() {
        return hari;
    }

//    public String getWaktu() {
//        return waktu;
//    }
    public String getFoto(){
        return foto;
    }

    public String getKegiatan() {
        return kegiatan;
    }
    public String getPenangungjwb() {
        return penangungjwb;
    }


}
