package com.example.adminservice.Sekretaris;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.adapter.InventoryAdapter;
import com.example.adminservice.adapter.ModelInventory;
import com.example.adminservice.adapter.RecyclerViewInterface;
import com.example.adminservice.api.ApiInterface;
import com.example.adminservice.api.Konfigurasi;
import com.example.adminservice.modul.data.datainvestoris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity_Inven extends AppCompatActivity implements RecyclerViewInterface {
    List<datainvestoris> modelList;
    TextView id, nama, jumlah, satuan, kondisi, keterangan;
    ImageView imgBack, imgAdd;
    ApiInterface api;
    List<ModelInventory> ListInventory;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setTitle("INVENTARIS MASJID");
        setContentView(R.layout.activity_inven);
        recyclerView = findViewById(R.id.re);

        recyclerView = findViewById(R.id.re);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListInventory = new ArrayList<>();

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        GetDataInventory();
        initcontent();
    }

    private void initcontent() {
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Inven.this, MainActivitySekretaris.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        imgAdd = findViewById(R.id.imgAdd);
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Inven.this, Input_Investaris.class));
            }
        });
    }

    private void GetDataInventory() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_INVENTORY,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                //getting _Inventory object from json array
                                JSONObject _Inventory = array.getJSONObject(i);

                                //adding the _Inventory to _Inventory list
                                ListInventory.add(new ModelInventory(
                                        _Inventory.getString("id_inventaris"),
                                        _Inventory.getString("nama_aset"),
                                        _Inventory.getString("jumlah"),
                                        _Inventory.getString("satuan"),
                                        _Inventory.getString("kondisi"),
                                        _Inventory.getString("keterangan")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            InventoryAdapter adapter = new InventoryAdapter(Activity_Inven.this, ListInventory, Activity_Inven.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Inven.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
        Intent intent = new Intent(Activity_Inven.this, Edit_Investaris.class);
        intent.putExtra("id_aset", ListInventory.get(position).getid_inventaris()).toString();
        intent.putExtra("nama_aset", ListInventory.get(position).getNama_aset()).toString();
        intent.putExtra("jumlah", ListInventory.get(position).getJumlah()).toString();
        intent.putExtra("satuan", ListInventory.get(position).getSatuan()).toString();
        intent.putExtra("kondisi", ListInventory.get(position).getKondisi());
        intent.putExtra("kondisi", ListInventory.get(position).getKeterangan());
        startActivity(intent);
    }
}






//    public void fetchservcea() {
//        api = Apiclient.getClient().create(ApiInterface.class);
//        Call<List<datainvestoris>> call = api.getdata();
//        call.enqueue(new Callback<List<datainvestoris>>() {
//            @Override
//            public void onResponse(Call<List<datainvestoris>> call, Response<List<datainvestoris>> response) {
//                modelList = response.body();
//                kur = new adapter_inven(Activity_Inven.this, modelList);
//                rec.setAdapter(kur);
//                kur.notifyDataSetChanged();
//            }
//            @Override
//            public void onFailure(Call<List<datainvestoris>> call, Throwable t) {
//
//            }
//        });
//    }