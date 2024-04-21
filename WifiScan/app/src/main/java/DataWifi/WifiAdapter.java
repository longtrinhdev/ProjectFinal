package DataWifi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wifiscan.R;

import java.text.DecimalFormat;
import java.util.List;

public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.WifiViewHolder> {
    private List<OutData> myList;

    @NonNull
    @Override
    public WifiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return  new WifiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WifiViewHolder holder, int position) {
        OutData outData = myList.get(position);
        if (outData == null) {
            return;
        }
        holder.txtSSID.setText(outData.getSSID());
        holder.txtBSSID.setText(outData.getBSSID());
        holder.txtLevel.setText(outData.getLevel() +" dBm");

        // Rào dữ liệu
        String decimalFormat= String.format("#0.%0" + 3 +"d",0);
        DecimalFormat df = new DecimalFormat(decimalFormat);
        // Ox
        String Gx = df.format(outData.getGx());
        String Gy = df.format(outData.getGy());
        String Gz = df.format(outData.getGz());
        // oy
        String Ax = df.format(outData.getAx());
        String Ay = df.format(outData.getAy());
        String Az = df.format(outData.getAz());
        // Oz
        String Mx = df.format(outData.getMx());
        String My = df.format(outData.getMy());
        String Mz = df.format(outData.getMz());


//        if (outData.getGx() == 0.0 || outData.getGy()==0.0 || outData.getGz() == 0.0) {
//            holder.txtGx.setText("(0" + Gx);
//            holder.txtGy.setText( " , 0"+ Gy);
//            holder.txtGz.setText(" , 0" + Gz +")");
//        }else {
            holder.txtGx.setText("(" + Gx);
            holder.txtGy.setText( ";  "+ Gy);
            holder.txtGz.setText(";  " + Gz +")");
        //}

//        if(outData.getAx() == 0.0 || outData.getAy()==0.0 || outData.getAz() == 0.0){
//            holder.txtAx.setText("(0" + Ax+" , 0");
//            holder.txtAy.setText( Ay+" , 0");
//            holder.txtAz.setText(Az +")");
//
//        }else {
            holder.txtAx.setText("(" + Ax+" ; ");
            holder.txtAy.setText( Ay+" ; ");
            holder.txtAz.setText(Az +")");
        //}

//        if (outData.getMx() == 0.0 || outData.getMy()==0.0 || outData.getMz() == 0.0) {
//            holder.txtMx.setText("(0" + Mx+" , 0");
//            holder.txtMy.setText( My+" , 0");
//            holder.txtMz.setText(Mz+")");
//        }else {
            holder.txtMx.setText("(" + Mx+" ; ");
            holder.txtMy.setText( My+" ; ");
            holder.txtMz.setText(Mz+")");
        //}


    }

    @Override
    public int getItemCount() {
        if (myList == null) {
            return 0;
        }
        return myList.size();
    }

    public void setData(List<OutData> list) {
        this.myList = list;
        notifyDataSetChanged();
    }

    public class WifiViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSSID,txtBSSID, txtLevel,txtGx,txtGy,txtGz,
                            txtAx, txtAy,txtAz, txtMx,txtMy, txtMz;

        public WifiViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSSID = itemView.findViewById(R.id.txt_ssid);
            txtBSSID = itemView.findViewById(R.id.txt_bssid);
            txtLevel = itemView.findViewById(R.id.txt_level);
            txtGx = itemView.findViewById(R.id.txt_gyrox);
            txtGy = itemView.findViewById(R.id.txt_gyroy);
            txtGz = itemView.findViewById(R.id.txt_gyroz);
            txtAx = itemView.findViewById(R.id.txt_accx);
            txtAy = itemView.findViewById(R.id.txt_accy);
            txtAz = itemView.findViewById(R.id.txt_accz);
            txtMx = itemView.findViewById(R.id.txt_magnetic_x);
            txtMy = itemView.findViewById(R.id.txt_magnetic_y);
            txtMz = itemView.findViewById(R.id.txt_magnetic_z);
        }

    }
}
