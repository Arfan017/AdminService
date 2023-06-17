package com.example.adminservice.modul.data;

import com.google.gson.annotations.SerializedName;

public class DataModel {

	@SerializedName("id_zakat")
	private String id_zakat;

	@SerializedName("Jenis_zakat")
	private String Jenis_zakat;

	@SerializedName("jumlah_beri")
	private String jumlah_beri;

	@SerializedName("hasil")
	private String hasil;



	public String getId_zakat() {
		return id_zakat;
	}

	public String getJenis_zakat() {
		return Jenis_zakat;
	}

	public String getJumlah_beri() {
		return jumlah_beri;
	}

	public String getHasil() {
		return hasil;
	}
}