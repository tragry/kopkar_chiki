package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SimpananKaryawanActivity extends AppCompatActivity {

    FloatingActionButton popupmenu;
    Dialog mdialog;

    String simpanan[], id[], jmlpersetor[], totalsimpanan[], totalpenarikan[], totalsaldo[];
    int hitung[] = {1,2,3,4};
    RecyclerView rvSimpanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpanan_karyawan);

        ImageView HomeLogo = findViewById(R.id.home);
        ImageView PlafonLogo = findViewById(R.id.plafon);
        ImageView AkunLogo = findViewById(R.id.akun);
        ImageView back = findViewById(R.id.back);

        simpanan = getResources().getStringArray(R.array.simpanan);
        id = getResources().getStringArray(R.array.id);
        jmlpersetor = getResources().getStringArray(R.array.jml_persetor);
        totalsimpanan = getResources().getStringArray(R.array.total_simpanan);
        totalpenarikan = getResources().getStringArray(R.array.total_penarikan);
        totalsaldo = getResources().getStringArray(R.array.total_saldo);

        popupmenu = findViewById(R.id.menu);
        mdialog = new Dialog(this);

        rvSimpanan = findViewById(R.id.list_simpanan);

        SimpananAdapter simpananAdapter = new SimpananAdapter(this, simpanan, id, jmlpersetor, totalsimpanan, totalpenarikan, totalsaldo, hitung);
        rvSimpanan.setAdapter(simpananAdapter);
        rvSimpanan.setLayoutManager(new LinearLayoutManager(this));

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SimpananKaryawanActivity.super.onBackPressed();
//            }
//        });
    }
}