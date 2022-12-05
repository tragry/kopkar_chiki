package tiara.anggreyani.chicken;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.MyViewHolder> {

    String data1[], data2[], data3[], data4[], data5[], data6[];
    int images[];
    Context context;

    public BarangAdapter(Context ct, String nama[], String harga[], String stock[], int img[],
                         String rating[], String deskripsi[], String satuan[]){
        context = ct;
        data1 = nama;
        data2 = harga;
        data3 = stock;
        data4 = rating;
        data5 = deskripsi;
        data6 = satuan;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_card_item_layout_barang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.namaBarang.setText(data1[position]);
        holder.hargaBarang.setText(data2[position]);
        holder.stockBarang.setText(data3[position]);
        holder.images.setImageResource(images[position]);

        holder.card_barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBarangActivity.class);
                intent.putExtra("data1", data1[holder.getAdapterPosition()]);
                intent.putExtra("data2", data2[holder.getAdapterPosition()]);
                intent.putExtra("data3", data3[holder.getAdapterPosition()]);
                intent.putExtra("data4", data4[holder.getAdapterPosition()]);
                intent.putExtra("data5", data5[holder.getAdapterPosition()]);
                intent.putExtra("data6", data6[holder.getAdapterPosition()]);
                intent.putExtra("myImage", images[holder.getAdapterPosition()]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView namaBarang, hargaBarang, stockBarang;
        CircleImageView images;
        RelativeLayout card_barang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            namaBarang = itemView.findViewById(R.id.tv_item_name);
            hargaBarang = itemView.findViewById(R.id.tv_item_price);
            stockBarang = itemView.findViewById(R.id.tv_jumlah_stock);
            images = itemView.findViewById(R.id.img_item_photo);
            card_barang = itemView.findViewById(R.id.card_barang);
        }
    }
}


/*
package tiara.anggreyani.chicken;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import tiara.anggreyani.chicken.DetailBarangActivity;
import tiara.anggreyani.chicken.R;

public class BarangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    RecyclerView mRecyclerView;
    private boolean isLoading;
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    Context c;
    String idSelect;

    String nama[], harga[], stock[];
    int img[];

    public BarangAdapter(Context c, RecyclerView mRecyclerView, String nama[], String harga[], String stock[], int img[]) {
        this.mRecyclerView = mRecyclerView;
        this.c = c;
        this.idSelect = idSelect;
        this.nama = nama;
        this.harga = harga;
        this.stock = stock;
        this.img = img;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            //View view = LayoutInflater.from(c).inflate(R.layout., parent, false);
            View view = LayoutInflater.from(c).inflate(R.layout.activity_card_item_layout_barang, parent, false);
            return new BarangAdapter.UserViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(c).inflate(R.layout.progressbar, parent, false);
            return new BarangAdapter.LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BarangAdapter.UserViewHolder) {
            holder.namaBarang.setText(data1[position]);
            holder.hargaBarang.setText(data2[position]);
            holder.stockBarang.setText(data3[position]);
            holder.images.setImageResource(images[position]);

            holder.item_barang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailBarangActivity.class);
                    intent.putExtra("data1", data1[position]);
                    intent.putExtra("data2", data2[position]);
                    intent.putExtra("data3", data3[position]);
                    intent.putExtra("myImage", images[position]);
                    context.startActivity(intent);
                }

        }
    }

    @Override
    public int getItemCount() {
        return img == null ? 0 : img.length;
    }

    @Override
    public int getItemViewType(int position) {
        return myArray.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        LinearLayout parent;

        public UserViewHolder(View view) {
            super(view);
            c = itemView.getContext();

            name = view.findViewById(R.id.name);
            parent = view.findViewById(R.id.parent);

        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }*/
    //}