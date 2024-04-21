package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import RoomDatabaseForStudent.SearchStudentAdapter;
import RoomDatabaseForStudent.Student;
import RoomDatabaseForStudent.StudentDatabase;
import RoomDatabaseForStudent.SearchStudentAdapter.IShowInforStudent;


public class Activity_Search extends AppCompatActivity {
    private EditText edtSearch;
    private RecyclerView rcvSearch;

    private List<Student> lst;
    private SearchStudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtSearch = findViewById(R.id.edt_input_search);
        rcvSearch = findViewById(R.id.rcv_search_student);

        lst = new ArrayList<>();
        adapter = new SearchStudentAdapter(new SearchStudentAdapter.IShowInforStudent() {
            @Override
            public void showInforStudent(Student student) {
                clickItemShowInfor(student);
            }
        });
        adapter.setDataSearch(lst);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSearch.setLayoutManager(linearLayoutManager);
        rcvSearch.setAdapter(adapter);
        // sự kiện search
        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handlSearchStudent();
                }
                return false;
            }
        });
        loadData();
    }

    private void clickItemShowInfor(Student student) {
        Intent intent = new Intent(this,Information_Student.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",student);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void handlSearchStudent() {
        String edtData = edtSearch.getText().toString().trim();
        // xóa dữ liệu cũ
        lst = new ArrayList<>();
        lst = StudentDatabase.getInstanceStudent(this).studentDao().search(edtData);
        adapter.setDataSearch(lst);
        hideSoftKeyboard();
    }

    // ẩn bàn phím nhập
    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    private void loadData() {
        lst = StudentDatabase.getInstanceStudent(this).studentDao().getListStudent();
        adapter.setDataSearch(lst);
    }
}