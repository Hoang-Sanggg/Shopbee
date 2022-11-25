package com.duan1.shopbee;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //Google
    private GoogleSignInClient gsc;
    private GoogleSignInAccount account;
    private TextInputEditText edt_email, edt_password;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CheckBox chk_remember_login;
    private String idUser, usernameIntent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        chk_remember_login = findViewById(R.id.chk_remember_login);

        //Đăng nhập google
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(LoginActivity.this, gso);
        //Kiểm tra login Google
        account = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
        if (account != null) {
            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        }

//        ImageButton imbLogin = findViewById(R.id.imb_login);
//        imbLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent googleIntent = gsc.getSignInIntent();
//                googleLauncher.launch(googleIntent);
//            }
//        });

    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onClickLogin(View view) {
        String username = edt_email.getText().toString();
        String password = edt_password.getText().toString();

        if (username.length() == 0 || password.length() == 0) {
            new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Notification")
                    .setMessage("Please enter full information\n- Username\n- Password")
                    .setIcon(R.drawable.attention_warning_14525)
                    .setPositiveButton("OK", null)
                    .show();
        } else {
            ArrayList<User> list = new ArrayList<>();
            db.collection("users")
                    .whereEqualTo("username", username)
                    .whereEqualTo("password", password)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Map<String, Object> map = document.getData();
                                    String username = map.get("username").toString();
                                    String password = map.get("password").toString();
                                    User user = new User(username, password);
                                    user.setId(document.getId());
                                    idUser = document.getId();
                                    usernameIntent = map.get("username").toString();
                                    list.add(user);
                                }
                                if (list.size() == 0) {
                                    new AlertDialog.Builder(LoginActivity.this)
                                            .setTitle("Notification")
                                            .setMessage("Wrong account information")
                                            .setIcon(R.drawable.attention_warning_14525)
                                            .setPositiveButton("OK", null)
                                            .show();
                                } else {
                                    if (chk_remember_login.isChecked()) {
                                        writeLogin(list.get(0));
                                    }
                                    idUser = list.get(0).getId();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                    intent.putExtra("idUser", idUser);
                                    intent.putExtra("username", usernameIntent);


                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    });
        }
    }

    ActivityResultLauncher<Intent> googleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String email = account.getEmail();
                        Log.d(">>>TAG", "onActivityResult: " + email);
                        //Chuyển qua màn hình MainActivity
                        if (account != null) {
                            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                        }
                    } catch (Exception e) {
                        Log.d(">>>TAG", "onActivityResult error: " + e.getMessage());
                    }
                }
            }
    );

    private void writeLogin(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("idUser", user.getId());
        editor.putString("username", user.getUsername());
        editor.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void readLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        readLogin();
    }
}
