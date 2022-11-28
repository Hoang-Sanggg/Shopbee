package com.duan1.shopbee.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ShowBottomNav;

import org.checkerframework.checker.index.qual.PolyUpperBound;

public class FragmentProduct extends Fragment {

    private TextView  tvDecription, tvIndustry, tvNameProduct, tvPriceProduct, tvbrandProduct,tvOrigin ,tvProductdetail,tvWarehouse,tvTransportfee,tvStatus,
    tvNameShop, tvSoldProduct, tvBaoHanhSp, tvShippingProduct, tvPriceFlashSale;
    private ImageView ivProduct;

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

    public FragmentProduct(String idProduct, String nameProduct, String description, String industry, String priceProduct, String productdetail, String warehouse, String transportfee, String status, String nameShop, String soldProduct, String brandProduct, String originProduct, String baoHanhSp, String shippingProduct, String priceFlashSale, String discountFlashSale, String soldFlashSale, String imageProduct, ShowBottomNav showBottomNav) {
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
        this.showBottomNav = showBottomNav;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        tvNameProduct.setText(nameProduct); //1t
        tvDecription.setText(description); //t
        tvWarehouse.setText(warehouse); //3t
        tvbrandProduct.setText(brandProduct); //4
        tvPriceProduct.setText(priceProduct); //5
        tvOrigin.setText(originProduct); //6
        tvStatus.setText(status); //7
        tvBaoHanhSp.setText(baoHanhSp); // 8
        tvShippingProduct.setText(shippingProduct); // 9
        tvNameShop.setText(nameShop); //10
        tvPriceFlashSale.setText(priceFlashSale);
        tvPriceProduct.setPaintFlags(tvPriceProduct.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        Glide.with(this)
                .load(imageProduct)
                .into(ivProduct);
    }

    private void initViews(View view){
        tvNameProduct = view.findViewById(R.id.txtName); //1
        tvDecription = view.findViewById(R.id.txtDescription); //2
        tvPriceProduct = view.findViewById(R.id.txtPrice); //3
        tvWarehouse = view.findViewById(R.id.txtStorage); //4
        tvBaoHanhSp = view.findViewById(R.id.txtExpiredDate); //5
        tvbrandProduct = view.findViewById(R.id.txtBrandProduct); //6
        tvOrigin = view.findViewById(R.id.txtOrigin); //7
        tvStatus = view.findViewById(R.id.txtUsage); //8
        tvNameShop = view.findViewById(R.id.txtSeller); //9
        tvShippingProduct = view.findViewById(R.id.txtSendFrom); //10
        ivProduct = view.findViewById(R.id.ivProduct);
        tvPriceFlashSale = view.findViewById(R.id.txtPriceFlashsale);
    }
}
