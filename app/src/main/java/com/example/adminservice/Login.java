package com.example.adminservice;

import static com.example.adminservice.api.Konfigurasi.URL_LOGIN;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adminservice.Admin.MainActivityAdmin;
import com.example.adminservice.Bendahara.MainActivityBendahara;
import com.example.adminservice.Sekretaris.MainActivitySekretaris;
import com.example.adminservice.api.Konfigurasi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
        EditText ETuss, ETpss;
        Boolean session = false;
        String status;
        Button but;
        String user, pass;
        private SessionManager sessionManager;
        private RequestQueue requestQueue;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

                ActionBar actionBar;
                actionBar = getSupportActionBar();
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

                ETuss = findViewById(R.id.uss);
                ETpss = findViewById(R.id.pss);

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
                                user = ETuss.getText().toString().trim();
                                pass = ETpss.getText().toString().trim();
                                login(user, pass);
                                break;
                }
        }

        private void login(String username, String password) {
                // Buatkan request untuk mengirim data ke server
                StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN,
                        new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                        try {
                                                JSONObject jsonObject = new JSONObject(response);
                                                boolean success = jsonObject.getBoolean("success");
                                                String message = jsonObject.getString("message");

                                                if (success) {
                                                        // Login berhasil
                                                        String id = jsonObject.getString("id");
                                                        // Lakukan sesuatu berdasarkan role pengguna (admin, sekretaris, bendahara)
                                                        if (id.equals("1")) {
                                                                // Pengguna dengan peran admin
                                                                // Redirect ke halaman admin
                                                                Intent intent = new Intent(Login.this, MainActivityAdmin.class)
                                                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                                finish();
                                                        } else if (id.equals("2")) {
                                                                // Pengguna dengan peran sekretaris
                                                                // Redirect ke halaman sekretaris
                                                                Intent intent = new Intent(Login.this, MainActivityBendahara.class)
                                                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                                finish();
                                                        } else if (id.equals("3")) {
                                                                // Pengguna dengan peran bendahara
                                                                // Redirect ke halaman bendahara
                                                                Intent intent = new Intent(Login.this, MainActivitySekretaris.class)
                                                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                                finish();
                                                        }
                                                } else {
                                                        // Login gagal
                                                        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                                                }
                                        } catch (JSONException e) {
                                                e.printStackTrace();
                                        }
                                }
                        },
                        new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Login.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }) {
                        @Override
                        protected Map<String, String> getParams() {
                                // Mengirim data username dan password ke server
                                Map<String, String> params = new HashMap<>();
                                params.put("username", username);
                                params.put("password", password);
                                return params;
                        }
                };
                // Menambahkan request ke antrian request Volley
                Volley.newRequestQueue(this).add(request);
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