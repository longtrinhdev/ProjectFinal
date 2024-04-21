package com.example.wifiscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    private TextView txtTenFile;
    private AppCompatButton btnQuetWifi, btnBack;
    private EditText edtNhapN, edtNhapOx, edtNhapOy, edtNhapOz;
    private LocationManager locationManager;
    private WifiManager wifiManager;
    private Handler wifiScanHandler = new Handler();
    private boolean isStart = false;



    @SuppressLint("MissingInflatedId") // ko có id trong file xml thì ko lỗi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Init();

        String nameFile = getIntent().getStringExtra("file");
        txtTenFile.setText(nameFile+".csv");

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);


        btnQuetWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edtNhapN.getText().toString().trim();
                String ox = edtNhapOx.getText().toString().trim();
                String oy = edtNhapOy.getText().toString().trim();
                String oz = edtNhapOz.getText().toString().trim();

                if (!checkWifiStatus()) {
                    Toast.makeText(MainActivity2.this,"Hãy bật wifi!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!checkLocationStatus()) {
                    Toast.makeText(MainActivity2.this,"Hãy bật GPS!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(a)) {
                    Toast.makeText(MainActivity2.this,"Hãy nhập số lần quét",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ox) || TextUtils.isEmpty(oy) || TextUtils.isEmpty(oz)) {
                    Toast.makeText(MainActivity2.this,"Hãy nhập tọa độ điểm",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isCheckInteger(a)) {
                    int n = Integer.parseInt(a);
                    float Ox = Float.parseFloat(edtNhapOx.getText().toString().trim());
                    float Oy = Float.parseFloat(edtNhapOy.getText().toString().trim());
                    float Oz = Float.parseFloat(edtNhapOz.getText().toString().trim());

                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",nameFile);
                    bundle.putInt("n",n);
                    bundle.putFloat("Ox",Ox);
                    bundle.putFloat("Oy",Oy);
                    bundle.putFloat("Oz",Oz);

                    intent.putExtra("data",bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity2.this,"Nhập sô nguyên!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // sự kiện thoát
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtTenFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile(nameFile);
            }
        });
    }
    // mở file

    private void openFile(String nameFile) {
        File downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(downloadsDirectory, nameFile+".csv");

        if (file.exists()) {
            Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "text/csv");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Xử lý nếu không có ứng dụng nào có thể xử lý tệp này
            }
        } else {
            // Xử lý nếu tệp không tồn tại trong thư mục download
            Toast.makeText(this, "File not found in Downloads folder", Toast.LENGTH_SHORT).show();
        }
    }

    private void  Init() {
        txtTenFile = findViewById(R.id.txtTenFile);
        btnQuetWifi = findViewById(R.id.btn_quet);
        btnBack = findViewById(R.id.btn_back);
        edtNhapN = findViewById(R.id.edt_nhap_so_lan_quet);
        edtNhapOx = findViewById(R.id.edt_nhap_OX);
        edtNhapOy = findViewById(R.id.edt_nhap_OY);
        edtNhapOz = findViewById(R.id.edt_nhap_OZ);
    }

    private boolean isCheckInteger(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkLocationStatus() {
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isGPSEnabled;
    }
    // check status wifi
    private boolean checkWifiStatus() {
        return wifiManager.isWifiEnabled();
    }
}