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

public class Edit_Pengurus extends AppCompatActivity implements View.OnClickListener {
    EditText idp, namp, nohp, jabatann;
    String id, nam, nop, jab;
    Button sav, ex, del;
    ApiInterface apiInterface;
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pengurus);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        namp = findViewById(R.id.namapeng);
        jabatann = findViewById(R.id.jabatan);
        nohp = findViewById(R.id.nohp);

        _id = getIntent().getStringExtra("id_pengurus");
        String pm = getIntent().getStringExtra("nama_pengurus");
        String jn = getIntent().getStringExtra("jabatan");
        String im = getIntent().getStringExtra("no_hp");

        namp.setText(pm);
        jabatann.setText(jn);
        nohp.setText(im);

        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);
        del = findViewById(R.id.del);
        del.setOnClickListener(this);
    }
    private void EditPengurus() {
        nam = namp.getText().toString();
        jab = jabatann.getText().toString();
        nop = nohp.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_EDIT_PENGURUS,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Pengurus.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Pengurus.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengurus", _id);
                params.put("nama_pengurus", nam);
                params.put("jabatan", jab);
                params.put("no_hp", nop);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void DeletePengurus() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_DELETE_PENGURUS,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Pengurus.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Pengurus.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_pengurus", _id);
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
                id = _id;
                nam = namp.getText().toString();
                jab = jabatann.getText().toString();
                nop = nohp.getText().toString();

                if (id.length() == 0) {
                    idp.setError("Harus Diisi");
                } else if (nam.length() == 0) {
                    namp.setError("Harus Diisi");
                } else if (jab.length() == 0) {
                    jabatann.setError("Harus Diisi");
                } else if (nop.length() == 0) {
                    nohp.setError("Harus Diisi");

                } else {
                    EditPengurus();
                    startActivity(new Intent(Edit_Pengurus.this, Activity_Pengurus.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                    break;
                }
            case R.id.del:
                DeletePengurus();
                startActivity(new Intent(Edit_Pengurus.this, Activity_Pengurus.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
        }
    }
}

//    private void delete(String idp) {
//        apiInterface = Apiclient.getClient().create(ApiInterface.class);
//        Call<deletpengurus> antarcal = apiInterface.deleturus(idp);
//        antarcal.enqueue(new Callback<deletpengurus>() {
//            @Override
//            public void onResponse(Call<deletpengurus> call, Response<deletpengurus> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
////                    Toast.makeText(Edit_Pengurus.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
////                    Toast.makeText(Edit_Pengurus.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<deletpengurus> call, Throwable t) {
//                Toast.makeText(Edit_Pengurus.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    private void getantar(String idp, String namp, String jabatan, String nope) {
//        apiInterface = Apiclient.getClient().create(ApiInterface.class);
//        Call<editpengurus> antarcal = apiInterface.editurus(idp, namp, jabatan, nope);
//        antarcal.enqueue(new Callback<editpengurus>() {
//            @Override
//            public void onResponse(Call<editpengurus> call, Response<editpengurus> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//                    Toast.makeText(Edit_Pengurus.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Edit_Pengurus.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<editpengurus> call, Throwable t) {
//                Toast.makeText(Edit_Pengurus.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }