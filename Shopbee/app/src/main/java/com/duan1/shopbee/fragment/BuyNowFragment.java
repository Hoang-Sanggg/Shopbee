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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ShowBottomNav;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyNowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyNowFragment extends Fragment {
    private TextView tvDecription, tvIndustry, tvNameProduct, tvPriceProduct, tvbrandProduct,tvOrigin ,tvProductdetail,tvWarehouse,tvTransportfee,tvStatus,
            tvNameShop, tvSoldProduct, tvBaoHanhSp, tvShippingProduct, tvPriceFlashSale, tvnameShop,namePd, pricePd, tvPriceAndShip;
    private ImageView ivProduct, buynow_ImgItem;
    private LinearLayout bottom_add_product, back_product, buyNow;
    ShowBottomNav showBottomNav;

    private String idProduct;
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
    private String priceFlashSale;
    private String discountFlashSale;
    private String soldFlashSale;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuyNowFragment() {
        // Required empty public constructor
    }

    public BuyNowFragment(String idProduct, String nameProduct, String description, String industry, String priceProduct, String productdetail, String warehouse, String transportfee, String status, String nameShop, String soldProduct, String brandProduct, String originProduct, String baoHanhSp, String shippingProduct, String priceFlashSale, String discountFlashSale, String soldFlashSale, String imageProduct) {
        this.idProduct = idProduct;
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
        this.priceFlashSale = priceFlashSale;
        this.discountFlashSale = discountFlashSale;
        this.soldFlashSale = soldFlashSale;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyNowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyNowFragment newInstance(String param1, String param2) {
        BuyNowFragment fragment = new BuyNowFragment();
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
        return inflater.inflate(R.layout.fragment_buy_now, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        tvnameShop.setText(nameShop);
        namePd.setText(nameProduct);
        pricePd.setText(priceProduct);
        tvPriceAndShip.setText(String.valueOf(Integer.parseInt(priceProduct)+Integer.parseInt(transportfee)));
        Glide.with(this)
                .load(imageProduct)
                .into(buynow_ImgItem);
    }

    private void initViews(View view){
        tvnameShop = view.findViewById(R.id.buynow_tvNameShop);
        namePd = view.findViewById(R.id.buynow_tvNameItem);
        pricePd = view.findViewById(R.id.buynow_tvPriceItem);
        tvPriceAndShip = view.findViewById(R.id.tvPriceAndShip);
        buynow_ImgItem = view.findViewById(R.id.buynow_ImgItem);
    }
}