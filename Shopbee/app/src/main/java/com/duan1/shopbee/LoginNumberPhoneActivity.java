package com.duan1.shopbee;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.duan1.shopbee.databinding.ActivityLoginNumberPhoneBinding;
import com.duan1.shopbee.databinding.ActivityVerifyOtpBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginNumberPhoneActivity extends AppCompatActivity {

    //Google
    private GoogleSignInClient gsc;
    private GoogleSignInAccount account;
    ImageView iv_black_login_number_phone, right_icon_help;
    Button btnLoginOTP;
    TextView txtDangNhapBangMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_number_phone);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));

        final EditText edtMobile = findViewById(R.id.edtMobile);
        btnGetOTP = findViewById(R.id.btnGetOTP);

        final ProgressBar progressBar = findViewById(R.id.progressBar_Login_Number_Phone);

        //Đăng nhập google
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(LoginNumberPhoneActivity.this, gso);

        //Kiểm tra login Google
        account = GoogleSignIn.getLastSignedInAccount(LoginNumberPhoneActivity.this);

        SignInButton signInButton = findViewById(R.id.btn_sign_google_login_number_phone);
        signInButton.setSize(SignInButton.SIZE_WIDE);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = gsc.getSignInIntent();
                starActivityForResult.launch(signInIntent);
            }
        });

        btnLoginOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginNumberPhoneActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                } else if(edtPhone.getText().toString().trim().length() > 10 ||
                        edtPhone.getText().toString().trim().length() < 9){
                    Toast.makeText(LoginNumberPhoneActivity.this, "Nhập số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.GONE);
                btnLoginOTP.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84" + edtPhone.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        LoginNumberPhoneActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                btnLoginOTP.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                btnLoginOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginNumberPhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                btnLoginOTP.setVisibility(View.VISIBLE);
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

    //Login Google
    ActivityResultLauncher<Intent> starActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        handleSignInResult(task);
                    }
                }
            }
    );

    //Kiểm tra Login Google
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {
                String personName = account.getDisplayName();
                String personGivenName = account.getGivenName();
                String personFamilyName = account.getFamilyName();
                String personEmail = account.getEmail();
                String personId = account.getId();
                Uri personPhoto = account.getPhotoUrl();
                Toast.makeText(this, "Email: " + personEmail, Toast.LENGTH_SHORT).show();

                Intent homeIntent = new Intent(LoginNumberPhoneActivity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("GOOGLE ERROR", e.getMessage());
        }
    }
}