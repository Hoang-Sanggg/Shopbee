package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duan1.shopbee.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    private String nameProduct;
    private String description; // mo ta
    private String industry; // nganh hang
    private String priceProduct; // gia
    private String productdetail; // chi tiet san pham
    private String warehouse; // ton kho
    private String transportfee; // phi van chuyen
    private String status; // trang thai
    private String nameShop;
    private String soldProduct; // da ban
    private String brandProduct;
    private String originProduct; // xuat xu
    private String baoHanhSp;
    private String shippingProduct;
    private String imageProduct;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    public ProductFragment(String nameProduct, String description, String industry, String priceProduct, String productdetail, String warehouse, String transportfee, String status, String nameShop, String soldProduct, String brandProduct, String originProduct, String baoHanhSp, String shippingProduct, String imageProduct, String mParam1, String mParam2) {
        this.nameProduct = nameProduct;
        this.description = description;
        this.industry = industry;
        this.priceProduct = priceProduct;
        this.productdetail = productdetail;
        this.warehouse = warehouse;
        this.transportfee = transportfee;
        this.status = status;
        this.nameShop = nameShop;
        this.soldProduct = soldProduct;
        this.brandProduct = brandProduct;
        this.originProduct = originProduct;
        this.baoHanhSp = baoHanhSp;
        this.shippingProduct = shippingProduct;
        this.imageProduct = imageProduct;
        this.mParam1 = mParam1;
        this.mParam2 = mParam2;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_product, container, false);
    }
}