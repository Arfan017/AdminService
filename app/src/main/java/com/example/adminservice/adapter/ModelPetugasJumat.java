package com.example.adminservice.adapter;
public class ModelPetugasJumat {
    private String id_penceramah;
    private String tanggal;
    private String penceramah;
    private String tema;
    private String imam_sholat;
    private String muadzin;

    public ModelPetugasJumat(String id_penceramah, String tanggal, String penceramah, String tema, String imam_sholat, String muadzin) {
        this.id_penceramah = id_penceramah;
        this.tanggal = tanggal;
        this.penceramah = penceramah;
        this.tema = tema;
        this.imam_sholat = imam_sholat;
        this.muadzin = muadzin;
    }

    public String getId_penceramah() {
        return id_penceramah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getPenceramah() {
        return penceramah;
    }

    public String getTema() {
        return tema;
    }

    public String getImam_sholat() {
        return imam_sholat;
    }

    public String getMuadzin() {
        return muadzin;
    }
}
