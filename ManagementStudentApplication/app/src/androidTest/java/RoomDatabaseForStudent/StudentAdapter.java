package RoomDatabaseForStudent;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementapplication.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> myList;

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sstudent,parent,false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = myList.get(position);
        if (student == null) {
            return;
        }
        holder.txtStudentName.setText(student.getHoVaTen());
        holder.txtDiemToan.setText(""+student.getDiemToan());
        holder.txtDiemVan.setText(""+student.getDiemAnh());
        holder.txtXepLoai.setText(student.getXepLoai());
        holder.txtXepLoai.setTextColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        if (myList == null) {
            return 0;
        }
        return myList.size();
    }
    public void setDataStudent(List<Student> lst) {
        this.myList = lst;
        notifyDataSetChanged();
    }

    //Tạo ViewHolder
    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView txtStudentName,txtDiemToan,txtDiemVan,txtDiemAnh,txtXepLoai;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStudentName = itemView.findViewById(R.id.txt_student_name);
            txtDiemToan = itemView.findViewById(R.id.txt_diem_toan);
            txtDiemVan = itemView.findViewById(R.id.txt_diem_van);
            txtDiemAnh = itemView.findViewById(R.id.txt_diem_anh);
            txtXepLoai = itemView.findViewById(R.id.txt_xep_loai);

        }
    }

}
