package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import RoomDatabaseForStudent.Student;
import RoomDatabaseForStudent.StudentDatabase;

public class activity_update_student extends AppCompatActivity {
    private EditText edtMSV,edtUpdateName,edtUpdateQQ,edtUpdateToan,edtUpdateVan,edtUpdateAnh,edtUpdateXepLoai;
    private Button btnUpdateStudent;
    private Student mStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        Init();
        Intent intent = getIntent();
        mStudent =(Student) intent.getExtras().get("student");
        if (mStudent != null) {
            edtMSV.setText(mStudent.getMsv());
            edtUpdateName.setText(mStudent.getHoVaTen());
            edtUpdateQQ.setText(mStudent.getQueQuan());
            edtUpdateToan.setText(""+mStudent.getDiemToan());
            edtUpdateVan.setText(""+mStudent.getDiemVan());
            edtUpdateAnh.setText(""+mStudent.getDiemAnh());
            edtUpdateXepLoai.setText(""+mStudent.getXepLoai());
        }
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStudent();
            }
        });
    }

    private void UpdateStudent() {
        String strMSV = edtMSV.getText().toString().trim();
        String strName = edtUpdateName.getText().toString().trim();
        String strQQ = edtUpdateQQ.getText().toString().trim();
        float strToan = Float.parseFloat(edtUpdateToan.getText().toString().trim());
        float strVan = Float.parseFloat(edtUpdateVan.getText().toString().trim());
        float strAnh = Float.parseFloat(edtUpdateAnh.getText().toString().trim());
        String strXepLoai = edtUpdateXepLoai.getText().toString().trim();

        if (TextUtils.isEmpty(strMSV)|| TextUtils.isEmpty(strName)) {
            return;
        }
        mStudent.setMsv(strMSV);
        mStudent.setHoVaTen(strName);
        mStudent.setQueQuan(strQQ);
        mStudent.setDiemToan(strToan);
        mStudent.setDiemVan(strVan);
        mStudent.setDiemAnh(strAnh);
        mStudent.setXepLoai(strXepLoai);
        StudentDatabase.getInstanceStudent(this).studentDao().updateStudent(mStudent);
        Toast.makeText(this,"Update thành công!",Toast.LENGTH_SHORT).show();
        // load lại dữ liệu
        Intent myIntent = new Intent();
        setResult(33,myIntent);
        finish();
    }

    private void Init() {
        edtMSV = findViewById(R.id.edt_update_msv);
        edtUpdateName = findViewById(R.id.edt_update_name_student);
        edtUpdateQQ = findViewById(R.id.edt_update_QQ);
        edtUpdateToan = findViewById(R.id.edt_update_diem_toan);
        edtUpdateVan = findViewById(R.id.edt_update_diem_van);
        edtUpdateAnh = findViewById(R.id.edt_update_diem_anh);
        edtUpdateXepLoai = findViewById(R.id.edt_update_xep_loai);
        btnUpdateStudent = findViewById(R.id.btn_update_student_1);

    }


}