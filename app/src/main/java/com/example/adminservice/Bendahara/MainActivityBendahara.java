package com.example.adminservice.Bendahara;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.adminservice.Login;
import com.example.adminservice.R;
import com.example.adminservice.Sekretaris.Activity_Inven;
import com.example.adminservice.Sekretaris.Activity_Kegiatan_Remaja;
import com.example.adminservice.Sekretaris.Activity_Pengurus;
import com.example.adminservice.Sekretaris.Activity_Petugas_Jumat;

public class MainActivityBendahara extends AppCompatActivity {
    ImageView zakat, inventaris, kegiatan, keuangan, kurban, penguru;
    CardView logout, ptgsjumatt;
    String  status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DASHBOARD BENDAHARA");
        setContentView(R.layout.activity_main_bendahara);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        status = getIntent().getStringExtra("status");

        initComponents();
    }

    private void initComponents() {
        keuangan = findViewById(R.id.keuangann);
        logout = findViewById(R.id.loh_out);

        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityBendahara.this, Activity_Keuangan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityBendahara.this, Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }
}