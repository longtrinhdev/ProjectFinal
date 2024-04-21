package Fragment_Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;

import com.example.studentmanagementapplication.R;

import java.util.ArrayList;
import java.util.List;

import RoomDatabaseForStudent.Student;
import RoomDatabaseForStudent.StudentAdapter;
import RoomDatabaseForStudent.StudentDatabase;

public class QueryFragment extends Fragment implements IClickSendData {
    private Button btn_back;
    private View sView;

    // recyclerView
    private RecyclerView rcvStudent;
    private List<Student> lst;
    private StudentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sView = inflater.inflate(R.layout.layout_query,container,false);
        InitStudent();

        return sView;
    }

    private void InitStudent() {
        rcvStudent = sView.findViewById(R.id.rcy_list_query);
        btn_back = sView.findViewById(R.id.btn_back);

        lst = new ArrayList<>();
        adapter = new StudentAdapter();
        adapter.setDataStudent(lst);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvStudent.setLayoutManager(layoutManager);
        rcvStudent.setAdapter(adapter);

        loadDataStudent();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }
    public boolean isCheckStudent(Student student) {
        List<Student> lst1 = StudentDatabase.getInstanceStudent(getContext()).studentDao().isCheckStudent(student.getMsv());
        return !lst1.isEmpty() && lst1 != null;
    }

    public void loadDataStudent() {
        lst = StudentDatabase.getInstanceStudent(getContext()).studentDao().getListStudent();
        adapter.setDataStudent(lst);
    }

    @Override
    public void loadDataUser() {
        loadDataStudent();
    }

}

