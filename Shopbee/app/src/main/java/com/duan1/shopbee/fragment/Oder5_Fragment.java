package com.duan1.shopbee.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.Order5_Adapter;
import com.duan1.shopbee.adapter.Order5_Customer_Adapter;
import com.duan1.shopbee.callback.ClickNextStatus;
import com.duan1.shopbee.callback.ClickToOrder;
import com.duan1.shopbee.model.Order;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Oder5_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Oder5_Fragment extends Fragment implements ClickToOrder, ClickNextStatus {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Order> orderList;
    private RecyclerView orderRecycer;
    private Order5_Adapter orderAdapter;


    public Oder5_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Oder2_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Oder5_Fragment newInstance(String param1, String param2) {
        Oder5_Fragment fragment = new Oder5_Fragment();
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
        return inflater.inflate(R.layout.fragment_oder2_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        readData();
    }

    private void readData() {
        orderList = new ArrayList<>();
        databaseReference.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);
                    orderList.add(order);
                    data(getView());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        orderAdapter = new Order5_Adapter(orderList, getContext(), Oder5_Fragment.this, Oder5_Fragment.this);
    }

    private void data(View view) {
        orderRecycer = view.findViewById(R.id.rcvTrans2);

        orderRecycer.setHasFixedSize(true);
        orderRecycer.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        orderRecycer.setAdapter(orderAdapter);
    }

    @Override
    public void ClickToOrder(List<Order> orderList, int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new DetailFragment(orderList.get(position).getIdProductOrder(), orderList.get(position).getCustomer(), orderList.get(position).getSeller(), orderList.get(position).getPriceOrder(), orderList.get(position).getPriceProductOrder(), orderList.get(position).getNumberOfOrder(), orderList.get(position).getNameProductOrder(), orderList.get(position).getStatusOrder(), orderList.get(position).getDateOrder(), orderList.get(position).getImageOrder(), orderList.get(position).getAddress(), orderList.get(position).getPhone()), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClickNextStatus(List<Order> list, int position, int id) {
        databaseReference.child("order").child(orderList.get(position).getIdProductOrder()).child("statusOrder").setValue(String.valueOf(Integer.parseInt(orderList.get(position).getStatusOrder())+1));
        requireActivity().getSupportFragmentManager().popBackStack();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new OrderFragment(), "MainFragment")
                .addToBackStack(null)
                .commit();
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        tab.select();
    }

}