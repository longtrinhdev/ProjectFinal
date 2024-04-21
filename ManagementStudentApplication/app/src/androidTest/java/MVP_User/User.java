package MVP_User;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.studentmanagementapplication.MainActivity;

import java.util.List;

import RoomDatabaseForUser.UserDatabase;

public class User {
    private String account;
    private String password;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
