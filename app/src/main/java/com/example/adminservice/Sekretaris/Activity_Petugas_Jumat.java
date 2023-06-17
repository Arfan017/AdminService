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
import com.example.adminservice.adapter.ModelPetugasJumat;
import com.example.adminservice.adapter.PetugasJumatAdapter;
import com.example.adminservice.adapter.RecyclerViewInterface;
import com.example.adminservice.api.ApiInterface;
import com.example.adminservice.api.Konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Petugas_Jumat extends AppCompatActivity implements RecyclerViewInterface {
    TextView id,penceramah,tema,imam,adzan;
    ImageView log,tn;
    ApiInterface api;
    List<ModelPetugasJumat> ListPetugasJumat;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        this.setTitle("PETUGAS JUMAT");
        setContentView(R.layout.editjumat);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        ListPetugasJumat = new ArrayList<>();

        recyclerView = findViewById(R.id.re);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetDataPengurus();
        initcontent();
    }

    private void initcontent() {
        log=findViewById(R.id.loh_out);
        log.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Activity_Petugas_Jumat.this, MainActivitySekretaris.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();


        }
    });tn=findViewById(R.id.tm);
        tn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Activity_Petugas_Jumat.this, Input_Petugas_Jumat.class));
        }
    });
    }

    private void GetDataPengurus() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_PETUGAS_JUMAT,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                //getting Pertugas object from json array
                                JSONObject Petugas = array.getJSONObject(i);

                                //adding the Pertugas to Pertugas list
                                ListPetugasJumat.add(new ModelPetugasJumat(
                                        Petugas.getString("id_penceramah"),
                                        Petugas.getString("tanggal"),
                                        Petugas.getString("penceramah"),
                                        Petugas.getString("tema"),
                                        Petugas.getString("imam"),
                                        Petugas.getString("muadzin")
                                        ));
                            }
                            //creating adapter object and setting it to recyclerview
                            PetugasJumatAdapter adapter = new PetugasJumatAdapter(Activity_Petugas_Jumat.this, ListPetugasJumat, Activity_Petugas_Jumat.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Petugas_Jumat.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Activity_Petugas_Jumat.this, Edit_Petugas_Jumat.class);
        intent.putExtra("id_penceramah", ListPetugasJumat.get(position).getId_penceramah());
        intent.putExtra("tanggal", ListPetugasJumat.get(position).getTanggal()).toString();
        intent.putExtra("penceramah", ListPetugasJumat.get(position).getPenceramah()).toString();
        intent.putExtra("tema", ListPetugasJumat.get(position).getTema()).toString();
        intent.putExtra("imam", ListPetugasJumat.get(position).getImam_sholat()).toString();
        intent.putExtra("muadzin", ListPetugasJumat.get(position).getMuadzin()).toString();
        startActivity(intent);
    }
}