package com.example.adminservice.adapter;

public class ModelKegiatan {
    private String id_kegiatan;
    private String tanggal;
    private String waktu;
    private String kegiatan;
    private String penanggungJwb;
    private String keterangan;
    private String gambar;
    private String status;

    public ModelKegiatan(String id_kegiatan, String tanggal, String waktu, String kegiatan, String penanggungJwb, String keterangan, String gambar, String status) {
        this.id_kegiatan = id_kegiatan;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.kegiatan = kegiatan;
        this.penanggungJwb = penanggungJwb;
        this.keterangan = keterangan;
        this.gambar = gambar;
        this.status = status;
    }

    public String getId_kegiatan() {
        return id_kegiatan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }
    public String getKegiatan() {
        return kegiatan;
    }
    public String getPenanggungJwb() {
        return penanggungJwb;
    }

    public String getKeterangan() {
        return keterangan;
    }
    public String getGambar() {
        return gambar;
    }

    public String getStatus() {
        return status;
    }
}
