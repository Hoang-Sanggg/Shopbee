package com.duan1.shopbee.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.R;

public class CartFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView rcvCart;
    private TextView txtTotal, txtMoney, txtTax, txtDelivery;
    private double tax;
    private ScrollView scrollView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart, container, false);
        txtDelivery = view.findViewById(R.id.txtDelivery);
        txtMoney = view.findViewById(R.id.txtMoney);
        txtTax = view.findViewById(R.id.txtTax);
        txtTotal = view.findViewById(R.id.txtTotal);
        rcvCart = view.findViewById(R.id.rcvCart);
        return view;
    }
    private Context context;

    public CartFragment(Context context) {
        this.context = context;
    }
}
