package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("password")
    private String jabatan;
    @SerializedName("nama_pengurus")
    private String nama;

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
