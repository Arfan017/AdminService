package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.dt_zakat;

public class inputzakat {
    private dt_zakat data;
    private String message;
    private boolean status;

    public void setData(dt_zakat data){
        this.data = data;
    }

    public dt_zakat getData(){
        return data;
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
