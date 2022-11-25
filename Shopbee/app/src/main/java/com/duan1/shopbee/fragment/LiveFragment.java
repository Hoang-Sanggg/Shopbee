package com.duan1.shopbee.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.LiveMainAdapter;
import com.duan1.shopbee.adapter.LiveStoriesAdapter;
import com.duan1.shopbee.adapter.LiveVoucherAdapter;
import com.duan1.shopbee.model.LiveMain;
import com.duan1.shopbee.model.LiveStories;
import com.duan1.shopbee.model.LiveVoucher;
import com.duan1.shopbee.slide_image.LivePhoto;
import com.duan1.shopbee.slide_image.LivePhotoAdapter;
import com.duan1.shopbee.slide_image.Photo;
import com.duan1.shopbee.slide_image.PhotoAdaper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private static final String ARG_PARAM4 = "DATA_LIVEPHOTO";
    private RecyclerView liveStoriesRecycler,liveVoucherRecycler,liveMainRecycler;
    private LivePhoto listPhoto;
    private ViewPager viewPager;
    private Timer timer;


    private List<LiveStories> liveStoriesList;
    private List<LiveVoucher> liveVoucherList;
    private List<LiveMain> liveMainList;
    private List<LivePhoto> livePhotoList;

    private LiveVoucherAdapter liveVoucherAdapter;
    private LiveStoriesAdapter liveStoriesAdapter;
    private LiveMainAdapter liveMainAdapter;
    private LivePhotoAdapter livePhotoAdapter;

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
     *
     */
    // TODO: Rename and change types and number of parameters
    public static LiveFragment newInstance(List<LiveStories>_liveStoriesList, List<LiveVoucher> _liveVoucherList, List<LiveMain> _liveMainList,List<LivePhoto> _livePhotoList) {
        LiveFragment fragment = new LiveFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,(Serializable) _liveStoriesList);
        args.putSerializable(ARG_PARAM2,(Serializable) _liveVoucherList);
        args.putSerializable(ARG_PARAM3,(Serializable) _liveMainList);
        args.putSerializable(ARG_PARAM4,(Serializable) _livePhotoList);
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
            livePhotoList = (List<LivePhoto>) getArguments().getSerializable(ARG_PARAM4);

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
        liveMainRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL, false));

        liveVoucherAdapter = new LiveVoucherAdapter(liveVoucherList);
        liveVoucherRecycler.setAdapter(liveVoucherAdapter);

        liveMainAdapter = new LiveMainAdapter(liveMainList);
        liveMainRecycler.setAdapter(liveMainAdapter);

        //slideshow

        viewPager = view.findViewById(R.id.viewPager_banner);
//        circleIndicator = view.findViewById(R.id.circle_banner);

        livePhotoList = getListPhoto();
//
        livePhotoAdapter= new LivePhotoAdapter(getContext(), getListPhoto());
        viewPager.setAdapter(livePhotoAdapter);

//        circleIndicator.setViewPager(viewPager);

//        photoAdaper.registerDataSetObserver(circleIndicator.getDataSetObserver());

    
        autoSlideImage();
    }

    private List<LivePhoto> getListPhoto(){
        List<LivePhoto> list = new ArrayList<>();
        list.add(new LivePhoto(R.mipmap.live_banner01));
        list.add(new LivePhoto(R.mipmap.live_banner02));
        list.add(new LivePhoto(R.mipmap.live_banner03));
        return list;
    }

    private void autoSlideImage(){
        if (livePhotoList == null || livePhotoList.isEmpty() || viewPager == null){
            return;
        }
        if(timer == null){
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = viewPager.getCurrentItem();
                            int totalItem = livePhotoList.size()-1;
                            if (currentItem < totalItem){
                                currentItem++;
                                viewPager.setCurrentItem(currentItem);
                            }else{
                                viewPager.setCurrentItem(0);
                            }
                        }
                    });
                }
            }, 500, 3000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null ){
            timer.cancel();
            timer = null;
        }
    }


    }

