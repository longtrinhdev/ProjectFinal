package RoomDatabaseForStudent;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementapplication.Information_Student;
import com.example.studentmanagementapplication.R;

import java.util.List;

public class SearchStudentAdapter extends RecyclerView.Adapter<SearchStudentAdapter.SearchViewHolder> {
    private List<Student> students;
    private IShowInforStudent iShowInforStudent;

    public interface IShowInforStudent {
        void showInforStudent(Student student);
    }

    public SearchStudentAdapter(IShowInforStudent iShowInforStudent) {
        this.iShowInforStudent = iShowInforStudent;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_search_student,parent,false);
        return new SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Student student = students.get(position);
        if (student == null) {
            return;
        }
        holder.txtSearchName.setText(student.getHoVaTen());
        holder.txtSearchMSV.setText(student.getMsv());
        // xử lý sự kiện bấm item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xử lý dữ liệu
                iShowInforStudent.showInforStudent(student);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (students == null) {
            return 0;
        }
        return students.size();
    }

    public  void setDataSearch(List<Student> myList) {
        this.students = myList;
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSearchName,txtSearchMSV;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSearchName = itemView.findViewById(R.id.txt_search_name);
            txtSearchMSV = itemView.findViewById(R.id.txt_search_msv);
        }
    }
}
