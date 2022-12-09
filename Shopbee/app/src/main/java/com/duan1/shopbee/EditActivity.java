package com.duan1.shopbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class EditActivity extends AppCompatActivity {

    EditText uName, uDescription, uPrice, uStorage;
    TextView uIndustry, uBrand, uBaoHanh, uShip, uStatus;
    CountryCodePicker uCountry;
    Button uOrder,uSubmit;
    String cName, cDescription, cPrice, cStorage, cIndustry, cBrand, cBaoHanh, cShip, cStatus, cCountry, cOrder;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit);

        reference = FirebaseDatabase.getInstance().getReference("product");

         uName= findViewById(R.id.edtEditNameProduct);
         uDescription= findViewById(R.id.edtEditDescription);
         uIndustry= findViewById(R.id.txtEditIndustry);
         uBrand= findViewById(R.id.txtEditBrand);
         uCountry = findViewById(R.id.Editcountry);
         uBaoHanh = findViewById(R.id.txtEditBaoHanh);
         uPrice = findViewById(R.id.edtEditPrice);
         uStorage = findViewById(R.id.edtEditStorage);
         uShip = findViewById(R.id.txtEditShip);
         uStatus = findViewById(R.id.txtEditStatus);
         uOrder = findViewById(R.id.btnEditOrder);
         uSubmit = findViewById(R.id.btnSubmit);

         showData();

        uSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isDescriptionChanged() || isIndustryChanged() || isBrandChanged() || isBaoHanhChanged() || isPriceChanged() || isStorageChanged() || isShipChanged() || isStatusChanged()){
                    Toast.makeText(EditActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "Không có thay đổi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isNameChanged() {
        if (!cName.equals(uName.getText().toString())){
            reference.child("name").setValue(uName.getText().toString());
            cName = uName.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isDescriptionChanged() {
        if (!cDescription.equals(uDescription.getText().toString())){
            reference.child("description").setValue(uDescription.getText().toString());
            cDescription = uDescription.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isIndustryChanged() {
        if (!cIndustry.equals(uIndustry.getText().toString())){
            reference.child("industry").setValue(uIndustry.getText().toString());
            cIndustry = uIndustry.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isBrandChanged() {
        if (!cBrand.equals(uBrand.getText().toString())){
            reference.child("brand").setValue(uBrand.getText().toString());
            cBrand = uBrand.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isBaoHanhChanged() {
        if (!uBaoHanh.equals(uBaoHanh.getText().toString())){
            reference.child("baohanh").setValue(uBaoHanh.getText().toString());
            cBaoHanh = uBaoHanh.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPriceChanged() {
        if (!cPrice.equals(uPrice.getText().toString())){
            reference.child("price").setValue(uPrice.getText().toString());
            cPrice = uPrice.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isStorageChanged() {
        if (!cStorage.equals(uStorage.getText().toString())){
            reference.child("warehouse").setValue(uStatus.getText().toString());
            cStorage = uStorage.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isShipChanged() {
        if (!cShip.equals(uShip.getText().toString())){
            reference.child("ship").setValue(uShip.getText().toString());
            cShip = uShip.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isStatusChanged() {
        if (!cStatus.equals(uStatus.getText().toString())){
            reference.child("status").setValue(uStatus.getText().toString());
            cStatus = uStatus.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData(){
        Intent intent = getIntent();
        cName = intent.getStringExtra("name");
        cDescription = intent.getStringExtra("description");
        cIndustry = intent.getStringExtra("industry");
        cBaoHanh = intent.getStringExtra("baohanh");
        cBrand = intent.getStringExtra("brand");
        cStatus = intent.getStringExtra("status");
        cStorage = intent.getStringExtra("storage");
        cShip = intent.getStringExtra("ship");
    }
}