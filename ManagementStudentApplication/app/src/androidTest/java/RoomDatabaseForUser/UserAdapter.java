package RoomDatabaseForUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagementapplication.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> lst;
    //xử lý click của recylerview
    private IlickItemUser  ilickItemUser;
    public interface IlickItemUser {
        void updateUser(User user);
        void deleteUser(User user);
    }

    public UserAdapter(IlickItemUser ilickItemUser) {
        this.ilickItemUser = ilickItemUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = lst.get(position);
        if (user == null) {
            return;
        }
        holder.txtNameTeacher.setText(user.getHoTen());
        holder.txtPhoneNumber.setText(user.getSoDienThoai());
        // sự kiện update
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilickItemUser.updateUser(user);
            }
        });
        // xóa
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilickItemUser.deleteUser(user);
            }
        });
    }
    // Lấy kích thước
    @Override
    public int getItemCount() {
        if (lst.isEmpty()) {
            return 0;
        }
        return lst.size();
    }
    public void setData(List<User> myList) {
        this.lst = myList;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        // Khai báo các thành phần
        private TextView txtNameTeacher,txtPhoneNumber;
        private Button btnUpdate,btnDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameTeacher = itemView.findViewById(R.id.txt_full_name);
            txtPhoneNumber = itemView.findViewById(R.id.txt_phone_number);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
