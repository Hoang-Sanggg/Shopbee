package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Buy_Fragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buy_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ProductCreate> productCreateList;
    ShowBottomNav showBottomNav;

    public Buy_Fragment() {
    }

    public Buy_Fragment(List<ProductCreate> productCreateList, ProductFragment showBottomNav) {
        this.productCreateList = productCreateList;
        this.showBottomNav = (ShowBottomNav) showBottomNav;
    }

    public Buy_Fragment(int contentLayoutId, List<ProductCreate> productCreateList, ShowBottomNav showBottomNav) {
        super(contentLayoutId);
        this.productCreateList = productCreateList;
        this.showBottomNav = showBottomNav;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment Buy_Fragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static Buy_Fragment newInstance(String param1, String param2) {
//        Buy_Fragment fragment = new Buy_Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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
        return inflater.inflate(R.layout.fragment_buy_, container, false);
    }

    public void onClickMyProduct(View view) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new MyProductFragment(productCreateList), "MainFragment")
                .addToBackStack(null)
                .commit();
    }
}