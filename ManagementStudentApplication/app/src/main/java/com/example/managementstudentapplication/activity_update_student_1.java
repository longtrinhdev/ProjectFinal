package com.example.managementstudentapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import RoomDatabaseForStudent.Student;
import RoomDatabaseForStudent.StudentDatabase;
import RoomDatabaseForStudent.UpdateStudentAdapter;

public class activity_update_student_1 extends AppCompatActivity {
    private RecyclerView rcv_lst_update;
    private List<Student> myList;
    private UpdateStudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student1);
        InitUpdate();

    }
    private void InitUpdate() {
        rcv_lst_update = findViewById(R.id.rcv_update);

        myList = new ArrayList<>();
        adapter = new UpdateStudentAdapter(new UpdateStudentAdapter.IClickItem() {
            @Override
            public void updateStudent(Student student) {
                // hàm sự kiện click
                clickUpdateStudent(student);
            }

            @Override
            public void deleteStudent(Student student) {
                clickDeleteStudent(student);
            }
        });
        adapter.setDataUpdate(myList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_lst_update.setLayoutManager(linearLayoutManager);
        rcv_lst_update.setAdapter(adapter);
        loadData();
    }

    private void clickUpdateStudent(Student student) {
        Intent intent = new Intent(this,activity_update_student.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("student",student);
        intent.putExtras(bundle);
        startActivityForResult(intent,99);

    }

    private void clickDeleteStudent(Student student) {
        new AlertDialog.Builder(this)
                .setTitle("Thông báo")
                .setMessage("Bạn chắc chắn muốn xóa")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StudentDatabase.getInstanceStudent(activity_update_student_1.this).studentDao().deleteStudent(student);
                        Toast.makeText(activity_update_student_1.this,"Bạn đã xóa thành công!",Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                }).create()
                .show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33) {
            loadData();
        }
    }
    private void loadData() {
        myList = StudentDatabase.getInstanceStudent(this).studentDao().getListStudent();
        adapter.setDataUpdate(myList);
    }
}