package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailBarangActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    ImageView back, photo_barang;
    TextView nama_barang, stock, harga, rating, deskripsi, satuan;

    String data1, data2, data3, data4, data5, data6;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        ImageView HomeLogo = findViewById(R.id.home);
        ImageView TokoLogo = findViewById(R.id.toko);
        ImageView AkunLogo = findViewById(R.id.akun);
        Button simpan = findViewById(R.id.simpan);

        photo_barang = findViewById(R.id.photo_img);
        nama_barang = findViewById(R.id.nama_barang);
        stock = findViewById(R.id.stock);
        harga = findViewById(R.id.harga);
        rating = findViewById(R.id.rating);
        deskripsi = findViewById(R.id.deskripsi);
        satuan = findViewById(R.id.satuan);


        getData();
        setData();

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailBarangActivity.super.onBackPressed();
            }
        });

        sharedPrefManager = new SharedPrefManager(this);

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailBarangActivity.this, "Barang telah ditambahkan ke Wishlist", Toast.LENGTH_LONG).show();
            }
        });

//        HomeLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DetailBarangActivity.this, HomeActivity.class));
//            }
//        });
//        TokoLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DetailBarangActivity.this, TokoActivity.class));
//            }
//        });
//        AkunLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DetailBarangActivity.this, ProfileActivity.class));
//            }
//        });
    }

    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") &&
        getIntent().hasExtra("data2") && getIntent().hasExtra("data3")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            data5 = getIntent().getStringExtra("data5");
            data6 = getIntent().getStringExtra("data6");
            myImage = getIntent().getIntExtra("myImage",1);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        nama_barang.setText(data1);
        harga.setText(data2);
        stock.setText(data3);
        rating.setText(data4);
        deskripsi.setText(data5);
        satuan.setText(data6);
        photo_barang.setImageResource(myImage);
    }
}