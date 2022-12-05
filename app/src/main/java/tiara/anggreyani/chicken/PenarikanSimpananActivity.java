package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PenarikanSimpananActivity extends AppCompatActivity {

    FloatingActionButton popupmenu;

    Button btnkirim;
    Dialog mdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penarikan_simpanan);

        ImageView PlafonLogo = findViewById(R.id.plafon);
        ImageView TokoLogo = findViewById(R.id.toko);
        ImageView AkunLogo = findViewById(R.id.akun);
        ImageView HomeLogo = findViewById(R.id.home);
        ImageView DataPenarikan = findViewById(R.id.datapenarikanputih);
        ImageView back = findViewById(R.id.back);

        //popup
        popupmenu = findViewById(R.id.menu);

        //popup menu
        //Popup menu
        popupmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpMenu();
            }
        });

        mdialog = new Dialog(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PenarikanSimpananActivity.super.onBackPressed();
            }
        });

//        btnkirim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openSuksesDialog();
//            }
//            private void openSuksesDialog() {
//                mdialog.setContentView(R.layout.alert_sukses);
//                mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                Button btnSukses = mdialog.findViewById(R.id.btnSukses);
//                TextView infosukses = mdialog.findViewById(R.id.infosukses);
//
//                infosukses.setText("Penarikan Simpanan Berhasil \n Dikirim");
//                btnSukses.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mdialog.dismiss();
//                        //Toast.makeText(HomeActivity.this, "Saldo Plafon Ditutup", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                mdialog.show();
//            }
//        });

        DataPenarikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, PenarikanSimpanan2Activity.class));
            }
        });

        //Bottom Bar
        PlafonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, PlafonActivity.class));
            }
        });
        TokoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, TokoActivity.class));
            }
        });
        AkunLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, ProfileActivity.class));
            }
        });
        HomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, HomeActivity.class));
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
                startActivity(new Intent(PenarikanSimpananActivity.this, WishlistActivity.class));
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
                startActivity(new Intent(PenarikanSimpananActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PenarikanSimpananActivity.this, PerubahanSimpananActivity.class));
            }
        });
        mdialog.show();
    }
}