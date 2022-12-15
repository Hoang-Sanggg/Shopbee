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
import android.widget.TextView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.MyOrderAdapter;
import com.duan1.shopbee.adapter.OrderAdapter;
import com.duan1.shopbee.callback.ClickToOrder;
import com.duan1.shopbee.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrderFragment extends Fragment implements ClickToOrder {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");


    TextView btnBack_trans;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Order> orderList;
    private RecyclerView orderRecycer;
    private MyOrderAdapter orderAdapter;

    public MyOrderFragment() {
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
        return inflater.inflate(R.layout.fragment_my_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);


        readData();

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


    private void readData(){
        orderList = new ArrayList<>();
        databaseReference.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);
                    orderList.add(order);
                    data(getView());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        orderAdapter = new MyOrderAdapter(orderList, getContext(), MyOrderFragment.this);
    }

    private void data(View view){
        orderRecycer = view.findViewById(R.id.rcvTrans);

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
}