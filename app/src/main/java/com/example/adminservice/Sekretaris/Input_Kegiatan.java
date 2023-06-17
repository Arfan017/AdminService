package com.example.adminservice.Sekretaris;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import com.example.adminservice.R;
import com.example.adminservice.api.Konfigurasi;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import android.Manifest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Input_Kegiatan extends AppCompatActivity implements View.OnClickListener {

    EditText _ETtanggal, _ETwaktu, _ETkegiatan, _ETpenanggungJwb, _ETKeterangan;
    String tanggal, waktu, kegiatan, penanggungJwb, Keterangan, encodedImageString;
    ImageView imageKegiatan;
    Button sav, ex, browse;
    Bitmap bitmap;
    private int jam, menit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_kegiatan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B4C6BC")));

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        _ETtanggal = findViewById(R.id.tanggal);
        _ETwaktu = findViewById(R.id.waktu);
        _ETkegiatan = findViewById(R.id.kegiatan);
        _ETpenanggungJwb = findViewById(R.id.penangung);
        _ETKeterangan = findViewById(R.id.keterangan);
        imageKegiatan = findViewById(R.id.ImgKegiatan);

        ex = findViewById(R.id.exi);
        ex.setOnClickListener(this);
        sav = findViewById(R.id.svv);
        sav.setOnClickListener(this);
        browse = findViewById(R.id.browse);

        _ETtanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Input_Kegiatan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        _ETtanggal.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        _ETwaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                jam = calendar.get(Calendar.DAY_OF_MONTH);
                menit = calendar.get(Calendar.DAY_OF_MONTH);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Input_Kegiatan.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        jam = hourOfDay;
                        menit = minute;

                        if (jam <= 12) {
                            _ETwaktu.setText(String.format(Locale.getDefault(), "%d:%d am", jam, menit));
                        } else {
                            _ETwaktu.setText(String.format(Locale.getDefault(), "%d:%d pm", jam, menit));
                        }
                    }
                }, jam, menit, true);
                timePickerDialog.show();
            }
        });

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(Input_Kegiatan.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent =  new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "pilih gambar"), 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == 1 && resultCode == RESULT_OK){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageKegiatan.setImageBitmap(bitmap);
                encodebitmap(bitmap);
            } catch (Exception ex) {
//                throw new RuntimeException(ex);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    };

    private void encodebitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteofimage = byteArrayOutputStream.toByteArray();
        encodedImageString = android.util.Base64.encodeToString(byteofimage, Base64.DEFAULT);
    }

    private void inputData() {
        tanggal = _ETtanggal.getText().toString();
        waktu = _ETwaktu.getText().toString();
        kegiatan = _ETkegiatan.getText().toString();
        penanggungJwb = _ETpenanggungJwb.getText().toString();
        Keterangan = _ETKeterangan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_ADD_KEGIATAN,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Input_Kegiatan.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Input_Kegiatan.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tanggal", tanggal);
                params.put("waktu", waktu);
                params.put("kegiatan", kegiatan);
                params.put("penanggungJwb", penanggungJwb);
                params.put("keterangan", Keterangan);
                params.put("gambar", encodedImageString);
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
                tanggal = _ETtanggal.getText().toString();
                waktu = _ETwaktu.getText().toString();
                kegiatan = _ETkegiatan.getText().toString();
                penanggungJwb = _ETpenanggungJwb.getText().toString();
                Keterangan = _ETKeterangan.getText().toString();

                if (tanggal.length() == 0) {
                    _ETtanggal.setError("Harus Diisi");
                } else if (waktu.length() == 0) {
                    _ETwaktu.setError("Harus Diisi");
                } else if (kegiatan.length() == 0) {
                    _ETkegiatan.setError("Harus Diisi");
                } else if (penanggungJwb.length() == 0) {
                    _ETpenanggungJwb.setError("Harus Diisi");
                } else if (Keterangan.length() == 0) {
                    _ETKeterangan.setError("Harus Diisi");
                } else {
                    inputData();
                    startActivity(new Intent(Input_Kegiatan.this, Activity_Kegiatan_Remaja.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }
                break;

            case R.id.exi:
                startActivity(new Intent(Input_Kegiatan.this, Activity_Kegiatan_Remaja.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
        }
    }
}