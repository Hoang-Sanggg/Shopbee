package com.duan1.shopbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    EditText edtname, edtdescription, edtindustry,edtproductdetail,edtwarehouse,edtstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        edtstatus = findViewById(R.id.edtStatus);
        edtwarehouse = findViewById(R.id.edtWareHouse);
        edtproductdetail = findViewById(R.id.edtProductDetail);
        edtname = findViewById(R.id.edtProductName);
        edtdescription = findViewById(R.id.edtDescription);
        edtindustry = findViewById(R.id.edtIndustry);





    }

    public void addFireStoreProduct(){
        String warehouse = edtwarehouse.getText().toString();
        String status = edtstatus.getText().toString();
        String productdetail = edtproductdetail.getText().toString();
        String industry = edtindustry.getText().toString();
        String description = edtdescription.getText().toString();;
        String name = edtname.getText().toString();

        Map<String, Object> P02 = new HashMap<>();
        P02.put("id","hd002");

    }
}