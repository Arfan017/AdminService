package com.example.adminservice.modul.crud.create;

public class inputpengurus {
    private String id_pengurus;
    private String nama_pengurus;
    private String jabatan;
    private String no_hp;

    public String getId_pengurus() {return id_pengurus;}

    public void setId_pengurus(String id_pengurus) {
        this.id_pengurus = id_pengurus;
    }

    public String getNama_pengurus() {
        return nama_pengurus;
    }

    public void setNama_pengurus(String nama_pengurus) {
        this.nama_pengurus = nama_pengurus;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

}
