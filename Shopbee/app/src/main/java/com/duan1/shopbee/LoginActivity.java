package com.duan1.shopbee;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //Google
    private GoogleSignInClient gsc;
    private GoogleSignInAccount account;
    private TextInputLayout edt_email, edt_password;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CheckBox chk_remember_login;
    private String idUser, usernameIntent;
    private TextView tvLogin, tvRegister;
    private Button btnLogin;

    public static String USERNAME = "";
    public static String _id ;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.black));

        edt_email = (TextInputLayout)findViewById(R.id.TextInputLayOutUsernameLogin);
        edt_password = (TextInputLayout)findViewById(R.id.TextInputLayOutPasswordLogin);
        chk_remember_login = findViewById(R.id.chk_remember_login);
        btnLogin = findViewById(R.id.btnLogin);


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

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, LoginNumberPhoneActivity.class);
            startActivity(intent);
            }
        });

        //Đăng nhập

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogin();
            }
        });

        tvRegister = findViewById(R.id.tvRegister);
        //Đăng ký
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onClickLogin() {
        String username = String.valueOf(edt_email.getEditText().getText());
        String password = String.valueOf(edt_password.getEditText().getText());

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
                                    USERNAME = usernameIntent;

                                    SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("username", username);
                                    editor.commit();

                                    mFunction function = new mFunction();
                                    mFunction.data(usernameIntent, LoginActivity.this);

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
