package tiara.anggreyani.chicken;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.internal.FastServiceLoaderKt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tiara.anggreyani.chicken.Model.Home.HomeResponse;
import tiara.anggreyani.chicken.Model.Plafon.PlafonResponse;
import tiara.anggreyani.chicken.Model.Simpanan.SimpananResponse;

public class HomeActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    AnyChartView anyChartView;
    Dialog mdialog;

    ImageView PlafonLogo, TokoLogo, AkunLogo; // Bottom Bar
    ImageView Home2, User2; // Top Bar

    TextView pinjaman, saldoSimpanan, saldoPlafon; //Deskripsi Chart
    ImageView pengajuan_pinjaman, penarikan_simpanan, perubahan_simpanan, wishlist; //Menu Dashboard
    ImageView popuppinjaman, popupsimpanan, popupplafon; //Popup
    TextView plafonsisa, plafontotal; //Popup Plafon
    FloatingActionButton popupmenu;

    //Popup Simpanan
    TextView SimpananPokok, SimpananWajib, SimpananPensiun, SimpananSukarela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Bottom Bar
        PlafonLogo = findViewById(R.id.plafon);
        TokoLogo = findViewById(R.id.toko);
        AkunLogo = findViewById(R.id.akun);
        //Top Bar
        Home2 = findViewById(R.id.home2);
        User2 = findViewById(R.id.user2);

        //Deskripsi Chart
        pinjaman = (TextView) findViewById(R.id.pinjaman_home);
        saldoSimpanan = (TextView) findViewById(R.id.simpanan_home);
        saldoPlafon = (TextView) findViewById(R.id.plafon_home);

        //Menu dashboard
        pengajuan_pinjaman = findViewById(R.id.pengajuan_pinjaman);
        penarikan_simpanan = findViewById(R.id.penarikan_simpanan);
        perubahan_simpanan = findViewById(R.id.perubahan_simpanan);
        wishlist = findViewById(R.id.wishlist);

        //Popup
        popuppinjaman = findViewById(R.id.logo_pinjaman);
        popupmenu = findViewById(R.id.menu);
        popupsimpanan = findViewById(R.id.logosimpanan);
        popupplafon = findViewById(R.id.logoplafon);
        mdialog = new Dialog(this);

        sharedPrefManager = new SharedPrefManager(this);

        dataHome();

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //PopUp Pinjaman
        popuppinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpPinjaman();
            }
        });
        pinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpPinjaman();
            }
        });

        //Pop Up Simpanan
        popupsimpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpSimpanan();
            }
        });
        saldoSimpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpSimpanan();
            }
        });

        //Pop Up Plafon
        popupplafon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopUpPlafon();
            }
        });
        saldoPlafon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpPlafon();
            }
        });
        //Popup menu
        popupmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpMenu();
            }
        });

        //4 menu dashboard
        pengajuan_pinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PerubahanSimpananActivity.class));
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WishlistActivity.class));
            }
        });



        //TopBar
        Home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });
        User2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        //Bottom Bar
        PlafonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PlafonActivity.class));
            }
        });
        TokoLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, TokoActivity.class));
            }
        });
        AkunLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

    }

    public void dataHome(){
        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        String token = sharedPrefManager.getSpToken();
        Call<HomeResponse> homeResponseCall = ApiClient.getUserservice().dataHome("Bearer"+" "+token);
        homeResponseCall.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {

                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("Token is Expired")){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }else {
                        Pie pie = AnyChart.pie();
                        List<DataEntry> data = new ArrayList<>();
                        data.add(new ValueDataEntry("Pinjaman", response.body().getKuranganPinjaman()));
                        data.add(new ValueDataEntry("Saldo Simpanan", response.body().getSimpanan()));
                        data.add(new ValueDataEntry("Saldo Plafon", response.body().getSisaPlafon()));

                        pie.data(data);
                        anyChartView.setChart(pie);

                        pinjaman.setText("Rp. " + currencyFormat("" + response.body().getKuranganPinjaman()));
                        saldoSimpanan.setText("Rp. " + currencyFormat("" + response.body().getSimpanan()));
                        saldoPlafon.setText("Rp. " + currencyFormat("" + response.body().getSisaPlafon()));
                        //Log.d(TAG, "onResponse: "+response.body().getData().getDetail().getPinjaman());
                    }
                }else{
                    Toast.makeText(HomeActivity.this, "Login Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String currencyFormat(String amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,##0");
        return formatter.format(Double.parseDouble(amount));
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
                    //plafonuse.setText("Rp. "+currencyFormat(""+total_plafonuse));

                    int num1 = response.body().getTotal_nilai_plafon();
                    int num2 = total_plafonuse;
                    int sisa = num1 - num2;
                    plafonsisa.setText("Rp. "+currencyFormat(""+sisa));

                    plafontotal.setText("Rp. "+currencyFormat(""+response.body().getTotal_nilai_plafon()));
                    //sisa.setText("Rp. "+currencyFormat(""+response.body().getData().getPlafon().getSisaPlafon().getReguler()));

                }else{
                    Toast.makeText(HomeActivity.this, "Page Plafon Gagal Dimuat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PlafonResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openInfoDialog() {
        mdialog.setContentView(R.layout.popup_info);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnOk = mdialog.findViewById(R.id.btnOk);
        TextView info = mdialog.findViewById(R.id.info);

        info.setText("Data Kosong");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                //Toast.makeText(HomeActivity.this, "OK", Toast.LENGTH_SHORT).show();
            }
        });
        mdialog.show();
    }

    public void openPinjamanDialog() {
        mdialog.setContentView(R.layout.popup_home_pinjaman);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnTutupPinjaman = mdialog.findViewById(R.id.btnTutupPinjaman);
        btnTutupPinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                //Toast.makeText(HomeActivity.this, "Saldo Simpanan Ditutup", Toast.LENGTH_SHORT).show();
            }
        });
        mdialog.show();
    }
    public void PopUpPinjaman(){
        String token = sharedPrefManager.getSpToken();
        Call<HomeResponse> homeResponseCall = ApiClient.getUserservice().dataHome("Bearer"+" "+token);
        homeResponseCall.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {

                int pinjaman = response.body().getKuranganPinjaman();
                if(pinjaman == 0){
                    openInfoDialog();
                }else{
                    openPinjamanDialog();
                }
            }
            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Untuk PopUp Simpanan
    public void openSimpananDialog() {
//        SimpananPokok = mdialog.findViewById(R.id.tvPokok);
//        SimpananWajib = mdialog.findViewById(R.id.tvWajib);
//        SimpananPensiun = mdialog.findViewById(R.id.tvPensiun);
//        SimpananSukarela = mdialog.findViewById(R.id.tvSukarela);
//
//        String token = " "+sharedPrefManager.getSpToken();
//        Call<SimpananResponse> simpananResponseCall = ApiClient.getUserservice().dataSimpanan("Bearer"+" "+token);
//        simpananResponseCall.enqueue(new Callback<SimpananResponse>() {
//            @Override
//            public void onResponse(Call<SimpananResponse> call, Response<SimpananResponse> response) {
//
//                if(response.isSuccessful()){
//                    SimpananPokok.setText("Rp. "+currencyFormat(""+));
//                    plafontotal.setText("Rp. "+currencyFormat(""+response.body().getTotal_nilai_plafon()));
//                    //sisa.setText("Rp. "+currencyFormat(""+response.body().getData().getPlafon().getSisaPlafon().getReguler()));
//
//                }else{
//                    Toast.makeText(HomeActivity.this, "Page Plafon Gagal Dimuat", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SimpananResponse> call, Throwable t) {
//                Toast.makeText(HomeActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

        mdialog.setContentView(R.layout.popup_home_simpanan);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnTutupSimpanan = mdialog.findViewById(R.id.btnTutup);
        btnTutupSimpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                //Toast.makeText(HomeActivity.this, "Saldo Simpanan Ditutup", Toast.LENGTH_SHORT).show();
            }
        });
        mdialog.show();
    }
    public void PopUpSimpanan(){
        String token = sharedPrefManager.getSpToken();
        Call<HomeResponse> homeResponseCall = ApiClient.getUserservice().dataHome("Bearer"+" "+token);
        homeResponseCall.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {

                int simpanan = response.body().getSimpanan();
                if(simpanan == 0){
                    openInfoDialog();
                }else{
                    openSimpananDialog();
                }
            }
            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Untuk Pop Up Plafon
    public void openPlafonDialog() {
        mdialog.setContentView(R.layout.popup_home_plafon);
        mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnTutup = mdialog.findViewById(R.id.btnTutupPlafon);
        plafonsisa = mdialog.findViewById(R.id.tvSisa);
        plafontotal = mdialog.findViewById(R.id.tvTotal);
        btnTutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog.dismiss();
                //Toast.makeText(HomeActivity.this, "Saldo Plafon Ditutup", Toast.LENGTH_SHORT).show();
            }
        });
        dataPlafon();
        mdialog.show();
    }
    public void PopUpPlafon(){
        String token = sharedPrefManager.getSpToken();
        Call<HomeResponse> homeResponseCall = ApiClient.getUserservice().dataHome("Bearer"+" "+token);
        homeResponseCall.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {

                int plafon = response.body().getSisaPlafon();
                if(plafon == 0){
                    openInfoDialog();
                }else{
                    openPlafonDialog();
                }
            }
            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //Untuk Popup Home
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
                startActivity(new Intent(HomeActivity.this, PinjamanUangActivity.class));
            }
        });
        wishlist_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WishlistActivity.class));
            }
        });
        simpanan_karyawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SimpananKaryawanActivity.class));
            }
        });
        pengajuan_pinjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PengajuanPinjamanActivity.class));
            }
        });
        penarikan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PenarikanSimpananActivity.class));
            }
        });
        perubahan_simpanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PerubahanSimpananActivity.class));
            }
        });
        mdialog.show();
    }
}