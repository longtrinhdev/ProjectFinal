package MVP;

import android.text.TextUtils;
import android.util.Patterns;

public class AdminLogin {
    private String email;
    private String password;

    public AdminLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCheckEmail() {
        String isEmail = "admin1505@gmail.com";
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.equals(isEmail);
    }
    // hàm invalid<Rules>
    public boolean ischeckPassword() {
        String isPassword = "admin1505@";
        return  password.equals(isPassword);
    }
}
