package com.example.adminservice.adapter;

public class ModelPengurus {
    private String id_pengurus;
    private String nama_pengurus;
    private String jabatan;
    private String no_hp;

    public ModelPengurus(String id_pengurus, String nama_pengurus, String jabatan, String no_hp) {
        this.id_pengurus = id_pengurus;
        this.nama_pengurus = nama_pengurus;
        this.jabatan = jabatan;
        this.no_hp = no_hp;
    }

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
