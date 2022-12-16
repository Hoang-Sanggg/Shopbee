package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String idProductOrder;
    private String customer;
    private String seller;
    private String priceOrder;
    private String priceProductOrder;
    private String numberof;
    private String nameProductOrder;
    private String statusOrder;
    private String dateOrder;
    private String imageOrder;
    private String address;
    private String phone;

    TextView tvAddressName, tvAddressPhoneNumber, tvAddressAddress, tvProductName_pending, tvnameShop, tvCode_detail, tvPaymentDetail;
            ImageView ivAvtProduct_pending;
            Button btnreadyProduct;
    Button btnCancel;

    public DetailFragment() {
        // Required empty public constructor
    }

    public DetailFragment(String idProductOrder, String customer, String seller, String priceOrder, String priceProductOrder, String numberof, String nameProductOrder, String statusOrder, String dateOrder, String imageOrder, String address, String phone) {
        this.idProductOrder = idProductOrder;
        this.customer = customer;
        this.seller = seller;
        this.priceOrder = priceOrder;
        this.priceProductOrder = priceProductOrder;
        this.numberof = numberof;
        this.nameProductOrder = nameProductOrder;
        this.statusOrder = statusOrder;
        this.dateOrder = dateOrder;
        this.imageOrder = imageOrder;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), idProductOrder, Toast.LENGTH_SHORT).show();

        initViews(view);

        tvAddressAddress.setText(address);
        tvAddressPhoneNumber.setText(phone);
        tvAddressName.setText(customer);
        Glide.with(getContext())
                .load(imageOrder)
                .into(ivAvtProduct_pending);
        tvnameShop.setText(seller);
        tvCode_detail.setText(idProductOrder);
        tvPaymentDetail.setText(priceProductOrder);

        btnreadyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseReference.child("order").child(idProductOrder).child("cancelCustomer").setValue("1");
                        requireActivity().getSupportFragmentManager().popBackStack();
                        requireActivity().getSupportFragmentManager().popBackStack();
                        requireActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_layout, new Order_Customer_Fragment(), "MainFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }

            private void initViews(View view) {
                tvAddressName = view.findViewById(R.id.tvAddressName);
                tvAddressPhoneNumber = view.findViewById(R.id.tvAddressPhoneNumber);
                tvAddressAddress = view.findViewById(R.id.tvAddressAddress);
                ivAvtProduct_pending = view.findViewById(R.id.ivAvtProduct_pending);
                tvProductName_pending = view.findViewById(R.id.tvProductName_pending);
                tvnameShop = view.findViewById(R.id.tvnameShop);
                tvCode_detail = view.findViewById(R.id.tvCode_detail);
                tvPaymentDetail = view.findViewById(R.id.tvPaymentDetail);
                btnreadyProduct = view.findViewById(R.id.btnPrepare_detail);

                btnCancel = view.findViewById(R.id.btnCancel);

            }
        }
}