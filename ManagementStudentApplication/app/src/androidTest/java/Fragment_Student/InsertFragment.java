package Fragment_Student;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementapplication.R;

import java.util.List;

import RoomDatabaseForStudent.Student;
import RoomDatabaseForStudent.StudentDatabase;


public class InsertFragment extends Fragment {
    private View iView;
    private EditText edtHoTen,edtMSV,edtQueQuan,edtDiemToan,edtDiemVan,edtDiemAnh,edtXepLoai;
    private Button btn_insert;
    private IClickSendData iClickSendData;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        iView = inflater.inflate(R.layout.layout_insert_fragment,container,false);
        InitInsertStudent();

        return iView;
    }

    private void  InitInsertStudent() {
        edtHoTen = iView.findViewById(R.id.edt_hoTen);
        edtMSV = iView.findViewById(R.id.edt_msv);
        edtQueQuan = iView.findViewById(R.id.edt_que_quan);
        edtDiemToan = iView.findViewById(R.id.edt_diem_toan);
        edtDiemVan = iView.findViewById(R.id.edt_diem_van);
        edtDiemAnh = iView.findViewById(R.id.edt_diem_anh);
        edtXepLoai = iView.findViewById(R.id.edt_xep_loai);
        btn_insert = iView.findViewById(R.id.btn_insert_student);

        //Xử lý sự kiện
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStudentForList();
            }
        });
    }
    private void reloadData() {
        if (iClickSendData != null) {
            iClickSendData.loadDataUser();
        }
    }
    public void setDataLoadListener(IClickSendData iClickSendData) {
        this.iClickSendData = iClickSendData;
    }

    private void insertStudentForList() {
        String strMSV= edtMSV.getText().toString().trim();
        String strHoTen= edtHoTen.getText().toString().trim();
        String strQueQuan= edtQueQuan.getText().toString().trim();
        float strDiemToan= Float.parseFloat(edtDiemToan.getText().toString().trim());
        float strDiemVan= Float.parseFloat(edtDiemVan.getText().toString().trim());
        float strDiemAnh= Float.parseFloat(edtDiemAnh.getText().toString().trim());
        String strXepLoai= edtXepLoai.getText().toString().trim();

        if (TextUtils.isEmpty(strMSV) || TextUtils.isEmpty(strHoTen)) {
            return;
        }
        Student student = new Student(strMSV,strHoTen,strQueQuan,strDiemToan,strDiemVan,strDiemAnh,strXepLoai);
        if (isCheckStudent(student)) {
            Toast.makeText(getContext(),"Đã tồn tại!",Toast.LENGTH_SHORT).show();
            return;
        }
        //add dữ liệu
        StudentDatabase.getInstanceStudent(getContext()).studentDao().insertStudent(student);
        Toast.makeText(getContext(),"Thêm bản ghi thành công!",Toast.LENGTH_SHORT).show();
        edtMSV.setText("");
        edtHoTen.setText("");
        edtQueQuan.setText("");
        edtDiemToan.setText("");
        edtDiemVan.setText("");
        edtDiemAnh.setText("");
        edtXepLoai.setText("");
        reloadData();
    }
    public boolean isCheckStudent(Student student) {
        List<Student> lst1 = StudentDatabase.getInstanceStudent(getContext()).studentDao().isCheckStudent(student.getMsv());
        return !lst1.isEmpty() && lst1 != null;
    }

}
