package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.CartAdapter;
import com.duan1.shopbee.adapter.OrderAdapter;
import com.duan1.shopbee.callback.ClickToBuy;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements ClickToProductSale, ClickToBuy {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ProductCreate> cart;

    private CartAdapter adapter;

    private RecyclerView recyclerView;

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


    public CartFragment() {
        // Required empty public constructor
    }

    public CartFragment(List<ProductCreate> cart) {
        this.cart = cart;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView imageView = view.findViewById(R.id.btnBack_Cart);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });



       adapter = new CartAdapter(cart, getContext(), CartFragment.this, CartFragment.this);
       Toast.makeText(getContext(), String.valueOf(cart.size()), Toast.LENGTH_SHORT).show();

       data(view);


    }

    private void data(View view){
        recyclerView = view.findViewById(R.id.rcvCart);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickToProductSale(List<ProductCreate> flashsaleList, int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new FragmentProduct(flashsaleList.get(position).getIdProduct(),flashsaleList.get(position).getNameProduct(), flashsaleList.get(position).getDescription(), flashsaleList.get(position).getIndustry(), flashsaleList.get(position).getPriceProduct(), flashsaleList.get(position).getProductdetail(), flashsaleList.get(position).getWarehouse(),flashsaleList.get(position).getTransportfee(), flashsaleList.get(position).getStatus(), flashsaleList.get(position).getNameShop(), flashsaleList.get(position).getSoldProduct(), flashsaleList.get(position).getBrandProduct(), flashsaleList.get(position).getOriginProduct(), flashsaleList.get(position).getBaoHanhSp(), flashsaleList.get(position).getShippingProduct(), flashsaleList.get(position).getPriceFlashSale(), flashsaleList.get(position).getDiscountFlashSale(), flashsaleList.get(position).getSoldFlashSale(), flashsaleList.get(position).getImageProduct()), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void ClickToBuy(List<ProductCreate> cart, int position) {
        buyNowProduct(cart, position);
    }

    private void buyNowProduct(List<ProductCreate> flashsaleList, int position){
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new BuyNowFragment(flashsaleList.get(position).getIdProduct(),flashsaleList.get(position).getNameProduct(), flashsaleList.get(position).getDescription(), flashsaleList.get(position).getIndustry(), flashsaleList.get(position).getPriceProduct(), flashsaleList.get(position).getProductdetail(), flashsaleList.get(position).getWarehouse(),flashsaleList.get(position).getTransportfee(), flashsaleList.get(position).getStatus(), flashsaleList.get(position).getNameShop(), flashsaleList.get(position).getSoldProduct(), flashsaleList.get(position).getBrandProduct(), flashsaleList.get(position).getOriginProduct(), flashsaleList.get(position).getBaoHanhSp(), flashsaleList.get(position).getShippingProduct(), flashsaleList.get(position).getPriceFlashSale(), flashsaleList.get(position).getDiscountFlashSale(), flashsaleList.get(position).getSoldFlashSale(), flashsaleList.get(position).getImageProduct()), "MainFragment")
                .addToBackStack(null)
                .commit();
    }
}