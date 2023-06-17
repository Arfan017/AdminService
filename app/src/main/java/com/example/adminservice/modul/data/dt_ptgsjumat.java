package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.create.inputptgs_jumat;
import com.example.adminservice.modul.crud.create.inputqurban;

public class dt_ptgsjumat {
    private inputptgs_jumat jumat;
    private String message;
    private boolean status;


    public void setJumat(inputptgs_jumat jumat){
        this.jumat = jumat;
    }

    public inputptgs_jumat getJumat(){
        return jumat;
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
