package com.example.adminservice.Admin;

import static com.example.adminservice.api.Konfigurasi.URL_IMAGES;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.adminservice.R;
import com.example.adminservice.Sekretaris.Activity_Kegiatan_Remaja;
import com.example.adminservice.api.Konfigurasi;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Ferivikasi_Kegiatan extends AppCompatActivity implements View.OnClickListener {
    EditText idkeg, _tanggal, _waktu, _kegiatan, _nanggung, _keterangan;
    String id, tgl, wak, keg, nang, ket, gambar;
    Button setuju, tolak;
    ImageView imageKegiatan;
    private int jam, menit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferivikasi_kegiatan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        _tanggal = findViewById(R.id.tanggal);
        _waktu = findViewById(R.id.waktu);
        _kegiatan = findViewById(R.id.kegiatan);
        _nanggung = findViewById(R.id.penangung);
        _keterangan = findViewById(R.id.keterangan);
        imageKegiatan = findViewById(R.id.ImgKegiatan);

        id = getIntent().getStringExtra("id_kegiatan");
        String tanggal = getIntent().getStringExtra("tanggal");
        String waktu = getIntent().getStringExtra("waktu");
        String kegiatan = getIntent().getStringExtra("kegiatan");
        String penanggungJwb = getIntent().getStringExtra("penanggungJwb");
        String keterangan = getIntent().getStringExtra("keterangan");
        String status = getIntent().getStringExtra("status");
        gambar = getIntent().getStringExtra("gambar");

        _tanggal.setText(tanggal);
        _waktu.setText(waktu);
        _kegiatan.setText(kegiatan);
        _nanggung.setText(penanggungJwb);
        _keterangan.setText(keterangan);
        Glide.with(this).load(URL_IMAGES + gambar).into(imageKegiatan);

        _tanggal.setEnabled(false);
        _waktu.setEnabled(false);
        _kegiatan.setEnabled(false);
        _nanggung.setEnabled(false);
        _keterangan.setEnabled(false);

        tolak = findViewById(R.id.tolak);
        tolak.setOnClickListener(this);
        setuju = findViewById(R.id.setujua);
        setuju.setOnClickListener(this);

        if (status.equals("setuju") || status.equals("tolak")){
            tolak.setEnabled(false);
            setuju.setEnabled(false);
        }

        _tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Ferivikasi_Kegiatan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        _tanggal.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        _waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam = calendar.get(Calendar.DAY_OF_MONTH);
                menit = calendar.get(Calendar.DAY_OF_MONTH);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Ferivikasi_Kegiatan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam = hourOfDay;
                        menit = minute;

                        if (jam <= 12) {
                            _waktu.setText(String.format(Locale.getDefault(), "%d:%d am", jam, menit));
                        } else {
                            _waktu.setText(String.format(Locale.getDefault(), "%d:%d pm", jam, menit));
                        }
                    }
                }, jam, menit, true);
                timePickerDialog.show();
            }
        });
    }

    private void VerifikasiKegiatan(String status) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.FERIVIKASI_KEGIATAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Ferivikasi_Kegiatan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Ferivikasi_Kegiatan.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kegiatan", id);
                params.put("status", status);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setujua:
                tgl = _tanggal.getText().toString();
                wak = _waktu.getText().toString();
                keg = _kegiatan.getText().toString();
                nang = _nanggung.getText().toString();
                ket = _keterangan.getText().toString();

                if (tgl.length() == 0) {
                    _tanggal.setError("Harus Diisi");
                } else if (wak.length() == 0) {
                    _waktu.setError("Harus Diisi");
                } else if (keg.length() == 0) {
                    _kegiatan.setError("Harus Diisi");
                } else if (nang.length() == 0) {
                    _nanggung.setError("Harus Diisi");
                } else if (ket.length() == 0) {
                    _keterangan.setError("Harus Diisi");
                } else {
                    VerifikasiKegiatan("setuju");
                    startActivity(new Intent(Ferivikasi_Kegiatan.this, Admin_Kegiatan_Remaja.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;
            case R.id.tolak:
                tgl = _tanggal.getText().toString();
                wak = _waktu.getText().toString();
                keg = _kegiatan.getText().toString();
                nang = _nanggung.getText().toString();
                ket = _keterangan.getText().toString();

                if (tgl.length() == 0) {
                    _tanggal.setError("Harus Diisi");
                } else if (wak.length() == 0) {
                    _waktu.setError("Harus Diisi");
                } else if (keg.length() == 0) {
                    _kegiatan.setError("Harus Diisi");
                } else if (nang.length() == 0) {
                    _nanggung.setError("Harus Diisi");
                } else if (ket.length() == 0) {
                    _keterangan.setError("Harus Diisi");
                } else {
                    VerifikasiKegiatan("tolak");
                    startActivity(new Intent(Ferivikasi_Kegiatan.this, Admin_Kegiatan_Remaja.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;
        }
    }
}