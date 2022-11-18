package com.duan1.shopbee.slide_image;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class LivePhotoAdapter extends PagerAdapter {
    private Context context;
    private List<LivePhoto> listPhoto;

    public LivePhotoAdapter(Context context, List<LivePhoto> listPhoto) {
        this.context = context;
        this.listPhoto = listPhoto;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<LivePhoto> getListPhoto() {
        return listPhoto;
    }

    public void setListPhoto(List<LivePhoto> listPhoto) {
        this.listPhoto = listPhoto;
    }

    @Override
    public int getCount() {
        if(listPhoto != null){
            return listPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // remove view
        container.removeView((View) object);
    }
}
