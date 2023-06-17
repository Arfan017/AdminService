package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.inputqurban;

public class dtqurban {
    private inputqurban kurban;
    private String message;
    private boolean status;


    public void setKurban(inputqurban kurban){
        this.kurban = kurban;
    }

    public inputqurban getKurban(){
        return kurban;
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
