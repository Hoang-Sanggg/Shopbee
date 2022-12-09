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

import com.duan1.shopbee.databinding.ActivityLoginNumberPhoneBinding;
import com.duan1.shopbee.databinding.ActivityRegisterNumberPhoneBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterNumberPhoneActivity extends AppCompatActivity {

    private ActivityRegisterNumberPhoneBinding binding;
    private FirebaseAuth mAuth;
    ImageView iv_black_register;
    Button btnNextRegister;
    TextView txtLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_number_phone);

        binding = ActivityRegisterNumberPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText edtPhone = findViewById(R.id.edtPhone_Register);
        btnNextRegister = findViewById(R.id.btnNext_Register);

        binding.btnNextRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtPhoneRegister.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterNumberPhoneActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                } else if(binding.edtPhoneRegister.getText().toString().trim().length() > 10 ||
                        binding.edtPhoneRegister.getText().toString().trim().length() < 9) {
                    Toast.makeText(RegisterNumberPhoneActivity.this, "Nhập số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.progressBar.setVisibility(View.GONE);
                binding.btnNextRegister.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84" + edtPhone.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        RegisterNumberPhoneActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btnNextRegister.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btnNextRegister.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterNumberPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.btnNextRegister.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterNumberPhoneActivity.this, "Đã gửi mã OTP", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                                intent.putExtra("mobile", edtPhone.getText().toString());
                                intent.putExtra("verificationId", verification);
                                startActivity(intent);
                            }
                        }
                );

            }
        });

        iv_black_register = findViewById(R.id.iv_black_register);
        iv_black_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterNumberPhoneActivity.this, RegisterActivity.class));
            }
        });

        txtLogin = findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterNumberPhoneActivity.this, LoginActivity.class));
            }
        });

    }
}