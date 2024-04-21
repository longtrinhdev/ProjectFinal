package com.example.managementstudentapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import Fragment.HomeFragment;
import Fragment.InforUser_Fragment;
import Fragment.ChangePassword_Fragment;
import RoomDatabaseForUser.User;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mdrawerLayout;
    private TextView txtNavName, txtNavEmail;
    // Tạo biến hằng để phân biệt giữa các fragment
    private  static  final int HOME_FRAGMENT = 0;
    private  static  final int INFOR_USER_FRAGMENT = 1;
    private  static  final int CHANGE_PASSWORD_FRAGMENT = 2;
    private User mUser;

    private int mCURRENT_FRAGMENT = HOME_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mdrawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        mdrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // mới vào app vào home luôn
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.mnu_home).setChecked(true);
        getSupportActionBar().setTitle("Home");
        setHeaderUser();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnu_home) {
            if (mCURRENT_FRAGMENT != HOME_FRAGMENT) {
                replaceFragment(new HomeFragment());
                mCURRENT_FRAGMENT = HOME_FRAGMENT;
                getSupportActionBar().setTitle("Home");
            }
        }else if (id == R.id.mnu_log_out) {
            new AlertDialog.Builder(MainActivity2.this)
                    .setTitle("Thoát")
                    .setMessage("Bạn chắc chắn muốn thoát?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No",null)
                    .create()
                    .show();

        }else if (id == R.id.mnu_infor) {
            if (mCURRENT_FRAGMENT != INFOR_USER_FRAGMENT) {
                replaceFragment(new InforUser_Fragment());
                mCURRENT_FRAGMENT = INFOR_USER_FRAGMENT;
                getSupportActionBar().setTitle("Personal Information");

                sendDataFromAcToFag(new InforUser_Fragment());
            }
        }else if (id == R.id.mnu_change_password) {
            if (mCURRENT_FRAGMENT != CHANGE_PASSWORD_FRAGMENT) {
                replaceFragment(new ChangePassword_Fragment());
                mCURRENT_FRAGMENT = CHANGE_PASSWORD_FRAGMENT;
                getSupportActionBar().setTitle("Change Password");
                // xử lý sự kiện
                sendDataFromAcToFag(new ChangePassword_Fragment());
            }
        }
        mdrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void sendDataFromAcToFag(Fragment fragment) {
        Bundle myBunle = new Bundle();
        myBunle.putSerializable("objects",mUser);
        fragment.setArguments(myBunle);
        replaceFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_home,fragment);
        transaction.commit();
    }
    private void setHeaderUser() {
        NavigationView navigationView =findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txtNavName = headerView.findViewById(R.id.txt_nav_name);
        txtNavEmail = headerView.findViewById(R.id.txt_nav_email);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("users")) {
            User user = (User) intent.getExtras().get("users");
            mUser = user;
            if (user != null) {
                txtNavName.setText(user.getHoTen());
                txtNavEmail.setText(user.getEmail());
            }
        }
    }
}