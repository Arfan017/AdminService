package com.example.adminservice;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.Admin.MainActivityAdmin;
import com.example.adminservice.Bendahara.MainActivityBendahara;
import com.example.adminservice.Sekretaris.MainActivitySekretaris;
import com.example.adminservice.api.Konfigurasi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
        EditText uss, pss;
        Boolean session = false;
        Button but;
        String user, pass;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

                ActionBar actionBar;
                actionBar = getSupportActionBar();
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

                uss = findViewById(R.id.uss);
                pss = findViewById(R.id.pss);

                but = findViewById(R.id.but);
                but.setOnClickListener(this);

                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                                @Override
                                public void onComplete(@NonNull Task<String> task) {
                                        if (!task.isSuccessful()) {
                                                Log.w("FCM", "Fetching FCM registration token failed", task.getException());
                                                return;
                                        }

                                        // Get new FCM registration token
                                        String token = task.getResult();

                                        // Log and toast
                                        Log.d("FCM", token);
                                        AddToken(token);
                                }
                        });
        }

        @Override
        public void onClick(View v) {
                switch (v.getId()) {
                        case R.id.but:
                                user = uss.getText().toString().trim();
                                pass = pss.getText().toString().trim();

                                login(user, pass);
                                break;
                }
        }

        private void login(String user, String pass){
                if (user.equals("Faruq") && pass.equals("12345")){
                        Intent intent = new Intent(Login.this, MainActivityAdmin.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                } else if (user.equals("sekretaris") && pass.equals("12345")){
                        startActivity(new Intent(Login.this, MainActivitySekretaris.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                } else if (user.equals("bendahara") && pass.equals("12345")){
                        startActivity(new Intent(Login.this, MainActivityBendahara.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                } else {
                        Toast.makeText(Login.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
        }

        private void AddToken(String Token) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_TOKEN,
                        new com.android.volley.Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                        Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                                }
                        }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("token", Token);
                                return params;
                        }
                };

                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(stringRequest);
        }


}