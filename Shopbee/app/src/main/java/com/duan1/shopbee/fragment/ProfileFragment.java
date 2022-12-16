package com.duan1.shopbee.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duan1.shopbee.LoginActivity;
import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.ProfileAdapter;
import com.duan1.shopbee.callback.HideBottomNav;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.model.ProductCreate;
import com.duan1.shopbee.model.Profile;
import com.duan1.shopbee.model.User;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements ShowBottomNav{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "DATA_PROFILE";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView ProfileRecycler;

    private List<Profile> mProfiles;
    private List<ProductCreate> productCreateList;

    private ProfileAdapter profileAdapter;

    TextView logout;
    ConstraintLayout constraintLayout;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ShowBottomNav showBottomNav;

    HideBottomNav hideBottomNav;

    public ProfileFragment(HideBottomNav hideBottomNav, ShowBottomNav showBottomNav) {
        // Required empty public constructor
        this.hideBottomNav = hideBottomNav;
        this.showBottomNav = showBottomNav;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(List<Profile>_mProfiles, List<ProductCreate> _listProduct, HideBottomNav hideBottomNav, ShowBottomNav showBottomNav) {
        ProfileFragment fragment = new ProfileFragment(hideBottomNav, showBottomNav);
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) _mProfiles);
        args.putSerializable(ARG_PARAM2, (Serializable) _listProduct);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfiles = (List<Profile>) getArguments().getSerializable(ARG_PARAM1);
            productCreateList = (List<ProductCreate>) getArguments().getSerializable(ARG_PARAM2);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ProfileRecycler = view.findViewById(R.id.recyclerProfile);
//
//        ProfileRecycler.setHasFixedSize(true);
//
//        ProfileRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//        mProfiles.size();
//
//        profileAdapter = new ProfileAdapter(mProfiles);
//
//        ProfileRecycler.setAdapter(profileAdapter);

        logout = view.findViewById(R.id.btn_logout);

        constraintLayout = view.findViewById(R.id.cTr_profile_img_bill);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, new Order_Customer_Fragment(), "MainFragment")
                        .addToBackStack(null)
                        .commit();
                hideBottomNav.hideBottomNav();
            }
        });

        showBottomNav.showBottomNav();
        TextView textView = view.findViewById(R.id.btn_myShop);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMyShop(view);
                hideBottomNav.hideBottomNav();
            }
        });
        logout = view.findViewById(R.id.btn_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("LOGIN_STATUS",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }



    public void onClickMyShop(View view) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new MyShopFragment(productCreateList,ProfileFragment.this), "MainFragment")
                .addToBackStack(null)
                .commit();
        hideBottomNav.hideBottomNav();
    }

    @Override
    public void showBottomNav() {
        showBottomNav.showBottomNav();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
