package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class dataptgsjumat {

    @SerializedName("id_penceramah")
    private String id_penceramah;

    @SerializedName("penceramah")
    private String penceramah;

    @SerializedName("tema")
    private String tema;

    @SerializedName("imam_shalat")
    private String imam_shalat;

    @SerializedName("ptgs_adzan")
    private String adzan;

    public dataptgsjumat() {
    }

    public String getId_penceramah() {return id_penceramah;}
    public String getPenceramah() {return penceramah;}
    public String getTema() {return tema;}
    public String getImam_shalat() {return imam_shalat;}
    public String getAdzan() {return adzan;}
}
