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

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.CategoryAdapter;
import com.duan1.shopbee.adapter.LiveMainAdapter;
import com.duan1.shopbee.adapter.LiveStoriesAdapter;
import com.duan1.shopbee.adapter.LiveVoucherAdapter;
import com.duan1.shopbee.model.LiveMain;
import com.duan1.shopbee.model.LiveStories;
import com.duan1.shopbee.model.LiveVoucher;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "DATA_LIVESTORIES";
    private static final String ARG_PARAM2 = "DATA_LIVEVOUCHER";
    private static final String ARG_PARAM3= "DATA_LIVEMAIN";
    private RecyclerView liveStoriesRecycler,liveVoucherRecycler,liveMainRecycler;

    private List<LiveStories> liveStoriesList;
    private List<LiveVoucher> liveVoucherList;
    private List<LiveMain> liveMainList;

    private LiveVoucherAdapter liveVoucherAdapter;
    private LiveStoriesAdapter liveStoriesAdapter;
    private LiveMainAdapter liveMainAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;

    public LiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveFragment newInstance(String param1, String param2, String param3) {
        LiveFragment fragment = new LiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            liveStoriesList = (List<LiveStories>) getArguments().getSerializable(ARG_PARAM1);
            liveVoucherList = (List<LiveVoucher>) getArguments().getSerializable(ARG_PARAM2);
            liveMainList = (List<LiveMain>) getArguments().getSerializable(ARG_PARAM3);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        liveStoriesRecycler = view.findViewById(R.id.recyclerLiveStories);
        liveVoucherRecycler = view.findViewById(R.id.recyclerLiveVoucher);
        liveMainRecycler = view.findViewById(R.id.recyclerLiveMain);



        liveStoriesRecycler.setHasFixedSize(true);
        liveVoucherRecycler.setHasFixedSize(true);
        liveMainRecycler.setHasFixedSize(true);


        liveStoriesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        liveVoucherRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        liveStoriesList.size();
        liveVoucherList.size();
        liveMainList.size();

        liveStoriesAdapter = new LiveStoriesAdapter(liveStoriesList);
        liveStoriesRecycler.setAdapter(liveStoriesAdapter);

        liveVoucherAdapter = new LiveVoucherAdapter(liveVoucherList);
        liveVoucherRecycler.setAdapter(liveVoucherAdapter);

        liveMainAdapter = new LiveMainAdapter(liveMainList);
        liveMainRecycler.setAdapter(liveMainAdapter);


    }

}