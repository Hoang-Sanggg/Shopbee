package com.duan1.shopbee;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        Cart cart = (Cart) bundle.get("object_cart");
        TextView tvNameProduct = findViewById(R.id.txtNameProduct);
        tvNameProduct.setText(cart.getName());

        ImageView ivProduct = findViewById(R.id.ivProduct);
        ivProduct.setImageResource(cart.getImage());

    }


}