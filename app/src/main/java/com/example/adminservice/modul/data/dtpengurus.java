package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.inputpengurus;

public class dtpengurus {
    private inputpengurus pengurus;
    private String message;
    private boolean status;

    public void setPengurus(inputpengurus pengurus){
        this.pengurus = pengurus;
    }

    public inputpengurus getPengurus (){
        return pengurus;
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
