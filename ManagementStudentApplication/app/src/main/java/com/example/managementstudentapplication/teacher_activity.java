package com.example.managementstudentapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

import RoomDatabaseForUser.User;
import RoomDatabaseForUser.UserAdapter;
import RoomDatabaseForUser.UserDatabase;

public class teacher_activity extends AppCompatActivity {
    private EditText edtHoTen,edtQueQuan,edtChucVu,edtSoDienThoai,edtEmail,edtPassWord;
//    private EditText edtSearch;
    private TextView txtStatusLoginAdmin;
    private Button btnInsert;
    private RecyclerView rcvLstUser;

    // Khai báo các thành phần của RecyclerView
    private List<User> myList;
    private UserAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Init();
        myList = new ArrayList<>();
        myAdapter = new UserAdapter(new UserAdapter.IlickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                clickDeleteUser(user);
            }
        });
        myAdapter.setData(myList);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(teacher_activity.this);
        rcvLstUser.setLayoutManager(linearLayoutManager);
        rcvLstUser.setAdapter(myAdapter);
        // Xử lý các sự kiện
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inSertUserForList();
            }
        });
        loadData();
    }

    // Hàm update
    private void clickUpdateUser(User user) {
        Intent intent = new Intent(teacher_activity.this, activity_update.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",user);
        intent.putExtras(bundle);
        startActivityForResult(intent,99);
    }
    // Hàm xóa
    private void clickDeleteUser( final User user) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa tài khoản người dùng")
                .setMessage("Bạn chắc chắn xóa?")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDatabase.getInstance(teacher_activity.this).userDao().deleteUser(user);
                        Toast.makeText(teacher_activity.this,"Bạn đã xóa thành công!",Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                }).show();
    }

    private void inSertUserForList() {
        String strName = edtHoTen.getText().toString().trim();
        String strQueQuan = edtQueQuan.getText().toString().trim();
        String strChucVu = edtChucVu.getText().toString().trim();
        String strSoDienThoai = edtSoDienThoai.getText().toString().trim();
        String strEmail = edtEmail.getText().toString().trim();
        String strPassword = edtPassWord.getText().toString().trim();
        txtStatusLoginAdmin.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(strName) || TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strChucVu)
                || TextUtils.isEmpty(strPassword)) {
            return;
        }
        if (!(strSoDienThoai.length() == 10) && !TextUtils.isEmpty(strEmail)) {
            txtStatusLoginAdmin.setText("Số điện thoại chưa đúng");
            txtStatusLoginAdmin.setTextColor(Color.RED);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches() || !(isCheckEmail(strEmail).equals("@gmail.com"))) {
            txtStatusLoginAdmin.setText("@gmail.com");
            txtStatusLoginAdmin.setTextColor(Color.RED);
            return;
        }

        User user = new User(strName,strQueQuan,strChucVu,strSoDienThoai,strEmail,strPassword);
        if (isCheckUser(user)) {
            Toast.makeText(teacher_activity.this,"Đã tồn tại!",Toast.LENGTH_SHORT).show();
            txtStatusLoginAdmin.setVisibility(View.GONE);
            return;
        }
        // add dữ liệu vào database
        UserDatabase.getInstance(teacher_activity.this).userDao().insert(user);
        Toast.makeText(teacher_activity.this,"Thêm giáo viên thành công!",Toast.LENGTH_SHORT).show();
        txtStatusLoginAdmin.setVisibility(View.GONE);
        edtHoTen.setText("");
        edtQueQuan.setText("");
        edtChucVu.setText("");
        edtSoDienThoai.setText("");
        edtEmail.setText("");
        edtPassWord.setText("");
        // Hiển thị trên RecyclerView
        loadData();
    }

    private void Init() {
        edtHoTen = findViewById(R.id.edt_fullname);
        edtQueQuan = findViewById(R.id.edt_address);
        edtChucVu  = findViewById(R.id.edt_level);
        edtSoDienThoai = findViewById(R.id.edt_phoneNumber);
        edtEmail = findViewById(R.id.edt_account);
        edtPassWord = findViewById(R.id.edt_user_password);
//        edtSearch = findViewById(R.id.edt_search);
        btnInsert = findViewById(R.id.btn_insert);
        rcvLstUser = findViewById(R.id.rcv_list_teacher);
        txtStatusLoginAdmin = findViewById(R.id.txt_status_login_admin);
    }
    // Hàm kiểm tra user
    public boolean isCheckUser(User user) {
        List<User> users = UserDatabase.getInstance(teacher_activity.this).userDao().checkerUser(user.getEmail());
        return !users.isEmpty() && users != null;
    }
    // Hàm load Data
    private void loadData() {
        myList = UserDatabase.getInstance(teacher_activity.this).userDao().getList();
        myAdapter.setData(myList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33) {
            loadData();
        }
    }
    // Hàm tách đuôi gmail
    private String isCheckEmail(String strEmail) {
        String[] mail ={};
        mail = strEmail.split("@");
        return "@"+mail[1];
    }
}