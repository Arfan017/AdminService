package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.inputinvestaris;


public class dtinventaris {
    private inputinvestaris taris;
    private String message;
    private boolean status;

    public void setTaris(inputinvestaris taris){
        this.taris = taris;
    }

    public inputinvestaris getTaris(){
        return taris;
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
