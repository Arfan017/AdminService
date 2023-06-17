package com.example.adminservice.Sekretaris;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.api.ApiInterface;
import com.example.adminservice.api.Konfigurasi;

import java.util.HashMap;
import java.util.Map;

public class Input_Investaris extends AppCompatActivity implements View.OnClickListener {

    EditText idin, nama, jumlah, satuan, kondisi, keterangan;
    String id, nam, jum, satu, kond, ket;
    Button sav, ex;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_investaris);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        nama = findViewById(R.id.namaset);
        jumlah = findViewById(R.id.jumlah);
        satuan = findViewById(R.id.satuan);
        kondisi = findViewById(R.id.kondisi);
        keterangan = findViewById(R.id.keterangan);

        ex = findViewById(R.id.exi);
        ex.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);
    }

    private void InputInvetory() {
        nam = nama.getText().toString();
        jum = jumlah.getText().toString();
        satu = satuan.getText().toString();
        kond = kondisi.getText().toString();
        ket = keterangan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD_INVENTORY,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Input_Investaris.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(Input_Investaris.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_aset", nama.getText().toString());
                params.put("jumlah", jumlah.getText().toString());
                params.put("satuan", satuan.getText().toString());
                params.put("kondisi", kondisi.getText().toString());
                params.put("keterangan", keterangan.getText().toString());
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.svv:
//                id = idin.getText().toString();
                nam = nama.getText().toString();
                jum = jumlah.getText().toString();
                satu = satuan.getText().toString();
                kond = kondisi.getText().toString();
                ket = keterangan.getText().toString();

//                if (id.length() == 0) {
//                    idin.setError("Harus Diisi");
//                } else
                if (nam.length() == 0) {
                    nama.setError("Harus Diisi");
                } else if (jum.length() == 0) {
                    jumlah.setError("Harus Diisi");
                } else if (satu.length() == 0) {
                    satuan.setError("Harus Diisi");
                } else if (kond.length() == 0) {
                    kondisi.setError("Harus Diisi");
                } else if (ket.length() == 0) {
                    keterangan.setError("Harus Diisi");
                } else {
                    InputInvetory();
                    startActivity(new Intent(Input_Investaris.this, Activity_Inven.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;
            case R.id.exi:
                startActivity(new Intent(Input_Investaris.this, Activity_Inven.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
        }
    }
}

//    private void getantar(String id, String nams, String jumlah, String satuan, String kondisi, String ket) {
//
//        apiInterface = Apiclient.getClient().create(ApiInterface.class);
//        Call<dtinventaris> antarcal = apiInterface.inventaris(id, nams, jumlah, satuan, kondisi, ket);
//        antarcal.enqueue(new Callback<dtinventaris>() {
//            @Override
//            public void onResponse(Call<dtinventaris> call, Response<dtinventaris> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//                    Toast.makeText(Input_Investaris.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Input_Investaris.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<dtinventaris> call, Throwable t) {
//                Toast.makeText(Input_Investaris.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
