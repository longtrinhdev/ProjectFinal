package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementapplication.R;

import RoomDatabaseForUser.User;

public class InforUser_Fragment extends Fragment {
    private View mView;
    private TextView txtChucVu, txtSDT,txtQueQuan,txtEmail,txtName;
    public InforUser_Fragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Vấn đề xuất phát từ việc bạn gọi phương thức InitUI() trước khi inflating layout của fragment.
        // Điều này làm cho mView vẫn là null khi bạn cố gắng truy cập các view bên trong nó.
        mView= inflater.inflate(R.layout.infor_user_fragment,container,false);
        InitUI();
        return mView;
    }

    private void InitUI() {
        txtName = mView.findViewById(R.id.txt_fragment_name);
        txtChucVu = mView.findViewById(R.id.txt_frag_chuc_vu);
        txtSDT = mView.findViewById(R.id.txt_frag_sdt);
        txtQueQuan = mView.findViewById(R.id.txt_frag_address);
        txtEmail = mView.findViewById(R.id.txt_frag_email);

        Bundle bundle = getArguments();
        if (bundle != null) {
            User user = (User) bundle.get("objects");
            if (user != null) {
                txtName.setText(user.getHoTen());
                txtChucVu.setText(user.getChucVu());
                txtSDT.setText(user.getSoDienThoai());
                txtQueQuan.setText(user.getQueQuan());
                txtEmail.setText(user.getEmail());
            }
        }
    }

}
