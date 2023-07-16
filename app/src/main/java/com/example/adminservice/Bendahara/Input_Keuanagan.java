package com.example.adminservice.Bendahara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.api.Konfigurasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class Input_Keuanagan extends AppCompatActivity {
    private String strJson;
    private OkHttpClient client;
    private okhttp3.Response reponse;
    private okhttp3.Request request;
    EditText tanggal, jumlah, keterangan;
    Spinner tipe_transaksi, kategori_transaksi;
    TextView jumlahuang;
    Button sav, ex;
    String saldo_akhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_keuanagan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tanggal = findViewById(R.id.tgl);
        jumlah = (EditText) findViewById(R.id.jumlah);
        keterangan = findViewById(R.id.keterangan);
        ex = findViewById(R.id.exi);
        sav = findViewById(R.id.svv);

        tipe_transaksi = findViewById(R.id.spinnTipeTrans);
        ArrayAdapter<CharSequence> adapter_tipe_transaksi = ArrayAdapter.createFromResource(this, R.array.Tipe_Transaksi, android.R.layout.simple_spinner_item);
        adapter_tipe_transaksi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tipe_transaksi.setAdapter(adapter_tipe_transaksi);

        kategori_transaksi = findViewById(R.id.spinnKategoriTrans);
        ArrayAdapter<CharSequence> adapter_Kategori_transaksi = ArrayAdapter.createFromResource(this, R.array.Kategori_Transaksi, android.R.layout.simple_spinner_item);
        adapter_Kategori_transaksi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        kategori_transaksi.setAdapter(adapter_Kategori_transaksi);

        client = new OkHttpClient();
        new GetDataKeuangan().execute();

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        tanggal.setText(currentDate.toString());

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Input_Keuanagan.this, new DatePickerDialog.OnDateSetListener() {
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

        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _Jumlah = jumlah.getText().toString();
                String Jumlah = _Jumlah;
                String Tipe_Transaksi = tipe_transaksi.getSelectedItem().toString();
                String Kategori_transaksi = kategori_transaksi.getSelectedItem().toString();
                String Keterangan = keterangan.getText().toString();

                if (Jumlah.length() == 0) {
                    jumlah.setError("Tidak boleh Kosong");
                } else if (Keterangan.length() == 0) {
                    keterangan.setError("Tidak boleh Kosong");
                } else if (Tipe_Transaksi.length() == 0) {
                    Toast.makeText(Input_Keuanagan.this, "Field Tipe Transaksi tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (Kategori_transaksi.length() == 0) {
                    Toast.makeText(Input_Keuanagan.this, "Field kategori Transaksi tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    inputData();
                    startActivity(new Intent(Input_Keuanagan.this, Activity_Keuangan.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Input_Keuanagan.this, Activity_Keuangan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }

    private void inputData() {
        String Tanggal = tanggal.getText().toString();
        String Jumlah = jumlah.getText().toString();
        String Tipe_Transaksi = tipe_transaksi.getSelectedItem().toString();
        String Kategori_transaksi = kategori_transaksi.getSelectedItem().toString();
        String Saldo_akhir = saldo_akhir;
        String Keterangan = keterangan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD_KEUANGAN_SEMENTARA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Input_Keuanagan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Input_Keuanagan.this, error.toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(Input_Keuanagan.this, Saldo_akhir, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("harian", Tanggal);
                params.put("jumlah", Jumlah);
                params.put("tipe_transaksi", Tipe_Transaksi);
                params.put("kategori_transaksi", Kategori_transaksi);
                params.put("saldo_sekarang", Saldo_akhir);
                params.put("keterangan", Keterangan);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private class GetDataKeuangan extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            request = new okhttp3.Request.Builder().url(Konfigurasi.URL_TOTAL_KEUANGAN).build();
            try {
                reponse = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                strJson = reponse.body().string();
                updateUserData(strJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserData(String strJson) {
        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);
            saldo_akhir = child.getString("saldo_akhir");
        } catch (JSONException e) {
//            throw new RuntimeException(e);
//            Toast.makeText(Input_Keuanagan.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            saldo_akhir = "1";
        }
    }
}