package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.auth.User;

import RoomDatabaseForUser.User;
import RoomDatabaseForUser.UserDatabase;

public class activity_update extends AppCompatActivity {
    private EditText edtUpdateName,edtUpdateQueQuan,edtUpdateChucVu,edtUpdateSoDienThoai,edtUpdateTaiKhoan, edtUpdateMatKhau;
    private Button btnUpdateteacher;
    private User mUser;
    private TextView txtStatusUpdateUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        InitUser();
        Intent myIntent = getIntent();
        mUser = (User) myIntent.getExtras().get("object");
        if (mUser != null) {
            edtUpdateName.setText(mUser.getHoTen());
            edtUpdateQueQuan.setText(mUser.getQueQuan());
            edtUpdateChucVu.setText(mUser.getChucVu());
            edtUpdateSoDienThoai.setText(mUser.getSoDienThoai());
            edtUpdateTaiKhoan.setText(mUser.getEmail());
            edtUpdateMatKhau.setText(mUser.getPassword());
        }
        btnUpdateteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDateUser();
            }
        });
    }

    private void upDateUser() {
        String strName = edtUpdateName.getText().toString().trim();
        String strQueQuan = edtUpdateQueQuan.getText().toString().trim();
        String strChucVu = edtUpdateChucVu.getText().toString().trim();
        String strSoDienThoai = edtUpdateSoDienThoai.getText().toString().trim();
        String strEmail = edtUpdateTaiKhoan.getText().toString().trim();
        String strPassword = edtUpdateMatKhau.getText().toString().trim();
        if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strQueQuan )|| TextUtils.isEmpty(strChucVu)
                || TextUtils.isEmpty(strPassword)) {
            return;
        }
        if ((strSoDienThoai.length() != 10)) {
            txtStatusUpdateUser.setVisibility(View.VISIBLE);
            txtStatusUpdateUser.setText("Số điện thoại chưa đúng");
            txtStatusUpdateUser.setTextColor(Color.RED);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches() || !(isCheckEmail(strEmail).equals("@gmail.com"))) {
            txtStatusUpdateUser.setVisibility(View.VISIBLE);
            txtStatusUpdateUser.setText("@gmail.com");
            txtStatusUpdateUser.setTextColor(Color.RED);
            return;
        }
        mUser.setHoTen(strName);
        mUser.setQueQuan(strQueQuan);
        mUser.setChucVu(strChucVu);
        mUser.setSoDienThoai(strSoDienThoai);
        mUser.setEmail(strEmail);
        mUser.setPassword(strPassword);
        UserDatabase.getInstance(this).userDao().updateUser(mUser);
        Toast.makeText(this,"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
        txtStatusUpdateUser.setVisibility(View.GONE);
        // Quay lại load dữ liệu
        Intent intent = new Intent();
        setResult(33,intent);
        finish();
    }

    // Hàm ánh xạ id
    private void InitUser() {
        edtUpdateName = findViewById(R.id.edt_update_name);
        edtUpdateQueQuan = findViewById(R.id.edt_update_que_quan);
        edtUpdateChucVu = findViewById(R.id.edt_update_chuc_vu);
        edtUpdateSoDienThoai = findViewById(R.id.edt_update_so_dien_thoai);
        edtUpdateTaiKhoan = findViewById(R.id.edt_update_tai_khoan);
        edtUpdateMatKhau = findViewById(R.id.edt_update_mat_khau);
        btnUpdateteacher = findViewById(R.id.btn_update_teacher);
        txtStatusUpdateUser = findViewById(R.id.txt_status_update_user);
    }
    private String isCheckEmail(String strEmail) {
        String[] mail ={};
        mail = strEmail.split("@");
        return "@"+mail[1];
    }
}