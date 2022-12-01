package com.duan1.shopbee.fragment;

import android.content.Context;
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

import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.CategoryAdapter;
import com.duan1.shopbee.adapter.CategoryMallAdapter;
import com.duan1.shopbee.adapter.FeaturedProductAdapter;
import com.duan1.shopbee.adapter.FlashSaleAdapter;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.callback.HideBottomNav;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.model.Category;
import com.duan1.shopbee.model.CategoryMall;
import com.duan1.shopbee.model.Flashsale;
import com.duan1.shopbee.model.ProductCreate;
import com.duan1.shopbee.model.Profile;
import com.duan1.shopbee.slide_image.MallBanner;
import com.duan1.shopbee.slide_image.MallBannerAdapter;
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
 * Use the {@link MallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MallFragment extends Fragment implements ClickToProductSale, ShowBottomNav {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "DATA_CATEGORY";
    private static final String ARG_PARAM2 = "DATA_FLASESALE";
    private static final String ARG_PARAM3 = "DATA_SRC_BANNER";


    // TODO: Rename and change types of parameters
    private List<CategoryMall> mcategoryList;
    private RecyclerView categoryRecycler,flashsaleRecycler,productMallRecycler;
    private CategoryMallAdapter categoryMallAdapter;
    private List<ProductCreate> flashsaleList;
    private FlashSaleAdapter flashSaleAdapter;
    private FeaturedProductAdapter featuredProductAdapter;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private MallBannerAdapter bannerAdpater;
    private List<MallBanner> listBanner;
    private Timer timer;

    ShowBottomNav showBottomNav;

    HideBottomNav hideBottomNav;

    public MallFragment(HideBottomNav hideBottomNav, ShowBottomNav showBottomNav) {
        // Required empty public constructor
        this.hideBottomNav = hideBottomNav;
        this.showBottomNav = showBottomNav;
    }

    public MallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment NewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MallFragment newInstance(List<CategoryMall> _categoryList,List<ProductCreate> _flashsaleList, List<MallBanner> _data, HideBottomNav hideBottomNav, ShowBottomNav showBottomNav) {
        MallFragment fragment = new MallFragment(hideBottomNav, showBottomNav);
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
            mcategoryList = (List<CategoryMall>) getArguments().getSerializable(ARG_PARAM1);
            flashsaleList = (List<ProductCreate>) getArguments().getSerializable(ARG_PARAM2);
            listBanner = (List<MallBanner>) getArguments().getSerializable(ARG_PARAM3);
        }

        mcategoryList = (List<CategoryMall>) getArguments().getSerializable(ARG_PARAM1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mall, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        productMallRecycler = view.findViewById(R.id.recyclerProductMall);
        productMallRecycler.setHasFixedSize(true);
        productMallRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2,GridLayoutManager.VERTICAL, false));

        featuredProductAdapter = new FeaturedProductAdapter(getContext(), flashsaleList);
        productMallRecycler.setAdapter(featuredProductAdapter);

        flashsaleRecycler = view.findViewById(R.id.recyclerFlashSales);
        flashsaleRecycler.setHasFixedSize(true);
        flashsaleRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1,GridLayoutManager.HORIZONTAL, false));

        flashSaleAdapter = new FlashSaleAdapter(flashsaleList, getContext(), MallFragment.this);
        flashsaleRecycler.setAdapter(flashSaleAdapter);

        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        listBanner = getListBanner();
        bannerAdpater = new MallBannerAdapter(getContext(), getListBanner());
        viewPager.setAdapter(bannerAdpater);

        circleIndicator.setViewPager(viewPager);

        bannerAdpater.registerDataSetObserver(circleIndicator.getDataSetObserver());

       autoSlideImages();
    }

    private List<MallBanner> getListBanner() {
        List<MallBanner> list = new ArrayList<>();
        list.add(new MallBanner(R.drawable.img_banner_01));
        list.add(new MallBanner(R.drawable.img_banner_02));
        list.add(new MallBanner(R.drawable.img_banner_03));
        list.add(new MallBanner(R.drawable.img_banner_04));
        list.add(new MallBanner(R.drawable.img_banner_05));
        list.add(new MallBanner(R.drawable.img_banner_06));
        list.add(new MallBanner(R.drawable.img_banner_07));
        list.add(new MallBanner(R.drawable.img_banner_08));
        list.add(new MallBanner(R.drawable.img_banner_09));
        list.add(new MallBanner(R.drawable.img_banner_10));


        return list;
    }

    private void autoSlideImages() {
        if (listBanner == null && listBanner.isEmpty() && viewPager == null) {
            return;
        }

        //Init Timer
        if (timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = listBanner.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    @Override
    public void onClickToProductSale(List<ProductCreate> flashsaleList, int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new FragmentProduct(flashsaleList.get(position).getIdProduct(),flashsaleList.get(position).getNameProduct(), flashsaleList.get(position).getDescription(), flashsaleList.get(position).getIndustry(), flashsaleList.get(position).getPriceProduct(), flashsaleList.get(position).getProductdetail(), flashsaleList.get(position).getWarehouse(),flashsaleList.get(position).getTransportfee(), flashsaleList.get(position).getStatus(), flashsaleList.get(position).getNameShop(), flashsaleList.get(position).getSoldProduct(), flashsaleList.get(position).getBrandProduct(), flashsaleList.get(position).getOriginProduct(), flashsaleList.get(position).getBaoHanhSp(), flashsaleList.get(position).getShippingProduct(), flashsaleList.get(position).getPriceFlashSale(), flashsaleList.get(position).getDiscountFlashSale(), flashsaleList.get(position).getSoldFlashSale(), flashsaleList.get(position).getImageProduct(), MallFragment.this), "MainFragment")
                .addToBackStack(null)
                .commit();
        hideBottomNav.hideBottomNav();
    }

    @Override
    public void showBottomNav() {
        showBottomNav.showBottomNav();
    }
}