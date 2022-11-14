package com.duan1.shopbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {

    ImageView left_icon_arrow, right_icon_help;
    TextInputLayout tilPhone;
    EditText edtUser_Phone;
    Button btnNext_Reset_Password;
    TextView txtSoDienThoaiDaThayDoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

    }
}