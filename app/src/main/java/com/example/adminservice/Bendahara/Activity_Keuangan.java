package com.example.adminservice.Bendahara;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.api.Konfigurasi;
import com.example.adminservice.adapter.KeuanganAdapter;
import com.example.adminservice.adapter.ModelKeuangan;
import com.example.adminservice.adapter.RecyclerViewInterface;
import com.example.adminservice.modul.data.datakeuangan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Activity_Keuangan extends AppCompatActivity implements RecyclerViewInterface {
    List<datakeuangan> modelList;
    ImageView imgBack, imgAdd;
    List<ModelKeuangan> ListKeuangan;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setTitle("DASHBOARD KEUANGAN");
        setContentView(R.layout.activity_uang);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        imgBack = findViewById(R.id.imgBack);
        imgAdd = findViewById(R.id.imgAdd);

        ListKeuangan = new ArrayList<>();

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initcontent();
        GetDataUang();
    }

    private void initcontent() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Keuangan.this, MainActivityBendahara.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Keuangan.this, Input_Keuanagan.class);
                startActivity(intent);
            }
        });
    }

    private void GetDataUang() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_KEUANGAN,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                ListKeuangan.add(new ModelKeuangan(
                                        product.getString("id_keuangan"),
                                        product.getString("harian"),
                                        product.getInt("jumlah"),
                                        product.getString("tipe_transaksi"),
                                        product.getString("kategori_transaksi"),
                                        product.getInt("saldo_sekarang"),
                                        product.getInt("saldo_akhir"),
                                        product.getString("keterangan"),
                                        product.getString("status")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            KeuanganAdapter adapter = new KeuanganAdapter(Activity_Keuangan.this, ListKeuangan, Activity_Keuangan.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Keuangan.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        int _jumlah = ListKeuangan.get(position).getJumlah();
        int Saldo_sekarang = ListKeuangan.get(position).getSaldo_sekarang();
        int Saldo_akhir = ListKeuangan.get(position).getSaldo_akhir();

        Intent intent = new Intent(Activity_Keuangan.this, Edit_Keuangan.class);
        intent.putExtra("id_keuangan", ListKeuangan.get(position).getId_keuangan()).toString();
        intent.putExtra("Hari", ListKeuangan.get(position).getHarian());
        intent.putExtra("Jumlah", ConvertToRp(_jumlah));
        intent.putExtra("Tipe_transaksi", ListKeuangan.get(position).getTipe_transaksi());
        intent.putExtra("Kategori_transaksi", ListKeuangan.get(position).getKategori_transaksi());
        intent.putExtra("Saldo_sekarang", ConvertToRp(Saldo_sekarang));
        intent.putExtra("Saldo_akhir", ConvertToRp(Saldo_akhir));
        intent.putExtra("Keterangan", ListKeuangan.get(position).getKeterangan());
        intent.putExtra("status", ListKeuangan.get(position).getStatus());
        startActivity(intent);
    }

    static String ConvertToRp(int x) {
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(x);
        return formattedNumber;
    }
}