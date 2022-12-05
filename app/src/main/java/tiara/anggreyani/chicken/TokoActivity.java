package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tiara.anggreyani.chicken.HomeActivity;
import tiara.anggreyani.chicken.PlafonActivity;
import tiara.anggreyani.chicken.ProfileActivity;
import tiara.anggreyani.chicken.R;

public class TokoActivity extends AppCompatActivity {

    FloatingActionButton popupmenu;
    Dialog mdialog;

    String nama[], harga[], stock[], rating[], deskripsi[], satuan[];
    RecyclerView rvBarang;
    int images[] = {R.drawable.gillete, R.drawable.abc, R.drawable.ballpoint, R.drawable.amplop, R.drawable.zwitsal, R.drawable.roma_malkist};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko);

        ImageView HomeLogo = findViewById(R.id.home);
        ImageView PlafonLogo = findViewById(R.id.plafon);
        ImageView AkunLogo = findViewById(R.id.akun);
        ImageView back = findViewById(R.id.back);

        nama = getResources().getStringArray(R.array.nama_barang);
        harga = getResources().getStringArray(R.array.harga_barang);
        stock = getResources().getStringArray(R.array.stock_barang);
        rating = getResources().getStringArray(R.array.rating_barang);
        deskripsi = getResources().getStringArray(R.array.deskripsi_barang);
        satuan = getResources().getStringArray(R.array.satuan_barang);
        //popup
        popupmenu = findViewById(R.id.menu);
        mdialog = new Dialog(this);


        rvBarang = findViewById(R.id.list_barang);

        BarangAdapter barangAdapter = new BarangAdapter(this, nama, harga, stock, images, rating, deskripsi, satuan);
        rvBarang.setAdapter(barangAdapter);
        rvBarang.setLayoutManager(new LinearLayoutManager(this));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TokoActivity.super.onBackPressed();
            }
        });

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //Popup menu
        popupmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpMenu();
            }
        });


        HomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, HomeActivity.class));
            }
        });
        PlafonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, PlafonActivity.class));
            }
        });
        AkunLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, ProfileActivity.class));
            }
        });
    }
    public void PopUpMenu(){
        mdialog.setContentView(R.layout.popup_menu);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView pinjaman_uang = mdialog.findViewById(R.id.pinjaman_uang);
        ImageView wishlist_menu = mdialog.findViewById(R.id.wishlist_menu);
        ImageView simpanan_karyawan = mdialog.findViewById(R.id.simpanan_karyawan);
        ImageView pengajuan_pinjaman = mdialog.findViewById(R.id.pengajuan_pinjaman);
        ImageView penarikan_simpanan = mdialog.findViewById(R.id.penarikan_simpanan);
        ImageView perubahan_simpanan = mdialog.findViewById(R.id.perubahan_simpanan);

        pinjaman_uang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        wishlist_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, WishlistActivity.class));
            }
        });
        simpanan_karyawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        pengajuan_pinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TokoActivity.this, PerubahanSimpananActivity.class));
            }
        });
        mdialog.show();
    }
}