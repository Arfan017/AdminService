package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.update.updatekurban;
import com.example.adminservice.modul.crud.update.updatezakat;

public class editzakat {
    private updatezakat zakat;
    private String message;
    private boolean status;


    public updatezakat getZakat() {
        return zakat;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
