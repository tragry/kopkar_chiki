package tiara.anggreyani.chicken;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UbahPasswordActivity extends AppCompatActivity {

    boolean passwordVisible;
    ImageView btnKirim, back;
    EditText edtPassLama, edtPassBaru, edtPassKonfrm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        back = findViewById(R.id.back);
        edtPassBaru = findViewById(R.id.pass_baru);
        edtPassLama = findViewById(R.id.pass_lama);
        edtPassKonfrm = findViewById(R.id.pass_konfrm);

        edtPassBaru.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edtPassBaru.getRight()-edtPassBaru.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = edtPassBaru.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            edtPassBaru.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_unsee,0);
                            //for Hide password
                            edtPassBaru.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            //set drawable image here
                            edtPassBaru.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_see,0);
                            //for Show password
                            edtPassBaru.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        edtPassBaru.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        edtPassLama.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edtPassLama.getRight()-edtPassLama.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = edtPassLama.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            edtPassLama.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_unsee,0);
                            //for Hide password
                            edtPassLama.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            //set drawable image here
                            edtPassLama.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_see,0);
                            //for Show password
                            edtPassLama.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        edtPassLama.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        edtPassKonfrm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edtPassKonfrm.getRight()-edtPassKonfrm.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = edtPassKonfrm.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            edtPassKonfrm.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_unsee,0);
                            //for Hide password
                            edtPassKonfrm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            //set drawable image here
                            edtPassKonfrm.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_see,0);
                            //for Show password
                            edtPassKonfrm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        edtPassKonfrm.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UbahPasswordActivity.super.onBackPressed();
            }
        });
    }
}