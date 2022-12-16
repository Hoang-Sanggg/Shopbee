package com.duan1.shopbee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.CancelPD_Apdater;
import com.duan1.shopbee.adapter.Order_Customer_Adapter;
import com.duan1.shopbee.adapter.StatusPD_Customer_Apdater;
import com.duan1.shopbee.model.Order;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cancel_Product_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cancel_Product_Fragment extends Fragment {

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
    CancelPD_Apdater statusPDApdater;

    private List<Order> orderList;
    private RecyclerView orderRecycer;
    private Order_Customer_Adapter orderAdapter;

    public Cancel_Product_Fragment() {
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
    public static Cancel_Product_Fragment newInstance(String param1, String param2) {
        Cancel_Product_Fragment fragment = new Cancel_Product_Fragment();
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

        statusPDApdater = new CancelPD_Apdater(getActivity());
        viewPager2.setAdapter(statusPDApdater);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Cần Phản Hồi") ;
                        break;
                    case 1:
                        tab.setText("Đã Hủy") ;
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