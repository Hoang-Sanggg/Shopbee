package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ShowBottomNav;

public class MyShopFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ShowBottomNav showBottomNav;


    public MyShopFragment(ShowBottomNav showBottomNav) {
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
        ImageView btn_back_myProduct = view.findViewById(R.id.btn_back_myProduct);
        LinearLayout lnMyProduct = view.findViewById(R.id.lnMyProduct);

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
    }

    public void onClickMyProduct(View view) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new MyProductFragment(), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

}