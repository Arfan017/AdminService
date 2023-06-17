package com.example.adminservice.Sekretaris;

import static com.example.adminservice.api.Konfigurasi.URL_IMAGES;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.adminservice.R;
import com.example.adminservice.api.Konfigurasi;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Edit_Kegiatan extends AppCompatActivity implements View.OnClickListener {
    EditText idkeg, _tanggal, _waktu, _kegiatan, _nanggung, _keterangan;
    String id, tgl, wak, keg, nang, ket;
    Button sav, ex;
    ImageView imageKegiatan;
    private int jam, menit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kegiatan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        _tanggal = findViewById(R.id.tanggal);
        _waktu = findViewById(R.id.waktu);
        _kegiatan = findViewById(R.id.kegiatan);
        _nanggung = findViewById(R.id.penangung);
        _keterangan = findViewById(R.id.keterangan);
        imageKegiatan = findViewById(R.id.ImgKegiatan);

        id = getIntent().getStringExtra("id_kegiatan");
        String tanggal = getIntent().getStringExtra("tanggal");
        String waktu = getIntent().getStringExtra("waktu");
        String kegiatan = getIntent().getStringExtra("kegiatan");
        String penanggungJwb = getIntent().getStringExtra("penanggungJwb");
        String keterangan = getIntent().getStringExtra("keterangan");
        String gambar = getIntent().getStringExtra("gambar");
        String Status = getIntent().getStringExtra("status");

        _tanggal.setText(tanggal);
        _waktu.setText(waktu);
        _kegiatan.setText(kegiatan);
        _nanggung.setText(penanggungJwb);
        _keterangan.setText(keterangan);
        Glide.with(this).load(URL_IMAGES + gambar).into(imageKegiatan);

        if (Status.equals("setuju") || Status.equals("tolak")){
            _tanggal.setEnabled(false);
            _waktu.setEnabled(false);
            _kegiatan.setEnabled(false);
            _nanggung.setEnabled(false);
            _keterangan.setEnabled(false);
        }

        ex = findViewById(R.id.delete);
        ex.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);

        _tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Edit_Kegiatan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        _tanggal.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        _waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam = calendar.get(Calendar.DAY_OF_MONTH);
                menit = calendar.get(Calendar.DAY_OF_MONTH);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Edit_Kegiatan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam = hourOfDay;
                        menit = minute;

                        if (jam <= 12) {
                            _waktu.setText(String.format(Locale.getDefault(), "%d:%d am", jam, menit));
                        } else {
                            _waktu.setText(String.format(Locale.getDefault(), "%d:%d pm", jam, menit));
                        }
                    }
                }, jam, menit, true);
                timePickerDialog.show();
            }
        });
    }

    private void EditKegiatan() {
        tgl = _tanggal.getText().toString();
        wak = _waktu.getText().toString();
        keg = _kegiatan.getText().toString();
        nang = _nanggung.getText().toString();
        ket = _keterangan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_EDIT_KEGIATAN,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Kegiatan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Kegiatan.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kegiatan", id);
                params.put("tanggal", tgl);
                params.put("waktu", wak);
                params.put("kegiatan", keg);
                params.put("penanggungJwb", nang);
                params.put("keterangan", ket);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void DeleteKegiatan() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_DELETE_KEGIATAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Edit_Kegiatan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Edit_Kegiatan.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_kegiatan", id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.svv:

                tgl = _tanggal.getText().toString();
                wak = _waktu.getText().toString();
                keg = _kegiatan.getText().toString();
                nang = _nanggung.getText().toString();
                ket = _keterangan.getText().toString();

                if (tgl.length() == 0) {
                    _tanggal.setError("Harus Diisi");
                } else if (wak.length() == 0) {
                    _waktu.setError("Harus Diisi");
                } else if (keg.length() == 0) {
                    _kegiatan.setError("Harus Diisi");
                } else if (nang.length() == 0) {
                    _nanggung.setError("Harus Diisi");
                } else if (ket.length() == 0) {
                    _keterangan.setError("Harus Diisi");
                } else {
                    EditKegiatan();
                    startActivity(new Intent(Edit_Kegiatan.this, Activity_Kegiatan_Remaja.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;
            case R.id.delete:
                DeleteKegiatan();
                startActivity(new Intent(Edit_Kegiatan.this, Activity_Kegiatan_Remaja.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                break;
        }
    }
}

//        browse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dexter.withActivity(Edit_Kegiatan.this)
//                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                        .withListener(new PermissionListener() {
//                            @Override
//                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                intent.setType("image/*");
//                                startActivityForResult(Intent.createChooser(intent, "pilih gambar"), 1);
//                            }
//
//                            @Override
//                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                            }
//
//                            @Override
//                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                                permissionToken.continuePermissionRequest();
//                            }
//                        }).check();
//            }
//        });

//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            Uri filepath = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(filepath);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                imageKegiatan.setImageBitmap(bitmap);
//                encodebitmap(bitmap);
//            } catch (Exception ex) {
////                throw new RuntimeException(ex);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    private void encodebitmap(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] byteofimage = byteArrayOutputStream.toByteArray();
//        encodedImageString = android.util.Base64.encodeToString(byteofimage, Base64.DEFAULT);
//    }