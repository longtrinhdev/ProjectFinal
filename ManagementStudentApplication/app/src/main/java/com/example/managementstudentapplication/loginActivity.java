package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import MVP.AdminLogin;
import MVP.AdminPresenter;
import MVP.LoginPresenter;

public class loginActivity extends AppCompatActivity {
    private Button btnLoginAdmin ,btnBackAdmin;
    private EditText edtAdminEmail, edtAdminPassword;
    private TextView txtStatus;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        Init();

        loginPresenter = new LoginPresenter(new AdminPresenter() {
            @Override
            public void loginAdminSuccess() {
                txtStatus.setVisibility(View.VISIBLE);
                txtStatus.setText("Đăng nhập thành công! ");
                txtStatus.setTextColor(Color.GREEN);
                Toast.makeText(loginActivity.this,"Đăng nhập thành công!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void loginAdminFailed() {
                txtStatus.setVisibility(View.VISIBLE);
                txtStatus.setText("Tài khoản hoặc mật khẩu chưa đúng! ");
                txtStatus.setTextColor(Color.RED);
            }
        });
        // hàm xử lý nút login
        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
                if (setLogin()) {
                    Intent intent = new Intent(loginActivity.this,teacher_activity.class);
                    startActivity(intent);
                }
            }
        });
        // Hàm xử lý nút back
        btnBackAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void onClickLogin() {
        String strEmail = edtAdminEmail.getText().toString().trim();
        String strPassword = edtAdminPassword.getText().toString().trim();
        AdminLogin admin = new AdminLogin(strEmail,strPassword);
        loginPresenter.loginAdmin(admin);
    }
    private  boolean setLogin() {
        String strEmail = edtAdminEmail.getText().toString().trim();
        String strPassword = edtAdminPassword.getText().toString().trim();
        AdminLogin admin = new AdminLogin(strEmail,strPassword);
        if (admin.isCheckEmail() && admin.ischeckPassword()) {
            return true;
        }
        return false;
    }

    // Hàm ánh xạ id
    private void Init() {
        edtAdminEmail = findViewById(R.id.edt_email);
        edtAdminPassword = findViewById(R.id.edt_password);
        txtStatus = findViewById(R.id.txt_status_login);
        btnLoginAdmin = findViewById(R.id.btn_Login);
        btnBackAdmin = findViewById(R.id.btn_backAdmin);
    }
}