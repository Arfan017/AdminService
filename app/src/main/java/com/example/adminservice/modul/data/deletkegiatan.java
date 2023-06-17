package com.example.adminservice.modul.data;

import com.example.adminservice.modul.crud.delete.deletekegiatan;
import com.example.adminservice.modul.crud.update.updatekegiatan;

public class deletkegiatan {
    private deletekegiatan kegiatan;
    private String message;
    private boolean status;

    public deletekegiatan getKegiatan() {
        return kegiatan;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }
}
