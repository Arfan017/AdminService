package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class datainvestoris {

    @SerializedName("id")
    private String id;

    @SerializedName("nama_aset")
    private String nama_aset;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("kondisi")
    private String kondisi;

    @SerializedName("keterangan")
    private String keterangan;


    public String getId() {
        return id;
    }

    public String getNama_aset() {
        return nama_aset;
    }
    public String getJumlah() {
        return jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public String getKeterangan() {
        return keterangan;
    }

}
