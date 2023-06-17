package com.example.adminservice.Bendahara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.R;
import com.example.adminservice.api.Konfigurasi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Edit_Keuangan extends AppCompatActivity {
    TextView Taggal, TipeTransaksi, KategoriTransaksi, JumlahTransaksi, saldo_sekarang, saldo_akhir, KeteranganTransaksi;
    Button BtnEdit, BtnDelete, BtnSave, BtnCancel;
    private EditText EtTanggal, EtJumlah, Etketerangan;
    private Spinner STipeTransaksi, SKategoriTransaksi;
    String id_keuangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_keuangang);

        this.setTitle("DETAIL KEUANGAN");

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        id_keuangan = getIntent().getStringExtra("id_keuangan");
        String Hari = getIntent().getStringExtra("Hari");
        String Jumlah = String.valueOf(getIntent().getStringExtra("Jumlah"));
        String Tipe_transaksi = getIntent().getStringExtra("Tipe_transaksi");
        String Kategori_transaksi = getIntent().getStringExtra("Kategori_transaksi");
        String Saldo_sekarang = String.valueOf(getIntent().getStringExtra("Saldo_sekarang"));
        String Saldo_akhir = String.valueOf(getIntent().getStringExtra("Saldo_akhir"));
        String Keterangan = getIntent().getStringExtra("Keterangan");
        String Status = getIntent().getStringExtra("status");

        Taggal = findViewById(R.id.TglTransaksi);
        TipeTransaksi = findViewById(R.id.TipeTransaksi);
        KategoriTransaksi = findViewById(R.id.KategoriTransaksi);
        JumlahTransaksi = findViewById(R.id.JumlahTransaksi);
        saldo_sekarang = findViewById(R.id.saldo_sekarang);
        saldo_akhir = findViewById(R.id.saldo_terakhir);
        KeteranganTransaksi = findViewById(R.id.KeteranganTransaksi);
        BtnEdit = findViewById(R.id.btnEdit);
        BtnDelete = findViewById(R.id.btnDelete);

        Taggal.setText(Hari);
        TipeTransaksi.setText(Tipe_transaksi);
        KategoriTransaksi.setText(Kategori_transaksi);
        JumlahTransaksi.setText(Jumlah);
        saldo_sekarang.setText(Saldo_sekarang);
        saldo_akhir.setText(Saldo_akhir);
        KeteranganTransaksi.setText(Keterangan);

        if (Status.equals("setuju") || Status.equals("tolak")){
            BtnEdit.setEnabled(false);
        }

        BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditData();
        }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });

    }

    private void EditKeuangan() {
        String Tanggal = EtTanggal.getText().toString();
        String Jumlah = EtJumlah.getText().toString();
        String Keterangan = Etketerangan.getText().toString();
        String Tipe_Transaksi = STipeTransaksi.getSelectedItem().toString();
        String Kategori_transaksi = SKategoriTransaksi.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_EDIT_KEUANGAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Keuangan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Keuangan.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_keuangan", id_keuangan);
                params.put("harian", Tanggal);
                params.put("jumlah", Jumlah);
                params.put("tipe_transaksi", Tipe_Transaksi);
                params.put("kategori_transaksi", Kategori_transaksi);
                params.put("keterangan", Keterangan);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
    private void DeleteKeuangan() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_DELETE_KEUANGAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Keuangan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Keuangan.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_keuangan", id_keuangan);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void EditData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_Keuangan.this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_edit_keuangan, null);
        builder.setTitle("Edit");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        EtTanggal = (EditText) view.findViewById(R.id.tgl);
        EtJumlah = (EditText) view.findViewById(R.id.jumlah);
        Etketerangan = (EditText) view.findViewById(R.id.keterangan);
        BtnSave = (Button) view.findViewById(R.id.btnSave);
        BtnCancel = (Button) view.findViewById(R.id.btnCancel);

        EtTanggal.setText(currentDate.toString());

        EtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Edit_Keuangan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        EtTanggal.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        STipeTransaksi = (Spinner) view.findViewById(R.id.spinnTipeTrans);
        ArrayAdapter<CharSequence> adapter_tipe_transaksi = ArrayAdapter.createFromResource(Edit_Keuangan.this, R.array.Tipe_Transaksi, android.R.layout.simple_spinner_item);
        adapter_tipe_transaksi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        STipeTransaksi.setAdapter(adapter_tipe_transaksi);

        SKategoriTransaksi = (Spinner) view.findViewById(R.id.spinnKategoriTrans);
        ArrayAdapter<CharSequence>adapter_Kategori_transaksi = ArrayAdapter.createFromResource(Edit_Keuangan.this, R.array.Kategori_Transaksi, android.R.layout.simple_spinner_item);
        adapter_Kategori_transaksi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SKategoriTransaksi.setAdapter(adapter_Kategori_transaksi);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (EtTanggal.getText().length() == 0){
                    EtTanggal.setError("Tidak boleh Kosong");
                } else if (EtJumlah.length()==0) {
                    EtJumlah.setError("Tidak boleh Kosong");
                } else if (Etketerangan.length()==0) {
                    Etketerangan.setError("Tidak boleh Kosong");
                } else {
                    EditKeuangan();
                    startActivity(new Intent(Edit_Keuangan.this, Activity_Keuangan.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                dialog.dismiss();
            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void DeleteData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Edit_Keuangan.this);
        //View view = getLayoutInflater().inflate(R.layout.layout_dialog_edit_keuangan, null);
        builder.setTitle("Delete");
        builder.setMessage("Apakah anda ingin menghapus data ini..?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteKeuangan();
                dialog.dismiss();
                startActivity(new Intent(Edit_Keuangan.this, Activity_Keuangan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}