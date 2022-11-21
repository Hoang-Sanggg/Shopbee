package com.duan1.shopbee.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.duan1.shopbee.fragment.NotifFragment;
import com.duan1.shopbee.fragment.UpsellerFragment;

public class NotificationAdapter extends FragmentPagerAdapter {
    public NotificationAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NotifFragment();
            case 1:
                return new UpsellerFragment();

            default:
                return new NotifFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "thông báo của tôi";
                break;
            case 1:
                title = "cập nhật người bán";
                break;
        }
        return title;
    }
}
