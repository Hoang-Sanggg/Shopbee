package com.duan1.shopbee.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.duan1.shopbee.fragment.Oder1_Fragment;
import com.duan1.shopbee.fragment.Oder2_Fragment;
import com.duan1.shopbee.fragment.Oder3_Fragment;
import com.duan1.shopbee.fragment.Oder4_Fragment;
import com.duan1.shopbee.fragment.Oder5_Fragment;

public class StatusPDApdater extends FragmentStateAdapter {
    public StatusPDApdater(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Oder1_Fragment();
            case 1:
                return new Oder2_Fragment();
            case 2:
                return new Oder3_Fragment();
            case 3:
                return new Oder4_Fragment();
            case 4:
                return new Oder5_Fragment();
            default:
                return new Oder1_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
