package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tiara.anggreyani.chicken.Model.Plafon.PlafonResponse;

public class PlafonActivity extends AppCompatActivity {

    FloatingActionButton popupmenu;
    Dialog mdialog;

    SharedPrefManager sharedPrefManager;
    TextView plafon, plafonuse, plafonsisa, nomsisa_plafon;
    ImageView back_plafon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plafon);

        ImageView HomeLogo = findViewById(R.id.home);
        ImageView TokoLogo = findViewById(R.id.toko);
        ImageView AkunLogo = findViewById(R.id.akun);
        plafon = findViewById(R.id.kuota_plafon);
        plafonuse = findViewById(R.id.digunakan_plafon);
        plafonsisa = findViewById(R.id.sisa_plafon);
        nomsisa_plafon = findViewById(R.id.nomsisa_plafon);
        back_plafon = findViewById(R.id.back_plafon);

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


        back_plafon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlafonActivity.super.onBackPressed();
            }
        });

        sharedPrefManager = new SharedPrefManager(this);

        dataPlafon();

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        HomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlafonActivity.this, HomeActivity.class));
            }
        });
        TokoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlafonActivity.this, TokoActivity.class));
            }
        });
        AkunLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlafonActivity.this, ProfileActivity.class));
            }
        });
    }
    public void dataPlafon(){
        String token = " "+sharedPrefManager.getSpToken();
        Call<PlafonResponse> plafonResponseCall = ApiClient.getUserservice().dataPlafon("Bearer"+" "+token);
        plafonResponseCall.enqueue(new Callback<PlafonResponse>() {
            @Override
            public void onResponse(Call<PlafonResponse> call, Response<PlafonResponse> response) {

                if(response.isSuccessful()){
                    int var1 = response.body().getSisa_plafon1();
                    int var2 = response.body().getSisa_plafon2();
                    int total_plafonuse = var1 + var2;
                    plafonuse.setText("Rp. "+currencyFormat(""+total_plafonuse));

                    int num1 = response.body().getTotal_nilai_plafon();
                    int num2 = total_plafonuse;
                    int sisa = num1 - num2;
                    plafonsisa.setText("Rp. "+currencyFormat(""+sisa));
                    nomsisa_plafon.setText("Rp. "+currencyFormat(""+sisa));

                    plafon.setText("Rp. "+currencyFormat(""+response.body().getTotal_nilai_plafon()));
                    //sisa.setText("Rp. "+currencyFormat(""+response.body().getData().getPlafon().getSisaPlafon().getReguler()));

                }else{
                    Toast.makeText(PlafonActivity.this, "Page Plafon Gagal Dimuat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PlafonResponse> call, Throwable t) {
                Toast.makeText(PlafonActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        return formatter.format(Double.parseDouble(amount));
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
                startActivity(new Intent(PlafonActivity.this, WishlistActivity.class));
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
                startActivity(new Intent(PlafonActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlafonActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlafonActivity.this, PerubahanSimpananActivity.class));
            }
        });
        mdialog.show();
    }
}