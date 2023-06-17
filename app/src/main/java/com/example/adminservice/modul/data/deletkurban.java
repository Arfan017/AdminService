package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.delete.deletekurban;
import com.example.adminservice.modul.crud.update.updatekurban;

public class deletkurban {
    private deletekurban kurban;
    private String message;
    private boolean status;



    public deletekurban getKurban(){
        return kurban;
    }


    public String getMessage(){
        return message;
    }


    public boolean isStatus(){
        return status;
    }
}
