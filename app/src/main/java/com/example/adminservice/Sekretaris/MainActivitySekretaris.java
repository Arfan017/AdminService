package com.example.adminservice.Sekretaris;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.adminservice.Bendahara.Activity_Keuangan;
import com.example.adminservice.Login;
import com.example.adminservice.R;

public class MainActivitySekretaris extends AppCompatActivity {
    ImageView zakat, inventaris, kegiatan, keuangan, kurban, penguru;
    CardView logout, ptgsjumatt;
    String  status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DASHBOARD SEKRETARIS");
        setContentView(R.layout.activity_main_sekretaris);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        status = getIntent().getStringExtra("status");

        initComponents();
    }

    private void initComponents() {
        inventaris = findViewById(R.id.investariss);
        kegiatan = findViewById(R.id.kegiatann);
        penguru = findViewById(R.id.penguruss);
        ptgsjumatt = findViewById(R.id.ptgsjumatt);
        logout = findViewById(R.id.loh_out);

        inventaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySekretaris.this, Activity_Inven.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivitySekretaris.this, Activity_Kegiatan_Remaja.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        penguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySekretaris.this, Activity_Pengurus.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        ptgsjumatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySekretaris.this, Activity_Petugas_Jumat.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivitySekretaris.this, Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }
}