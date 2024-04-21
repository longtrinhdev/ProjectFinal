package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import RoomDatabaseForStudent.Student;

public class Information_Student extends AppCompatActivity {
    private TextView txtInforName,txtInforMSV,txtInforXepLoai,txtInforToan,txtInforVan,txtInforAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);
        Init();
        ResultForIntent();
    }

    private void Init() {
        txtInforMSV = findViewById(R.id.txt_infor_msv);
        txtInforName = findViewById(R.id.txt_infor_name);
        txtInforXepLoai = findViewById(R.id.txt_infor_xep_loai);
        txtInforToan = findViewById(R.id.txt_infor_toan);
        txtInforVan = findViewById(R.id.txt_infor_van);
        txtInforAnh = findViewById(R.id.txt_infor_anh);
    }
    private void ResultForIntent() {
        Intent intent = getIntent();
        Student student = (Student)intent.getExtras().get("object");
        if (student == null) {
            return;
        }
        txtInforName.setText(student.getHoVaTen());
        txtInforMSV.setText(student.getMsv());
        txtInforXepLoai.setText(student.getXepLoai());
        txtInforXepLoai.setTextColor(Color.RED);
        txtInforToan.setText(""+student.getDiemToan());
        txtInforToan.setTextColor(Color.RED);
        txtInforVan.setText(""+student.getDiemVan());
        txtInforVan.setTextColor(Color.RED);
        txtInforAnh.setText(""+student.getDiemAnh());
        txtInforAnh.setTextColor(Color.RED);

    }

}