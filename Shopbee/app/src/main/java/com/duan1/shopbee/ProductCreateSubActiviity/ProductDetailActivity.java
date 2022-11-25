package com.duan1.shopbee.ProductCreateSubActiviity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.duan1.shopbee.ProductCreateActivity;
import com.duan1.shopbee.R;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {
    ListView lvProductDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ArrayList<String> list = new ArrayList<>();
        list.add("Size S");
        list.add("Size M");
        list.add("Size L");
        list.add("Size XL");
        list.add("Size XXL");
        lvProductDetail = findViewById(R.id.lvProductDetail);
        lvProductDetail.setClickable(true);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
        lvProductDetail.setAdapter(adapter);
        lvProductDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectdFromList = lvProductDetail.getItemAtPosition(position).toString();
                Intent intent = new Intent(ProductDetailActivity.this, ProductCreateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("detail",selectdFromList);
                intent.putExtras(bundle);
                finish();
                startActivity(intent);

            }
        });
    }
}