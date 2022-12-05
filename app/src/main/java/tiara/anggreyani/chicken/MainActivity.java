package tiara.anggreyani.chicken;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tiara.anggreyani.chicken.Model.Login.LoginResponse;
import tiara.anggreyani.chicken.Model.Login.SendLogin;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    ImageView loginButton;
    SharedPrefManager sharedPrefManager;
    TextView lupaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox unsee = findViewById(R.id.unsee);

        username = findViewById(R.id.username);
        password = findViewById(R.id.ed_password);
        loginButton = findViewById(R.id.loginbutton);
        lupaButton = findViewById(R.id.forgot_pswd);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(MainActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        // inisialisasi view
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        unsee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        lupaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LupaPasswordActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(MainActivity.this, "username or Password belum diisi", Toast.LENGTH_LONG).show();
                }else{
                    login();
                }
            }
        });
    }

    public void login(){
        SendLogin sendLogin = new SendLogin(""+username.getText().toString(), ""+password.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserservice().userLogin(sendLogin);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(MainActivity.this, "Login Sukses", Toast.LENGTH_LONG).show();
                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_TOKEN, ""+response.body().getAccessToken());
                    //Toast.makeText(MainActivity.this, "Token "+loginResponse.getAccessToken(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent( MainActivity.this, HomeActivity.class).putExtra("data", ""+loginResponse.getAccessToken()));
                }else{
                    Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}