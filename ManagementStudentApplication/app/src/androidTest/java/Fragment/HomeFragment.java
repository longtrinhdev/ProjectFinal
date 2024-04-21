package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentmanagementapplication.HomeFragmentToActivity;
import com.example.studentmanagementapplication.activity_update_student_1;
import com.example.studentmanagementapplication.R;
import com.example.studentmanagementapplication.Activity_Search;

public class HomeFragment extends Fragment {
    private View hView;
    private Button btn_insert,btn_query,btn_delete,btn_search,btn_update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        hView=  inflater.inflate(R.layout.home_fragment,container,false);

        InitHomeFragment();
        return hView;
    }
    private void InitHomeFragment() {
        btn_insert = hView.findViewById(R.id.btn_insertUser);
        btn_query = hView.findViewById(R.id.btn_queryUser);
        btn_update = hView.findViewById(R.id.btn_updateUser);
        btn_delete =  hView.findViewById(R.id.btn_deleteUser);
        btn_search =  hView.findViewById(R.id.btn_searchUser);

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeFragmentToActivity.class);
                intent.putExtra("query",0);
                startActivity(intent);
            }
        });

        // xử lý sự kiện insert
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeFragmentToActivity.class);
                intent.putExtra("insert",1);
                startActivity(intent);
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),activity_update_student_1.class);
                startActivity(intent);
            }
        });
        // sự kiện xóa
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),activity_update_student_1.class);
                startActivity(intent);
            }
        });
        // search
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Activity_Search.class);
                startActivity(intent);
            }
        });

    }
}
