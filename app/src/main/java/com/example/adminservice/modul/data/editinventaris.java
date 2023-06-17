package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.update.updateinvestaris;
import com.example.adminservice.modul.crud.update.updatekurban;

public class editinventaris {
    private updateinvestaris inven;
    private String message;
    private boolean status;


    public updateinvestaris getInven() {
        return inven;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
