package com.example.adminservice.api;
import com.example.adminservice.modul.data.DataModel;
import com.example.adminservice.modul.data.data_kegiatan;
import com.example.adminservice.modul.data.datainvestoris;
import com.example.adminservice.modul.data.datakeuangan;
import com.example.adminservice.modul.data.datakurban;
import com.example.adminservice.modul.data.datapengurus;
import com.example.adminservice.modul.data.dataptgsjumat;
import com.example.adminservice.modul.data.deletinventaris;
import com.example.adminservice.modul.data.deletkegiatan;
import com.example.adminservice.modul.data.deletkurban;
import com.example.adminservice.modul.data.deletpengurus;
import com.example.adminservice.modul.data.deletptgsjumat;
import com.example.adminservice.modul.data.deletuang;
import com.example.adminservice.modul.data.deletzakat;
import com.example.adminservice.modul.data.dt_kegiatan;
import com.example.adminservice.modul.data.dt_ptgsjumat;
import com.example.adminservice.modul.data.dtinventaris;
import com.example.adminservice.modul.data.dtpengurus;
import com.example.adminservice.modul.data.dtqurban;
import com.example.adminservice.modul.data.dtuang;
import com.example.adminservice.modul.data.editinventaris;
import com.example.adminservice.modul.data.editkegiatan;
import com.example.adminservice.modul.data.editkurban;
import com.example.adminservice.modul.data.editpengurus;
import com.example.adminservice.modul.data.editptgsjumat;
import com.example.adminservice.modul.data.edituang;
import com.example.adminservice.modul.data.editzakat;
import com.example.adminservice.modul.data.inputzakat;
import com.example.adminservice.modul.data.log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Login.php")
    Call<log> login (
            @Field("nama_pengurus") String username,
            @Field("password") String password
    );

    @GET("data_zakat.php")
    Call<List<DataModel>> getData();

    @GET("investaris.php")
    Call<List<datainvestoris>> getdata();

    @GET("keuangan.php")
    Call<List<datakeuangan>> getuang();
    @GET("kurban.php")
    Call<List<datakurban>> getkurban();

    @GET("pengurus.php")
    Call<List<datapengurus>> getpengurus();

    @GET("petugas_jumat.php")
    Call<List<dataptgsjumat>> getjumat();

    @GET("kegiatanmesjid.php")
    Call<List<data_kegiatan>> kegiatan();

    @FormUrlEncoded
    @POST("inputdt_zakat.php")
    Call<inputzakat> input(
            @Field("id_zakat") String id_zakat,
            @Field("jenis_zakat") String jenis_zakat,
            @Field("jumlah_beri") String Jumlah_zakat,
            @Field("hasil") String hasil
    );

    @FormUrlEncoded
    @POST("edit_zakat.php")
    Call<editzakat> editzakat(
            @Field("id_zakat") String id_zakat,
            @Field("jenis_zakat") String jenis_zakat,
            @Field("jumlah_beri") String Jumlah_zakat,
            @Field("hasil") String hasil
    );

    @FormUrlEncoded
    @POST("delet_zakat.php")
    Call<deletzakat> deletzakat(
            @Field("id_zakat") String id_zakat
    );

    @FormUrlEncoded
    @POST("inputuang.php")
    Call<dtuang> uang(
            @Field("id_keuangan") String id_keuangan,
            @Field("nama_pemberi") String nama_pemberi,
            @Field("no_hp") String no_hp,
            @Field("alamat") String harian,
            @Field("harian") String jumlah,
            @Field("jumlah") String alamat
    );

    @FormUrlEncoded
    @POST("edit_uang.php")
    Call<edituang> edituang(
            @Field("id_keuangan") String id_keuangan,
            @Field("nama_pemberi") String nama_pemberi,
            @Field("no_hp") String no_hp,
            @Field("harian") String harian,
            @Field("jumlah") String jumlah,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("delet_uang.php")
    Call<deletuang> deletuang(
            @Field("id_keuangan") String id_keuangan
    );

    @FormUrlEncoded
    @POST("inputkurban.php")
    Call<dtqurban> kurban(
            @Field("id_qurban") String id_keuangan,
            @Field("pemberi_kurban") String nama_pemberi,
            @Field("jenis_hewan") String jenis_hewan
    );

    @FormUrlEncoded
    @POST("editkurban.php")
    Call<editkurban> editkurban(
            @Field("id_qurban") String id_keuangan,
            @Field("pemberi_kurban") String nama_pemberi,
            @Field("jenis_hewan") String jenis_hewan
    );

    @FormUrlEncoded
    @POST("deletkurban.php")
    Call<deletkurban> deletkurban(
            @Field("id_qurban") String id_keuangan
    );

    @FormUrlEncoded
    @POST("input_ptgs_jumat.php")
    Call<dt_ptgsjumat> juamt(
            @Field("id_penceramah") String id_penceramah,
            @Field("penceramah") String penceramah,
            @Field("tema") String tema,
            @Field("imam_shalat") String imam_shalat,
            @Field("ptgs_adzan") String ptgs_adzan
    );

    @FormUrlEncoded
    @POST("edit_ptgsmenjid.php")
    Call<editptgsjumat> editjuamt(
            @Field("id_penceramah") String id_penceramah,
            @Field("penceramah") String penceramah,
            @Field("tema") String tema,
            @Field("imam_shalat") String imam_shalat,
            @Field("ptgs_adzan") String ptgs_adzan
    );

    @FormUrlEncoded
    @POST("delet_ptgsmenjid.php")
    Call<deletptgsjumat> deletjuamt(
            @Field("id_penceramah") String id_penceramah
    );

    @FormUrlEncoded
    @POST("input_pengurus.php")
    Call<dtpengurus> urus(
            @Field("id_pengurus") String id_pengurus,
            @Field("nama_pengurus") String nama_pengurus,
            @Field("jabatan") String jabatan,
            @Field("no_hp") String no_hp
    );

    @FormUrlEncoded
    @POST("edit_pengurus.php")
    Call<editpengurus> editurus(
            @Field("id_pengurus") String id_pengurus,
            @Field("nama_pengurus") String nama_pengurus,
            @Field("jabatan") String jabatan,
            @Field("no_hp") String no_hp
    );

    @FormUrlEncoded
    @POST("delet_pengurus.php")
    Call<deletpengurus> deleturus(
            @Field("id_pengurus") String id_pengurus
    );

    @FormUrlEncoded
    @POST("input_investaris.php")
    Call<dtinventaris> inventaris(
            @Field("id") String id,
            @Field("nama_aset") String nama_aset,
            @Field("satuan") String satuan,
            @Field("jumlah") String jumlah,
            @Field("kondisi") String kondisi,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @POST("edit_investaris.php")
    Call<editinventaris> editinventaris(
            @Field("id") String id,
            @Field("nama_aset") String nama_aset,
            @Field("satuan") String satuan,
            @Field("jumlah") String jumlah,
            @Field("kondisi") String kondisi,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @POST("delet_investaris.php")
    Call<deletinventaris> deletinventaris(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("input_kegiatan.php")
    Call<dt_kegiatan> kegiatan(
            @Field("id_kegiatan") String id_kegiatan,
            @Field("hari") String hari,
            @Field("waktu") String waktu,
            @Field("kegiatan") String kegiatan,
            @Field("penangungjwb") String penangungjwb
    );

    @FormUrlEncoded
    @POST("_input_kegiatan.php")
    Call<dt_kegiatan> _kegiatan(
//            @Field("id_kegiatan") String id_kegiatan,
            @Field("tanggal") String tanggal,
            @Field("waktu") String waktu,
            @Field("kegiatan") String kegiatan,
            @Field("penangungjwb") String penangungjwb,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @POST("edit_kegiatan.php")
    Call<editkegiatan> editkegiatan(
            @Field("id_kegiatan") String id_kegiatan,
            @Field("hari") String hari,
            @Field("waktu") String waktu,
            @Field("kegiatan") String kegiatan,
            @Field("penangungjwb") String penangungjwb
    );

    @FormUrlEncoded
    @POST("delet_kegiatan.php")
    Call<deletkegiatan> delkegiatan(
            @Field("id_kegiatan") String id_kegiatan
    );
}




