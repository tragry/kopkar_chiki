package tiara.anggreyani.chicken;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SimpananAdapter extends RecyclerView.Adapter<SimpananAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[], data5[], data6[];
    int hitung[];
    Context context;

    public  SimpananAdapter(Context ct, String simpanan[], String id[], String jmlpersetor[], String totalsimpanan[],
                            String totalpenarikan[], String totalsaldo[], int htg[]){
        context = ct;
        data1 = simpanan;
        data2 = id;
        data3 = jmlpersetor;
        data4 = totalsimpanan;
        data5 = totalpenarikan;
        data6 = totalsaldo;
        hitung = htg;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_item_simpanan_karyawan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.simpananSimpanan.setText(data1[position]);
        holder.idSimpanan.setText(data2[position]);
        holder.jmlPersetorSimpanan.setText(data3[position]);
        holder.totalSimpanan.setText(data4[position]);
        holder.totalPenarikan.setText(data5[position]);
        holder.totalSaldo.setText(data6[position]);

//        holder.card_simpanan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailBarangActivity.class);
//                intent.putExtra("data1", data1[holder.getAdapterPosition()]);
//                intent.putExtra("data2", data2[holder.getAdapterPosition()]);
//                intent.putExtra("data3", data3[holder.getAdapterPosition()]);
//                intent.putExtra("data4", data4[holder.getAdapterPosition()]);
//                intent.putExtra("data5", data5[holder.getAdapterPosition()]);
//                intent.putExtra("data6", data6[holder.getAdapterPosition()]);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return hitung.length;
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        TextView simpananSimpanan, idSimpanan, jmlPersetorSimpanan, totalSimpanan, totalPenarikan, totalSaldo;
        CardView card_simpanan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            simpananSimpanan = itemView.findViewById(R.id.tv_simpanan);
            idSimpanan = itemView.findViewById(R.id.tv_id);
            jmlPersetorSimpanan = itemView.findViewById(R.id.tv_jmlPersetor);
            totalSimpanan = itemView.findViewById(R.id.tv_totalSimpanan);
            totalPenarikan = itemView.findViewById(R.id.tv_totalPenarikan);
            totalSaldo = itemView.findViewById(R.id.tv_totalSaldo);
            card_simpanan = itemView.findViewById(R.id.card_simpanan);
        }
    }
}
