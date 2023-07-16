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

public class Input_Pengurus extends AppCompatActivity implements View.OnClickListener {
    EditText idp, namp, nohp,jabatann;
    String id, nam, nop, jab ;
    Button sav, ex;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pengurus);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        namp = findViewById(R.id.namapeng);
        jabatann = findViewById(R.id.jabatan);
        nohp = findViewById(R.id.nohp);

        ex = findViewById(R.id.exi);
        ex.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);
    }

    private void inputPengurus() {
        nam = namp.getText().toString();
        jab = jabatann.getText().toString();
        nop = nohp.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD_PENGURUS,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Input_Pengurus.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Input_Pengurus.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_pengurus", nam);
                params.put("jabatan", jab);
                params.put("no_hp", nop);
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

                nam = namp.getText().toString();
                jab = jabatann.getText().toString();
                nop = nohp.getText().toString();

                if(nam.length()==0) {
                    namp.setError("Harus Diisi");
                }else if(jab.length()==0) {
                    jabatann.setError("Harus Diisi");
                }else if(nop.length()==0) {
                    nohp.setError("Harus Diisi");
                }else{
                    inputPengurus();
                    startActivity(new Intent(Input_Pengurus.this, Activity_Pengurus.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();

                }
                break;
            case R.id.exi:
                startActivity(new Intent(Input_Pengurus.this, Activity_Pengurus.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
        }
    }
}
