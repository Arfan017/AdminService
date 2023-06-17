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

public class Edit_Investaris extends AppCompatActivity implements View.OnClickListener {
    EditText nama, jumlah, satuan, kondisi, keterangan;
    String id, idin, nam, jum, satu, kond, ket;
    Button sav, del;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_investaris);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        idin = getIntent().getStringExtra("id_aset");
        String nm = getIntent().getStringExtra("nama_aset");
        String jm = getIntent().getStringExtra("jumlah");
        String st = getIntent().getStringExtra("satuan");
        String kn = getIntent().getStringExtra("kondisi");
        String kt = getIntent().getStringExtra("kondisi");

//        idin = findViewById(R.id.idinv);
        nama = findViewById(R.id.namaset);
        jumlah = findViewById(R.id.jumlah);
        satuan = findViewById(R.id.satuan);
        kondisi = findViewById(R.id.kondisi);
        keterangan = findViewById(R.id.keterangan);

//        idin.setText(id);
        nama.setText(nm);
        jumlah.setText(jm);
        satuan.setText(st);
        kondisi.setText(kn);
        keterangan.setText(kt);

        del = findViewById(R.id.del);
        del.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);
    }

    private void EditInvetory() {
        nam = nama.getText().toString();
        jum = jumlah.getText().toString();
        satu = satuan.getText().toString();
        kond = kondisi.getText().toString();
        ket = keterangan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_EDIT_INVENTORY,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Investaris.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Investaris.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_inventory", idin);
                params.put("nama_aset", nam);
                params.put("jumlah", jum);
                params.put("satuan", satu);
                params.put("kondisi", kond);
                params.put("keterangan", ket);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void DeleteInventory() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_DELETE_INVENTORY,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Investaris.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Investaris.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_inventory", idin);
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
                id = idin;
                nam = nama.getText().toString();
                jum = jumlah.getText().toString();
                satu = satuan.getText().toString();
                kond = kondisi.getText().toString();
                ket = keterangan.getText().toString();

//                if(id.length()==0){
//                    idin.setError("Harus Diisi");
//                }else

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
                    EditInvetory();
                    startActivity(new Intent(Edit_Investaris.this, Activity_Inven.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                    break;
                }
            case R.id.del:
                DeleteInventory();
                startActivity(new Intent(Edit_Investaris.this, Activity_Inven.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
        }
    }
}
