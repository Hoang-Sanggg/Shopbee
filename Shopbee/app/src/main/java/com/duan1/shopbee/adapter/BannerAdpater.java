package com.duan1.shopbee.adapter;

import android.appwidget.AppWidgetHost;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.mode.Banner;

import java.util.List;

public class BannerAdpater extends PagerAdapter {

    public BannerAdpater(Context context, List<Banner> listBanner) {
        this.context = context;
        this.listBanner = listBanner;
    }

    private Context context;
    private List<Banner> listBanner;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View  view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, container, false);
        ImageView imgBanner = view.findViewById(R.id.img_banner);

        Banner banner = listBanner.get(position);
        if (banner != null) {
            Glide.with(context).load(banner.getResoucreId()).into(imgBanner);
        }

        //Add view to viewGroup
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if (listBanner != null) {
            return listBanner.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove view
        container.removeView((View) object);
    }
}
