package com.duan1.shopbee.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.duan1.shopbee.fragment.NotifFragment;
import com.duan1.shopbee.fragment.UpsellerFragment;


public class NotifiAdapter extends FragmentStateAdapter {
    public NotifiAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
    public int getItemCount() {
        return 2;
    }
}
