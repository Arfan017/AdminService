package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.delete.deleteinventaris;
import com.example.adminservice.modul.crud.update.updateinvestaris;

public class deletinventaris {
    private deleteinventaris inven;
    private String message;
    private boolean status;


    public deleteinventaris getInven() {
        return inven;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
