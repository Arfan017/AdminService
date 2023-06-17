package com.example.adminservice.adapter;

public class ModelInventory {
    private String id_inventaris;
    private String nama_aset;
    private String jumlah;
    private String satuan;
    private String kondisi;
    private String keterangan;

    public ModelInventory(String id_inventaris, String nama_aset, String jumlah, String satuan, String kondisi, String keterangan) {
        this.id_inventaris = id_inventaris;
        this.nama_aset = nama_aset;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.kondisi = kondisi;
        this.keterangan = keterangan;
    }

    public String getid_inventaris() {
        return id_inventaris;
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
