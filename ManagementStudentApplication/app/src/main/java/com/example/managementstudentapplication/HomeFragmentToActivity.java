package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import Fragment_Student.InsertFragment;
import Fragment_Student.QueryFragment;

public class HomeFragmentToActivity extends AppCompatActivity {
    // tạo các biến hằng để phân biệt
    private  static final  int FRAG_Q = 0;
    private  static final  int FRAG_I = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_to);
        ShowFragment();

    }
    // tạo hàm nhận biết giữa các sự kiện
    private void ShowFragment() {
        int a = getIntent().getIntExtra("query",99);
        int b = getIntent().getIntExtra("insert",99);

        if (a == FRAG_Q) {
            replaceFragment(new QueryFragment());
        }else if(b == FRAG_I) {
            replaceFragment(new InsertFragment());
        }
    }
    //tạo replace giữa các fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_Student,fragment);
        transaction.commit();
    }

}