package com.duan1.shopbee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterNumberPhoneActivity extends AppCompatActivity {

    ImageView iv_black_login_number_phone, right_icon_help;
    Button btnGetOTP;
    TextView txtDangNhapBangMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number_phone);

        final EditText edtMobile = findViewById(R.id.edtTextPhone_Register);
        btnGetOTP = findViewById(R.id.btnNext_Register);

        final ProgressBar  progressBar = findViewById(R.id.progressBar);

        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterNumberPhoneActivity.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                btnGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84" + edtMobile.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        RegisterNumberPhoneActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                progressBar.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                btnGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterNumberPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                btnGetOTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                                intent.putExtra("mobile", edtMobile.getText().toString());
                                intent.putExtra("verificationId", verification);
                                startActivity(intent);
                            }
                        }
                );

            }
        });

        txtDangNhapBangMatKhau = findViewById(R.id.txtDangNhapBangMatKhau);

        txtDangNhapBangMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterNumberPhoneActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        iv_black_login_number_phone = findViewById(R.id.iv_black_login_number_phone);
        iv_black_login_number_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterNumberPhoneActivity.this, LoginActivity.class));
            }
        });
    }
}