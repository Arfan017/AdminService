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

public class Edit_Petugas_Jumat extends AppCompatActivity implements View.OnClickListener {
    EditText idcer, cerama, tema, imam, adzan, tanggal;
    String id, cer, tem, im, adz, tgl;
    Button sav, del;
    ApiInterface apiInterface;
    String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ptgsjumat);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

//        idcer = findViewById(R.id.idpenceramah);
        cerama = findViewById(R.id.penceramah);
        tema = findViewById(R.id.tema);
        imam = findViewById(R.id.imam);
        adzan = findViewById(R.id.adzan);
        tanggal = findViewById(R.id.tanggal);

        _id = getIntent().getStringExtra("id_penceramah");
        String tgl = getIntent().getStringExtra("tanggal");
        String pm = getIntent().getStringExtra("penceramah");
        String jn = getIntent().getStringExtra("tema");
        String im = getIntent().getStringExtra("imam");
        String ad = getIntent().getStringExtra("muadzin");

        tanggal.setText(tgl);
        cerama.setText(pm);
        tema.setText(jn);
        imam.setText(im);
        adzan.setText(ad);

        del = findViewById(R.id.del);
        del.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);
    }

    private void EditData() {
        tgl = tanggal.getText().toString();
        cer = cerama.getText().toString();
        tem = tema.getText().toString();
        im = imam.getText().toString();
        adz = adzan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_EDIT_PETUGAS_JUMAT,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Petugas_Jumat.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Petugas_Jumat.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_penceramah", _id);
                params.put("tanggal", tgl);
                params.put("penceramah", cer);
                params.put("tema", tem);
                params.put("imam", im);
                params.put("muadzin", adz);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void DeletePetugas() {
        id = _id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_DELETE_PETUGAS_JUMAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Petugas_Jumat.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Petugas_Jumat.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_penceramah", _id);
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
                tgl = tanggal.getText().toString();
                cer = cerama.getText().toString();
                tem = tema.getText().toString();
                im = imam.getText().toString();
                adz = adzan.getText().toString();

                if (tgl.length() == 0) {
                    tanggal.setError("Harus Diisi");
                } else if (cer.length() == 0) {
                    cerama.setError("Harus Diisi");
                } else if (tem.length() == 0) {
                    tema.setError("Harus Diisi");
                } else if (im.length() == 0) {
                    imam.setError("Harus Diisi");
                } else if (adz.length() == 0) {
                    adzan.setError("Harus Diisi");
                } else {
                    EditData();
                    startActivity(new Intent(Edit_Petugas_Jumat.this, Activity_Petugas_Jumat.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;
            case R.id.del:
                DeletePetugas();
                startActivity(new Intent(Edit_Petugas_Jumat.this, Activity_Petugas_Jumat.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
        }
    }
}

//    private void delete(String idc) {
//        apiInterface = Apiclient.getClient().create(ApiInterface.class);
//        Call<deletptgsjumat> antarcal = apiInterface.deletjuamt(idc);
//        antarcal.enqueue(new Callback<deletptgsjumat>() {
//            @Override
//            public void onResponse(Call<deletptgsjumat> call, Response<deletptgsjumat> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//                    Toast.makeText(Edit_Petugas_Jumat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Edit_Petugas_Jumat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<deletptgsjumat> call, Throwable t) {
//                Toast.makeText(Edit_Petugas_Jumat.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    private void getantar(String idc, String pencer, String tem, String imam,String adzan) {
//        apiInterface = Apiclient.getClient().create(ApiInterface.class);
//        Call<editptgsjumat> antarcal = apiInterface.editjuamt(idc, pencer, tem, imam,adzan);
//        antarcal.enqueue(new Callback<editptgsjumat>() {
//            @Override
//            public void onResponse(Call<editptgsjumat> call, Response<editptgsjumat> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//                    Toast.makeText(Edit_Petugas_Jumat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Edit_Petugas_Jumat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<editptgsjumat> call, Throwable t) {
//                Toast.makeText(Edit_Petugas_Jumat.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }