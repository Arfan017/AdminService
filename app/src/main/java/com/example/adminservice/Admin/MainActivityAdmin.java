package com.example.adminservice.Admin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.adminservice.Bendahara.Activity_Keuangan;
import com.example.adminservice.Login;
import com.example.adminservice.R;
import com.example.adminservice.Sekretaris.Activity_Inven;
import com.example.adminservice.Sekretaris.Activity_Kegiatan_Remaja;
import com.example.adminservice.Sekretaris.Activity_Pengurus;
import com.example.adminservice.Sekretaris.Activity_Petugas_Jumat;

public class MainActivityAdmin extends AppCompatActivity {
    ImageView kegiatan, keuangan;
    CardView logout;
    String  status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        status = getIntent().getStringExtra("status");

        initComponents();
    }

    private void initComponents() {
        keuangan = findViewById(R.id.keuangann);
        kegiatan = findViewById(R.id.kegiatann);
        logout = findViewById(R.id.loh_out);

        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAdmin.this, Admin_Keuangan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdmin.this, Admin_Kegiatan_Remaja.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAdmin.this, Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }
}