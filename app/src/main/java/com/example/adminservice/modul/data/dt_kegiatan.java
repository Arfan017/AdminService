package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.inputkegiatan;

public class dt_kegiatan {
    private inputkegiatan kegiatan;
    private String message;
    private boolean status;

    public void setKegiatan(inputkegiatan kegiatan){
        this.kegiatan = kegiatan;
    }

    public inputkegiatan getKegiatan(){
        return kegiatan;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }
}
