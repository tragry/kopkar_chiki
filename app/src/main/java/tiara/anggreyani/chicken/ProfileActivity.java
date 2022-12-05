package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tiara.anggreyani.chicken.Model.Profile.ProfileResponse;

public class ProfileActivity extends AppCompatActivity {

    FloatingActionButton popupmenu;
    Dialog mdialog;

    SharedPrefManager sharedPrefManager;
    TextView nik, nama_lengkap, no_ktp, jenis, tempat_lahir, tanggal_lahir,
            alamat, status, no_hp, departemen, jabatan, grade, nama,
            mulai, selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView logoutButton = findViewById(R.id.logout);
        ImageView ubahPassword = findViewById(R.id.ubah_password);
        ImageView PlafonLogo = findViewById(R.id.plafon);
        ImageView TokoLogo = findViewById(R.id.toko);
        ImageView HomeLogo = findViewById(R.id.home);
        nik = findViewById(R.id.nik);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        no_ktp = findViewById(R.id.no_ktp);
        jenis = findViewById(R.id.jenis);
        tempat_lahir = findViewById(R.id.tempat_lahir);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        alamat = findViewById(R.id.alamat);
        status = findViewById(R.id.status);
        no_hp = findViewById(R.id.no_hp);
        departemen = findViewById(R.id.departemen);
        jabatan= findViewById(R.id.jabatan);
        grade = findViewById(R.id.grade);
        nama = findViewById(R.id.nama);
        mulai= findViewById(R.id.mulai);
        selesai= findViewById(R.id.selesai);
        ImageView back = findViewById(R.id.back);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.super.onBackPressed();
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

        sharedPrefManager = new SharedPrefManager(this);

        dataProfile();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(ProfileActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
        ubahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UbahPasswordActivity.class));
            }
        });
        PlafonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, PlafonActivity.class));
            }
        });
        TokoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, TokoActivity.class));
            }
        });
        HomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
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
                startActivity(new Intent(ProfileActivity.this, WishlistActivity.class));
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
                startActivity(new Intent(ProfileActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, PerubahanSimpananActivity.class));
            }
        });
        mdialog.show();
    }

        public void dataProfile(){

        String token = " "+sharedPrefManager.getSpToken();
        Call<ProfileResponse> profileResponseCall = ApiClient.getUserservice().dataProfile( "Bearer"+" "+token);
            profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                    if(response.isSuccessful()){
                        //Sapaan
                        nama.setText(""+response.body().getName());

                        //Data Pengguna
                        nik.setText(""+response.body().getNoKaryawan());
                        nama_lengkap.setText(" "+response.body().getName());
                        no_ktp.setText(" "+response.body().getNoKtp());
                        jenis.setText(" "+response.body().getGender());
                        tempat_lahir.setText(" "+response.body().getTempatLahir());
                        tanggal_lahir.setText(" "+response.body().getTglLahir());
                        alamat.setText(" "+response.body().getAddress());
                        status.setText(" "+response.body().getStatusPerkawinan());
                        no_hp.setText(" "+response.body().getPhone());
                        departemen.setText(" "+response.body().getDepartemen());
                        jabatan.setText(" "+response.body().getJabatan());
                        grade.setText(" "+response.body().getGrade());

                        //Keanggotaan
                        mulai.setText(" "+response.body().getTglMulai());
                        selesai.setText(" "+response.body().getTglSelesai());
                    }else{
                        Toast.makeText(ProfileActivity.this, "Data User Gagal Dimuat", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    Toast.makeText(ProfileActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

}