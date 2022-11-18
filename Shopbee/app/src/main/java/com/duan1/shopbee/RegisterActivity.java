package com.duan1.shopbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    ImageView left_icon_arrow, right_icon_help, ivShopee;
    TextView txtDangKy;
    TextInputLayout tilPhone;
    EditText edtTextPhone;
    Button btnNext, btnLoginGoogle, btnLoginFacebook, tvLogin;
    CheckBox chkActivated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
}