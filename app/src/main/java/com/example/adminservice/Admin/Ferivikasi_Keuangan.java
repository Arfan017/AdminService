package com.example.adminservice.Admin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class Ferivikasi_Keuangan extends AppCompatActivity {
    private String strJson, _url = Konfigurasi.URL_TOTAL_KEUANGAN;
    private OkHttpClient client;
    private okhttp3.Response reponse;
    private okhttp3.Request request;
    private ProgressDialog progressDialog;
    TextView TVTaggal, TVTipeTransaksi, TVKategoriTransaksi, TVJumlahTransaksi, TVsaldo_sekarang, TVsaldo_akhir, TVKeteranganTransaksi;
    Button BtnSetuju, BtnTolak;
    String id_keuangan, Hari, Jumlah, Tipe_transaksi, Kategori_transaksi, Saldo_sekarang, Saldo_akhir, Keterangan;
    String saldo_akhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferivikasi_keuangang);

        this.setTitle("DETAIL KEUANGAN");

        client = new OkHttpClient();
        new GetDataKeuangan().execute();

        progressDialog = new ProgressDialog(Ferivikasi_Keuangan.this);
        progressDialog.setMessage("Mengambil Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        id_keuangan = getIntent().getStringExtra("id_keuangan");
        Hari = getIntent().getStringExtra("Hari");
        Jumlah = String.valueOf(getIntent().getStringExtra("Jumlah"));
        Tipe_transaksi = getIntent().getStringExtra("Tipe_transaksi");
        Kategori_transaksi = getIntent().getStringExtra("Kategori_transaksi");
        Saldo_sekarang = String.valueOf(getIntent().getStringExtra("Saldo_sekarang"));
        Saldo_akhir = String.valueOf(getIntent().getStringExtra("Saldo_akhir"));
        Keterangan = getIntent().getStringExtra("Keterangan");
        String status = getIntent().getStringExtra("status");

        TVTaggal = findViewById(R.id.TglTransaksi);
        TVTipeTransaksi = findViewById(R.id.TipeTransaksi);
        TVKategoriTransaksi = findViewById(R.id.KategoriTransaksi);
        TVJumlahTransaksi = findViewById(R.id.JumlahTransaksi);
        TVsaldo_sekarang = findViewById(R.id.saldo_sekarang);
//        TVsaldo_akhir = findViewById(R.id.saldo_terakhir);
        TVKeteranganTransaksi = findViewById(R.id.KeteranganTransaksi);

        BtnSetuju = findViewById(R.id.setuju);
        BtnTolak = findViewById(R.id.tolak);

        TVTaggal.setText(Hari);
        TVTipeTransaksi.setText(Tipe_transaksi);
        TVKategoriTransaksi.setText(Kategori_transaksi);
        TVJumlahTransaksi.setText(Jumlah);

//        TVsaldo_sekarang.setText(saldo_akhir);
        TVsaldo_sekarang.setVisibility(View.GONE);
//        TVsaldo_akhir.setText(Saldo_akhir);
//        TVsaldo_akhir.setVisibility(View.GONE);
        TVKeteranganTransaksi.setText(Keterangan);

        if (status.equals("setuju") || status.equals("tolak")) {
            BtnTolak.setEnabled(false);
            BtnSetuju.setEnabled(false);
        }

        BtnSetuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ferivikasi_Keuangan("setuju");
            }
        });

        BtnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ferivikasi_Keuangan("tolak");
            }
        });
    }

    private void ferivikasi_Keuangan(String status) {
        String Saldo_akhir_ = TVsaldo_sekarang.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi");
        builder.setMessage("Anda yakin ingin melakukan verifikasi?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Melakukan verifikasi keuangan
                verifikasiKeuanganAPI(status, Saldo_akhir_);
            }
        });
        builder.setNegativeButton("Tidak", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void verifikasiKeuanganAPI(String status, String saldoAkhir) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.FERIVIKASI_KEUANGAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Ferivikasi_Keuangan.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Ferivikasi_Keuangan.this, "error verifikasi: \n" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_keuangan", id_keuangan);
                params.put("harian", Hari);
                params.put("jumlah", Jumlah.replace(".", ""));
                params.put("tipe_transaksi", Tipe_transaksi);
                params.put("kategori_transaksi", Kategori_transaksi);
                params.put("saldo_sekarang", saldoAkhir);
                params.put("keterangan", Keterangan);
                params.put("status", status);
                return params;
            }
        };

        // Set retry policy dan tambahkan request ke queue
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000, // Timeout dalam milliseconds (misalnya 10 detik)
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private class GetDataKeuangan extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            request = new okhttp3.Request.Builder().url(_url).build();
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
                updateStatus(strJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateStatus(String strJson) {
        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);
            TVsaldo_sekarang.setText(child.getString("saldo_akhir"));
            progressDialog.dismiss();

        } catch (JSONException e) {
            TVsaldo_sekarang.setText("1");
            progressDialog.dismiss();
        }
    }
}