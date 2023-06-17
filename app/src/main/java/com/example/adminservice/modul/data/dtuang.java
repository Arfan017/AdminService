package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.inputuang;

public class dtuang {
    private inputuang uang;
    private String message;
    private boolean status;

    public void setData(inputuang uang){
        this.uang = uang;
    }

    public inputuang getuang(){
        return uang;
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
