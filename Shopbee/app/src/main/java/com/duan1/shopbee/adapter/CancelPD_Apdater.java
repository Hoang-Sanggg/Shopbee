package com.duan1.shopbee.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.duan1.shopbee.fragment.Cancel1_Fragment;
import com.duan1.shopbee.fragment.Cancel2_Fragment;

public class CancelPD_Apdater extends FragmentStateAdapter {
    public CancelPD_Apdater(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Cancel1_Fragment();
            case 1:
                return new Cancel2_Fragment();
            default:
                return new Cancel1_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
