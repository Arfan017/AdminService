package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.delete.deletekeuangan;
import com.example.adminservice.modul.crud.update.updateuang;

public class deletuang {
    private deletekeuangan uang;
    private String message;
    private boolean status;


    public deletekeuangan getUang() {
        return uang;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
