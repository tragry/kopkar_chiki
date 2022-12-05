package tiara.anggreyani.chicken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LupaPasswordActivity extends AppCompatActivity {

    Button kirim, signin;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        kirim = findViewById(R.id.kirim);
        signin = findViewById(R.id.signin);
        email = findViewById(R.id.email_ed);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LupaPasswordActivity.this, MainActivity.class));
            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String email = getText().toString();
            }
        });
    }
}