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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.adapter.PengurusAdapter;
import com.example.adminservice.adapter.ModelPengurus;
import com.example.adminservice.adapter.RecyclerViewInterface;
import com.example.adminservice.api.ApiInterface;
import com.example.adminservice.api.Konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Pengurus extends AppCompatActivity implements RecyclerViewInterface {
    ImageView log,tn;
    ApiInterface api;
    List<ModelPengurus> ListPengurus;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setTitle("PENGURUS MASJID");
        setContentView(R.layout.editurus);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        ListPengurus = new ArrayList<>();

        recyclerView = findViewById(R.id.re);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetDataPengurus();
        initcontent();
    }

    private void GetDataPengurus() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_PENGURUS,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                //getting _Pengurus object from json array
                                JSONObject _Pengurus = array.getJSONObject(i);

                                //adding the _Pengurus to _Pengurus list
                                ListPengurus.add(new ModelPengurus(
                                        _Pengurus.getString("id_pengurus"),
                                        _Pengurus.getString("nama_pengurus"),
                                        _Pengurus.getString("jabatan"),
                                        _Pengurus.getString("no_hp")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            PengurusAdapter adapter = new PengurusAdapter(Activity_Pengurus.this, ListPengurus, Activity_Pengurus.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Pengurus.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(Activity_Pengurus.this, Edit_Pengurus.class);
        intent.putExtra("id_pengurus", ListPengurus.get(position).getId_pengurus()).toString();
        intent.putExtra("nama_pengurus", ListPengurus.get(position).getNama_pengurus()).toString();
        intent.putExtra("jabatan", ListPengurus.get(position).getJabatan()).toString();
        intent.putExtra("no_hp", ListPengurus.get(position).getNo_hp()).toString();
        startActivity(intent);
    }

    private void initcontent() {
        log=findViewById(R.id.loh_out);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Pengurus.this, MainActivitySekretaris.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

            }
        });

        tn=findViewById(R.id.tm);
        tn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Pengurus.this, Input_Pengurus.class));
            }
        });
    }
}