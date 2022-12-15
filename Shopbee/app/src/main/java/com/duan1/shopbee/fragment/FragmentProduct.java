package com.duan1.shopbee.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.model.ProductCreate;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class FragmentProduct extends Fragment implements ClickToProductSale {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");


    private TextView  tvDecription, tvIndustry, tvNameProduct, tvPriceProduct, tvbrandProduct,tvOrigin ,tvProductdetail,tvWarehouse,tvTransportfee,tvStatus,
            tvNameShop, tvSoldProduct, tvBaoHanhSp, tvShippingProduct, tvPriceFlashSale, tvNumberOfMessages;
    private ImageView ivProduct;
    private LinearLayout bottom_add_product, back_product, buyNow, btnAddToCart;
    ShowBottomNav showBottomNav;
    private ConstraintLayout btnCart;
    private List<ProductCreate> productCreateList;
    private List<ProductCreate> cart;

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

    public FragmentProduct(List<ProductCreate> productCreateList) {
        this.productCreateList = productCreateList;
    }

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

    public FragmentProduct(String idProduct, String nameProduct, String description, String industry, String priceProduct, String productdetail, String warehouse, String transportfee, String status, String nameShop, String soldProduct, String brandProduct, String originProduct, String baoHanhSp, String shippingProduct, String priceFlashSale, String discountFlashSale, String soldFlashSale, String imageProduct) {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        cart = new ArrayList<>();

        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String nameShopS = sharedPref.getString("username", "");

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
        tvIndustry.setText(industry);
        tvPriceProduct.setPaintFlags(tvPriceProduct.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        readData();



        Glide.with(this)
                .load(imageProduct)
                .into(ivProduct);

        if(nameShop.equals(nameShopS)){
            bottom_add_product.setVisibility(View.GONE);
        }

        back_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyNowProduct();
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String maSp = "CRT"+RandomMaDonHang(10);

                        databaseReference.child("cart").child(nameShopS).child(maSp).child("nameProduct").setValue(nameProduct);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("description").setValue(description);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("industry").setValue(industry);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("priceProduct").setValue(priceProduct);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("productdetail").setValue("6");
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("warehouse").setValue(warehouse);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("transportfee").setValue(transportfee);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("status").setValue(status);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("nameShop").setValue(nameShopS);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("soldProduct").setValue("11");
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("brandProduct").setValue(brandProduct);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("originProduct").setValue(originProduct);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("baoHanhSp").setValue(baoHanhSp);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("shippingProduct").setValue("15");
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("priceFlashSale").setValue(priceFlashSale);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("discountFlashSale").setValue("18");
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("soldFlashSale").setValue("18");
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("imageProduct").setValue(imageProduct);
                        databaseReference.child("cart").child(nameShopS).child(maSp).child("idProduct").setValue(maSp);

//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("nameProduct").setValue(nameProduct);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("description").setValue(decription);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("industry").setValue(txtNewIndustry.getText());
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("priceProduct").setValue("5");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("productdetail").setValue("6");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("warehouse").setValue(storage);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("transportfee").setValue("8");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("status").setValue(Status);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("nameShop").setValue(name);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("soldProduct").setValue("11");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("brandProduct").setValue(txtNewBrand.getText());
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("originProduct").setValue("13");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("baoHanhSp").setValue(BaoHanh);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("shippingProduct").setValue("15");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("priceFlashSale").setValue(price);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("discountFlashSale").setValue("18");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("soldFlashSale").setValue("18");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("imageProduct").setValue(linkDL);

                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        requireActivity().getSupportFragmentManager().popBackStack();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, new CartFragment(cart), "MainFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void readData(){
        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String nameShopS = sharedPref.getString("username", "");
        databaseReference.child("cart").child(nameShopS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cart.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ProductCreate productCreate = postSnapshot.getValue(ProductCreate.class);
                    cart.add(productCreate);
                    Log.d("iiii", "onClick: "+cart.size());
                }
                tvNumberOfMessages.setText(String.valueOf(cart.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d("iiii", "onClick: "+cart.size());

    }

    @Override
    public void onClickToProductSale(List<ProductCreate> flashsaleList, int position) {

    }

    private void buyNowProduct(){
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new BuyNowFragment(idProduct,nameProduct,description,industry,priceProduct,productdetail,warehouse,transportfee,status,nameShop,soldProduct,brandProduct,originProduct,baoHanhSp,shippingProduct,priceFlashSale,discountFlashSale,soldFlashSale,imageProduct), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

    private void initViews(View view){
        tvNameProduct = view.findViewById(R.id.txtNameProduct); //1
        tvDecription = view.findViewById(R.id.txtDescription); //2
        tvPriceProduct = view.findViewById(R.id.txtPrice); //3
        tvWarehouse = view.findViewById(R.id.txtStorage); //4
        tvBaoHanhSp = view.findViewById(R.id.txtExpiredDate); //5
        tvbrandProduct = view.findViewById(R.id.txtBrandProduct); //6
        tvOrigin = view.findViewById(R.id.txtOrigin); //7
        tvStatus = view.findViewById(R.id.txtUsage); //8
        tvIndustry = view.findViewById(R.id.txtIndustryProduct);
        tvNameShop = view.findViewById(R.id.txtSeller); //9
        tvShippingProduct = view.findViewById(R.id.txtSendFrom); //10
        ivProduct = view.findViewById(R.id.ivProduct);
        tvPriceFlashSale = view.findViewById(R.id.txtPriceFlashsale);
        bottom_add_product = view.findViewById(R.id.bottom_add_product);

        back_product = view.findViewById(R.id.back_product);
        buyNow = view.findViewById(R.id.btnBuyNow);

        btnAddToCart = view.findViewById(R.id.btnAddToCart);
        tvNumberOfMessages = view.findViewById(R.id.tvNumberOfMessages);

        btnCart = view.findViewById(R.id.btnCart);

    }

    public static String RandomMaDonHang(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
