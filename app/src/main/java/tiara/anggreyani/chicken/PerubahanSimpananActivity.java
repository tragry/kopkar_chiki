package tiara.anggreyani.chicken;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PerubahanSimpananActivity extends AppCompatActivity {

    FloatingActionButton popupmenu;

    ImageView btnkirim;
    TextView simpananBaru;
    Dialog mdialog;

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] version = {"Rp. 50,000","Rp. 100,000","Rp. 150,000", "Rp. 200,000", "Rp. 250,000",
            "Rp. 300,000","Rp. 350,000","Rp. 400,000", "Rp. 450,000", "Rp. 450,000", "Rp. 500,000",
            "Rp. 550,000","Rp. 600,000","Rp. 650,000","Rp. 700,000","Rp. 750,000","Rp. 800,000",
            "Rp. 850,000","Rp. 900,000","Rp. 950,000","Rp. 1,000,000","Rp. 1,050,000","Rp. 1,100,000","Rp. 1,150,000",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perubahan_simpanan);

        ImageView PlafonLogo = findViewById(R.id.plafon);
        ImageView TokoLogo = findViewById(R.id.toko);
        ImageView AkunLogo = findViewById(R.id.akun);
        ImageView HomeLogo = findViewById(R.id.home);
        ImageView back = findViewById(R.id.back);
        simpananBaru = findViewById(R.id.list_simpanan_baru);

        //popup
        popupmenu = findViewById(R.id.menu);
        mdialog = new Dialog(this);

        //popup menu
        //Popup menu
        popupmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpMenu();
            }
        });

        btnkirim = findViewById(R.id.btnKirim);

        simpananBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSimpananBaru();
            }
        });

        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openSuksesDialog();
                openGagalDialog();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerubahanSimpananActivity.super.onBackPressed();
            }
        });

        //Bottom Bar
        PlafonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerubahanSimpananActivity.this, PlafonActivity.class));
            }
        });
        TokoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerubahanSimpananActivity.this, TokoActivity.class));
            }
        });
        AkunLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerubahanSimpananActivity.this, ProfileActivity.class));
            }
        });
        HomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerubahanSimpananActivity.this, HomeActivity.class));
            }
        });
    }

    public void openGagalDialog() {
        mdialog.setContentView(R.layout.alert_gagal);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnGagal = mdialog.findViewById(R.id.btnGagal);
        TextView infogagal = mdialog.findViewById(R.id.infogagal);

        infogagal.setText("Perubahan Simpanan Gagal \n Disimpan");
        btnGagal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                //Toast.makeText(HomeActivity.this, "Saldo Plafon Ditutup", Toast.LENGTH_SHORT).show();
            }
        });
        mdialog.show();
    }
    public void openSuksesDialog() {
        mdialog.setContentView(R.layout.alert_sukses);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnSukses = mdialog.findViewById(R.id.btnSukses);
        TextView infosukses = mdialog.findViewById(R.id.infosukses);

        infosukses.setText("Perubahan Simpanan Berhasil \n Disimpan");
        btnSukses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                //Toast.makeText(HomeActivity.this, "Saldo Plafon Ditutup", Toast.LENGTH_SHORT).show();
            }
        });
        mdialog.show();
    }

    public void listSimpananBaru(){
        mdialog.setContentView(R.layout.list_perubahan_simpanan);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        listView = mdialog.findViewById(R.id.list_view);

        list = new ArrayList<>();

        for (int i = 0;i<version.length;i++){
            list.add(version[i]);
        }
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter(PerubahanSimpananActivity.this,android.R.layout.simple_list_item_single_choice,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                simpananBaru.setText(version[i]);
                mdialog.dismiss();
//                Toast.makeText(PerubahanSimpananActivity.this, "Selected -> " + version[i], Toast.LENGTH_SHORT).show();
////                mdialog.dismiss();
            }
        });

        mdialog.show();
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
                startActivity(new Intent(PerubahanSimpananActivity.this, WishlistActivity.class));
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
                startActivity(new Intent(PerubahanSimpananActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerubahanSimpananActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerubahanSimpananActivity.this, PerubahanSimpananActivity.class));
            }
        });
        mdialog.show();
    }
}