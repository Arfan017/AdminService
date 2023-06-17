package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class datakurban {

    @SerializedName("id_qurban")
    private String id_qurban;

    @SerializedName("pemberi_kurban")
    private String pemberi_kurban;

    @SerializedName("jenis_hewan")
    private String jenis_hewan;





    public String getId_qurban() {
        return id_qurban;
    }

    public String getPemberi_kurban() {
        return pemberi_kurban;
    }

    public String getJenis_hewan() {
        return jenis_hewan;
    }


}
