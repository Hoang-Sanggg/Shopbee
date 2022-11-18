package com.duan1.shopbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.duan1.shopbee.adapter.BannerAdpater;
import com.duan1.shopbee.mode.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MallActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdpater bannerAdpater;
    private List<Banner> listBanner;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circle_indicator);

        listBanner = getListBanner();
        bannerAdpater = new BannerAdpater(this, listBanner);
        viewPager.setAdapter(bannerAdpater);

        circleIndicator.setViewPager(viewPager);
        bannerAdpater.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImages();
    }

    private List<Banner> getListBanner() {
        List<Banner> list = new ArrayList<>();
        list.add(new Banner(R.drawable.banner_6));
        list.add(new Banner(R.drawable.banner_7));
        list.add(new Banner(R.drawable.banner_8));
        list.add(new Banner(R.drawable.banner_9));
        list.add(new Banner(R.drawable.banner_10));

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
                        int currentItem  = viewPager.getCurrentItem();
                        int totalItem = listBanner.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}