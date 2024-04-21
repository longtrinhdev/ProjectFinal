package Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementapplication.MainActivity2;
import com.example.studentmanagementapplication.R;

import RoomDatabaseForUser.User;
import RoomDatabaseForUser.UserDatabase;

public class ChangePassword_Fragment extends Fragment {

    private View cView;
    private TextView txtStatusChangePassword;
    private EditText edtOldPassword,edtNewPassword,edtAgainPassword;
    private Button btnChangePassword;
    private User user;
    private MainActivity2 mainActivity2;

    public  ChangePassword_Fragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cView = inflater.inflate(R.layout.change_password_fragment,container,false);
        mainActivity2 = (MainActivity2) getActivity();
        InitCP();
        updateNewPassword();
        return cView;
    }

    private void InitCP() {
        txtStatusChangePassword = cView.findViewById(R.id.txt_status_change_password);
        edtOldPassword = cView.findViewById(R.id.edt_password_old);
        edtNewPassword = cView.findViewById(R.id.edt_password_new);
        edtAgainPassword = cView.findViewById(R.id.edt_password_new_again);
        btnChangePassword = cView.findViewById(R.id.btn_Change_password);
    }
    private int isCheckPassword() {
        String strOldPass = edtOldPassword.getText().toString().trim();
        String strNewPass = edtNewPassword.getText().toString().trim();
        String strNewPassAgain = edtAgainPassword.getText().toString().trim();
        int cnt =0 ;
        if (cView !=null) {
            user = (User) getArguments().get("objects");
            if (user != null) {
                if (!strOldPass.equals(user.getPassword())) {
                    cnt=1;
                }else  {
                    if(strNewPass.equals(strNewPassAgain)) {
                        cnt =0;
                    }else {
                        cnt =2;
                    }
                }
            }
        }
        return  cnt;
    }
    private void updateNewPassword() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtStatusChangePassword.setVisibility(View.VISIBLE);
                if (isCheckPassword() == 1) {
                    txtStatusChangePassword.setText("Mật khẩu cũ chưa đúng");
                    txtStatusChangePassword.setTextColor(Color.RED);
                }
                else if(isCheckPassword() == 2) {
                    txtStatusChangePassword.setText("Mật khẩu mới chưa đúng");
                    txtStatusChangePassword.setTextColor(Color.RED);
                }
                else if (isCheckPassword() == 0) {

                    String strNewPassAgain = edtAgainPassword.getText().toString().trim();
                    user.setPassword(strNewPassAgain);
                    UserDatabase.getInstance(getContext()).userDao().updateUser(user);
                    txtStatusChangePassword.setText("Thay đổi thành công");
                    txtStatusChangePassword.setTextColor(Color.BLUE);

                }
            }
        });

    }

}
