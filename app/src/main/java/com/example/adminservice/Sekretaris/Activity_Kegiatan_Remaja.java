package com.example.adminservice.Sekretaris;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.adapter.KegiatanAdapter;
import com.example.adminservice.adapter.ModelKegiatan;
import com.example.adminservice.adapter.RecyclerViewInterface;
import com.example.adminservice.api.ApiInterface;
import com.example.adminservice.api.Konfigurasi;
import com.example.adminservice.modul.data.data_kegiatan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class Activity_Kegiatan_Remaja extends AppCompatActivity implements RecyclerViewInterface {
    ImageView imgBack, imgAdd;
    List<data_kegiatan> modelList;
    TextView id, nama, jumlah, satuan, kondisi, keterangan;
    ApiInterface api;
    List<ModelKegiatan> ListKegiatan;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("kEGIATAN REMAJA MASJID");
        setContentView(R.layout.activity_kegiatan_masjid);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        ListKegiatan = new ArrayList<>();

        recyclerView = findViewById(R.id.re);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initcontent();
        GetDataKegiatan();
    }

    private void initcontent() {
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Kegiatan_Remaja.this, MainActivitySekretaris.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

            }
        });

        imgAdd = findViewById(R.id.imgAdd);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Kegiatan_Remaja.this, Input_Kegiatan.class));
            }
        });
    }

    private void GetDataKegiatan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_KEGIATAN,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                //getting _kegiatan object from json array
                                JSONObject _kegiatan = array.getJSONObject(i);

                                //adding the _kegiatan to _kegiatan list
                                ListKegiatan.add(new ModelKegiatan(
                                        _kegiatan.getString("id_kegiatan"),
                                        _kegiatan.getString("tanggal"),
                                        _kegiatan.getString("waktu"),
                                        _kegiatan.getString("kegiatan"),
                                        _kegiatan.getString("penanggungJwb"),
                                        _kegiatan.getString("keterangan"),
                                        _kegiatan.getString("gambar"),
                                        _kegiatan.getString("status")
                                        )
                                );
                            }
                            //creating adapter object and setting it to recyclerview
                            KegiatanAdapter adapter = new KegiatanAdapter(Activity_Kegiatan_Remaja.this, ListKegiatan, Activity_Kegiatan_Remaja.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Kegiatan_Remaja.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(Activity_Kegiatan_Remaja.this, Edit_Kegiatan.class);
        intent.putExtra("id_kegiatan", ListKegiatan.get(position).getId_kegiatan()).toString();
        intent.putExtra("tanggal", ListKegiatan.get(position).getTanggal()).toString();
        intent.putExtra("waktu", ListKegiatan.get(position).getWaktu()).toString();
        intent.putExtra("kegiatan", ListKegiatan.get(position).getKegiatan()).toString();
        intent.putExtra("penanggungJwb", ListKegiatan.get(position).getPenanggungJwb());
        intent.putExtra("keterangan", ListKegiatan.get(position).getKeterangan());
        intent.putExtra("gambar", ListKegiatan.get(position).getGambar());
        intent.putExtra("status", ListKegiatan.get(position).getStatus());
        startActivity(intent);
    }
}