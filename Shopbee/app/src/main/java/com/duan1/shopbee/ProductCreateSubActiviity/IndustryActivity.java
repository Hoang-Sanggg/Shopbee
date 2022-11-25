package com.duan1.shopbee.ProductCreateSubActiviity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.duan1.shopbee.ProductCreateActivity;
import com.duan1.shopbee.R;


import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class IndustryActivity extends AppCompatActivity {
    ListView lvIndustry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry);
        ArrayList<String> list = new ArrayList<>();
        list.add("Thời trang nữ");
        list.add("Thời trang nam");
        list.add("Trang sức");
        list.add("Phụ kiện");
        list.add("Giày dép");
        lvIndustry = findViewById(R.id.lvIndustry);
        lvIndustry.setClickable(true);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        lvIndustry.setAdapter(adapter);
        lvIndustry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectdFromList = lvIndustry.getItemAtPosition(position).toString();
                Intent intent = new Intent(IndustryActivity.this, ProductCreateActivity.class);
                intent.putExtra("industry",selectdFromList);
                startActivity(intent);
                finish();
            }
        });

    }
}