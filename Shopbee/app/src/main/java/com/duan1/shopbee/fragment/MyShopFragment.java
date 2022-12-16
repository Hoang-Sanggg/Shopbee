package com.duan1.shopbee.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.OrderAdapter;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.model.Order;
import com.duan1.shopbee.model.ProductCreate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyShopFragment extends Fragment{

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");

    private List<Order> orderList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<ProductCreate> productCreateList;
    ShowBottomNav showBottomNav;
    TextView textView;

    public MyShopFragment(List<ProductCreate> productCreateList, ShowBottomNav showBottomNav) {
        this.productCreateList = productCreateList;
        this.showBottomNav = showBottomNav;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView btn_back_myProduct = view.findViewById(R.id.btn_back_myShop);
        LinearLayout lnMyProduct = view.findViewById(R.id.lnMyProduct);
        LinearLayout lnOrder = view.findViewById(R.id.btnDonHang);
        LinearLayout myOrder = view.findViewById(R.id.myOrder);
        textView = view.findViewById(R.id.box1);



        btn_back_myProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
                showBottomNav.showBottomNav();
            }
        });

        lnMyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMyProduct(view);
            }
        });

        lnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMyOrder(view);
            }
        });

        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMyyOrder(view);
            }
        });

//        Toast.makeText(getContext(), productCreateList.size(), Toast.LENGTH_SHORT).show();


        readData();
    }

    public void onClickMyProduct(View view) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new MyProductFragment(productCreateList), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

    public void onClickMyOrder(View view) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, new OrderFragment(), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

    public void onClickMyyOrder(View view) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new Cancel_Product_Fragment(), "MainFragment")
                .addToBackStack(null)
                .commit();
    }



    private void readData(){

        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String nameShopS = sharedPref.getString("username", "");

        orderList = new ArrayList<>();
        databaseReference.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);
                    if(String.valueOf(order.getSeller()).equals(String.valueOf(nameShopS))){
                        orderList.add(order);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        textView.setText(String.valueOf(orderList.size()));
    }

}