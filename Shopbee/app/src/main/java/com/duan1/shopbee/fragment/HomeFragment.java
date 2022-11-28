package com.duan1.shopbee.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.CategoryAdapter;
import com.duan1.shopbee.adapter.FlashSaleAdapter;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.model.Category;
import com.duan1.shopbee.model.Flashsale;
import com.duan1.shopbee.model.ProductCreate;
import com.duan1.shopbee.slide_image.Photo;
import com.duan1.shopbee.slide_image.PhotoAdaper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ClickToProductSale {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "DATA_CATEGORY";
    private static final String ARG_PARAM2 = "DATA_FLASESALE";
    private static final String ARG_PARAM3 = "DATA_SRC_BANNER";

    // TODO: Rename and change types of parameters
    private List<Category> categoryList;
    private List<ProductCreate> flashsaleList;
    private CategoryAdapter categoryAdapter;
    private FlashSaleAdapter flashSaleAdapter;

    private RecyclerView categoryRecycler,flashsaleRecycler;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdaper photoAdaper;
    private List<Photo> listPhoto;
    private Timer timer;

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
    public static HomeFragment newInstance(List<Category> _categoryList, List<ProductCreate> _flashsaleList, List<Photo> _data) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) _categoryList);
        args.putSerializable(ARG_PARAM2, (Serializable) _flashsaleList);
        args.putSerializable(ARG_PARAM3, (Serializable) _data );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryList = (List<Category>) getArguments().getSerializable(ARG_PARAM1);
            flashsaleList = (List<ProductCreate>) getArguments().getSerializable(ARG_PARAM2);
            listPhoto = (List<Photo>) getArguments().getSerializable(ARG_PARAM3);
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

        flashSaleAdapter = new FlashSaleAdapter(flashsaleList, getContext(), HomeFragment.this);
        flashsaleRecycler.setAdapter(flashSaleAdapter);


        viewPager = view.findViewById(R.id.viewPager_banner);
//        circleIndicator = view.findViewById(R.id.circle_banner);

        listPhoto = getListPhoto();

        photoAdaper = new PhotoAdaper(getContext(), getListPhoto());
        viewPager.setAdapter(photoAdaper);

//        circleIndicator.setViewPager(viewPager);

//        photoAdaper.registerDataSetObserver(circleIndicator.getDataSetObserver());


        autoSlideImage();


    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.banner_7));
        list.add(new Photo(R.drawable.banner_6));
        list.add(new Photo(R.drawable.banner_8));
        list.add(new Photo(R.drawable.banner_9));
        list.add(new Photo(R.drawable.banner_10));
        return list;
    }

    private void autoSlideImage(){
        if (listPhoto == null || listPhoto.isEmpty() || viewPager == null){
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
                            int totalItem = listPhoto.size()-1;
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

    @Override
    public void onClickToProductSale(List<ProductCreate> flashsaleList, int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new FragmentProduct(flashsaleList.get(position).getIdProduct(),flashsaleList.get(position).getNameProduct(), flashsaleList.get(position).getDescription(), flashsaleList.get(position).getIndustry(), flashsaleList.get(position).getPriceProduct(), flashsaleList.get(position).getProductdetail(), flashsaleList.get(position).getWarehouse(),flashsaleList.get(position).getTransportfee(), flashsaleList.get(position).getStatus(), flashsaleList.get(position).getNameShop(), flashsaleList.get(position).getSoldProduct(), flashsaleList.get(position).getBrandProduct(), flashsaleList.get(position).getOriginProduct(), flashsaleList.get(position).getBaoHanhSp(), flashsaleList.get(position).getShippingProduct(), flashsaleList.get(position).getPriceFlashSale(), flashsaleList.get(position).getDiscountFlashSale(), flashsaleList.get(position).getSoldFlashSale(), flashsaleList.get(position).getImageProduct()), "MainFragment")
                .addToBackStack(null)
                .commit();
        Toast.makeText(getContext(), flashsaleList.get(position).getIdProduct(), Toast.LENGTH_SHORT).show();
    }
}