package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.CategoryAdapter;
import com.duan1.shopbee.adapter.FlashSaleAdapter;
import com.duan1.shopbee.model.Category;
import com.duan1.shopbee.model.Flashsale;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "DATA_CATEGORY";
    private static final String ARG_PARAM2 = "DATA_FLASESALE";

    // TODO: Rename and change types of parameters
    private List<Category> categoryList;
    private List<Flashsale> flashsaleList;
    private CategoryAdapter categoryAdapter;
    private FlashSaleAdapter flashSaleAdapter;

    private RecyclerView categoryRecycler,flashsaleRecycler;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(List<Category> _categoryList, List<Flashsale> _flashsaleList) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) _categoryList);
        args.putString(ARG_PARAM2, param2);
        args.putSerializable(ARG_PARAM2, (Serializable) _flashsaleList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryList = (List<Category>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            flashsaleList = (List<Flashsale>) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryRecycler = view.findViewById(R.id.recyclerCategory);
        categoryRecycler.setHasFixedSize(true);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.HORIZONTAL, false));

        categoryList.size();
        categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

        flashsaleRecycler = view.findViewById(R.id.recyclerFlashSales);
        flashsaleRecycler.setHasFixedSize(true);
        flashsaleRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1,GridLayoutManager.HORIZONTAL, false));

        flashSaleAdapter = new FlashSaleAdapter(flashsaleList);
        flashsaleRecycler.setAdapter(flashSaleAdapter);
    }

}