package com.example.adminservice.Sekretaris;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Input_Petugas_Jumat extends AppCompatActivity implements View.OnClickListener {
    EditText idcer, cerama, tema, imam, adzan, tanggal;
    String id, cer, tem, im, adz, tgl;
    Button sav, ex;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_ptgsjumat);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

//        idcer = findViewById(R.id.idpenceramah);
        cerama = findViewById(R.id.penceramah);
        tema = findViewById(R.id.tema);
        imam = findViewById(R.id.imam);
        adzan = findViewById(R.id.adzan);
        tanggal = findViewById(R.id.tanggal);

        ex = findViewById(R.id.exi);
        ex.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Input_Petugas_Jumat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        tanggal.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void inputData() {
        tgl = tanggal.getText().toString();
        cer = cerama.getText().toString();
        tem = tema.getText().toString();
        im = imam.getText().toString();
        adz = adzan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD_PETUGAS_JUMAT,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Input_Petugas_Jumat.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Input_Petugas_Jumat.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
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
                    inputData();
                    startActivity(new Intent(Input_Petugas_Jumat.this, Activity_Petugas_Jumat.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;

            case R.id.exi:
                startActivity(new Intent(Input_Petugas_Jumat.this, Activity_Petugas_Jumat.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

        }
    }
}

//    getantar(id, cer, tem, im,adz);

//    private void getantar(String idc, String pencer, String tem, String imam,String adzan) {
//
//        apiInterface = Apiclient.getClient().create(ApiInterface.class);
//        Call<dt_ptgsjumat> antarcal = apiInterface.juamt(idc, pencer, tem, imam,adzan);
//        antarcal.enqueue(new Callback<dt_ptgsjumat>() {
//            @Override
//            public void onResponse(Call<dt_ptgsjumat> call, Response<dt_ptgsjumat> response) {
//
//                if (response.body() != null && response.isSuccessful() && response.body().isStatus()) {
//                    Toast.makeText(Input_Petugas_Jumat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Input_Petugas_Jumat.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<dt_ptgsjumat> call, Throwable t) {
//                Toast.makeText(Input_Petugas_Jumat.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }