package com.duan1.shopbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.duan1.shopbee.databinding.ActivityLoginNumberPhoneBinding;
import com.duan1.shopbee.databinding.ActivityVerifyOtpBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginNumberPhoneActivity extends AppCompatActivity {

    private ActivityLoginNumberPhoneBinding binding;
    private FirebaseAuth mAuth;
    ImageView iv_black_login_number_phone, right_icon_help;
    Button btnLoginOTP;
    TextView txtDangNhapBangMatKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_number_phone);

        binding = ActivityLoginNumberPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText edtPhone = findViewById(R.id.edtPhone);
        btnLoginOTP = findViewById(R.id.btnLoginOTP);


        binding.btnLoginOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginNumberPhoneActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                } else if(binding.edtPhone.getText().toString().trim().length() > 10 ||
                        binding.edtPhone.getText().toString().trim().length() < 9){
                    Toast.makeText(LoginNumberPhoneActivity.this, "Nhập số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.progressBar.setVisibility(View.GONE);
                binding.btnLoginOTP.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+84" + binding.edtPhone.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        LoginNumberPhoneActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btnLoginOTP.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btnLoginOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginNumberPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btnLoginOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginNumberPhoneActivity.this, "Đã gửi mã OTP", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                                intent.putExtra("mobile", edtPhone.getText().toString());
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
                Intent intent = new Intent(LoginNumberPhoneActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        iv_black_login_number_phone = findViewById(R.id.iv_black_login_number_phone);
        iv_black_login_number_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginNumberPhoneActivity.this, LoginActivity.class));
            }
        });
    }
}