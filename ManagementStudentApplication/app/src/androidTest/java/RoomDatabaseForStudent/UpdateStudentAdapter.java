package RoomDatabaseForStudent;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementapplication.R;

import java.util.List;

public class UpdateStudentAdapter extends RecyclerView.Adapter<UpdateStudentAdapter.UpdateViewholder> {
    private List<Student> lst;

    private IClickItem iClickItem;

    public interface IClickItem{
        void updateStudent(Student student);
        void deleteStudent(Student student);
    }
    // hàm khởi tạo


    public UpdateStudentAdapter(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    @NonNull
    @Override
    public UpdateViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_update,parent,false);
        return  new UpdateViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateViewholder holder, int position) {
        final Student student = lst.get(position);
        if (student == null) {
            return;
        }
        holder.txtUpdateName.setText(student.getHoVaTen());
        holder.txtUpdateMsv.setText(student.getMsv());
        // xử lý sự kiện click button
        holder.btn_update_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.updateStudent(student);
            }
        });
        // sự kiện xóa
        holder.btn_delete_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItem.deleteStudent(student);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (lst == null) {
            return 0 ;
        }
        return lst.size();
    }

    public  void setDataUpdate(List<Student> lst) {
        this.lst = lst;
        notifyDataSetChanged();
    }
    public  class UpdateViewholder extends RecyclerView.ViewHolder {
        private TextView txtUpdateName,txtUpdateMsv;
        private Button btn_update_student,btn_delete_student;
        public UpdateViewholder(@NonNull View itemView) {
            super(itemView);
            txtUpdateName = itemView.findViewById(R.id.txt_update_name);
            txtUpdateMsv = itemView.findViewById(R.id.txt_update_msv);
            btn_update_student = itemView.findViewById(R.id.btn_update_student);
            btn_delete_student = itemView.findViewById(R.id.btn_delete_student);
        }
    }
}
