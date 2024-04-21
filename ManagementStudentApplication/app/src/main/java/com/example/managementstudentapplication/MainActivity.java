package com.example.managementstudentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.auth.User;

import java.util.List;

import MVP_User.User;
import RoomDatabaseForUser.UserDao_Impl;
import RoomDatabaseForUser.UserDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView txtAdmin,txtStatusLoginUser;
    private EditText edtAccount,edtMatKhau;
    private Button btnLoginUser;
    private List<RoomDatabaseForUser.User> lst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtAdmin = findViewById(R.id.txt_admin);
        txtStatusLoginUser = findViewById(R.id.txt_status_login_user);
        edtAccount  = findViewById(R.id.edt_username);
        edtMatKhau = findViewById(R.id.edt_password_user);
        btnLoginUser = findViewById(R.id.btn_login_user);

        txtAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheckLoginUser()) {
                    txtStatusLoginUser.setVisibility(View.VISIBLE);
                    txtStatusLoginUser.setText("Đăng nhập thành công");
                    txtStatusLoginUser.setTextColor(Color.BLUE);
                    Intent myIntent = new Intent(MainActivity.this,MainActivity2.class);
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                    hideSoftKeyboard();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("users",user());
                    myIntent.putExtras(bundle);
                    startActivity(myIntent);
                }else {
                    txtStatusLoginUser.setVisibility(View.VISIBLE);
                    txtStatusLoginUser.setText("Tài khoản hoặc mật khẩu sai");
                    txtStatusLoginUser.setTextColor(Color.RED);
                }
            }
        });
    }
    private boolean isCheckLoginUser() {
        String strAccount = edtAccount.getText().toString().trim();
        String strPasswordUser = edtMatKhau.getText().toString().trim();
        User user = new User(strAccount,strPasswordUser);
        lst = UserDatabase.getInstance(MainActivity.this).userDao().getList();
        for(int i =0 ; i< lst.size(); i++) {
            if (user.getAccount().equals(lst.get(i).getEmail()) && Patterns.EMAIL_ADDRESS.matcher(user.getAccount()).matches()) {
                if (user.getPassword().equals(lst.get(i).getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }
    private RoomDatabaseForUser.User user() {
        String strAccount = edtAccount.getText().toString().trim();
        String strPasswordUser = edtMatKhau.getText().toString().trim();
        User user = new User(strAccount,strPasswordUser);
        lst = UserDatabase.getInstance(MainActivity.this).userDao().getList();
        for(int i =0 ; i< lst.size(); i++) {
            if (user.getAccount().equals(lst.get(i).getEmail()) && Patterns.EMAIL_ADDRESS.matcher(user.getAccount()).matches()) {
                if (user.getPassword().equals(lst.get(i).getPassword())) {
                    return lst.get(i);
                }
            }
        }
        return null;
    }
    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }


}