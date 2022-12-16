package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.AddMyProductAdapter;
import com.duan1.shopbee.adapter.NotifiAdapter;
import com.duan1.shopbee.adapter.OrderAdapter;
import com.duan1.shopbee.adapter.StatusPDApdater;
import com.duan1.shopbee.callback.ClickToOrder;
import com.duan1.shopbee.callback.SelectTab;
import com.duan1.shopbee.model.Order;
import com.duan1.shopbee.model.ProductCreate;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    TextView btnBack_trans;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    StatusPDApdater statusPDApdater;

    private List<Order> orderList;
    private RecyclerView orderRecycer;
    private OrderAdapter orderAdapter;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);


        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.view_pager);

        statusPDApdater = new StatusPDApdater(getActivity());
        viewPager2.setAdapter(statusPDApdater);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Chờ xác nhận") ;
                        break;
                    case 1:
                        tab.setText("Chờ lấy hàng") ;
                        break;
                    case 2:
                        tab.setText("Đang giao") ;
                        break;
                    case 3:
                        tab.setText("Đã giao") ;
                        break;
                    case 4:
                        tab.setText("Đơn hủy") ;
                        break;
                }
            }
        }).attach();


        btnBack_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });



    }

    public void initViews(View view){
        btnBack_trans = view.findViewById(R.id.btnBack_trans);
    }
}