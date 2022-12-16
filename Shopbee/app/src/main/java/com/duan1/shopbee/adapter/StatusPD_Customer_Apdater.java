package com.duan1.shopbee.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.duan1.shopbee.fragment.Oder1_Customer_Fragment;
import com.duan1.shopbee.fragment.Oder2_Customer_Fragment;
import com.duan1.shopbee.fragment.Oder3_Customer_Fragment;
import com.duan1.shopbee.fragment.Oder4_Customer_Fragment;
import com.duan1.shopbee.fragment.Oder5_Customer_Fragment;

public class StatusPD_Customer_Apdater extends FragmentStateAdapter {
    public StatusPD_Customer_Apdater(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Oder1_Customer_Fragment();
            case 1:
                return new Oder2_Customer_Fragment();
            case 2:
                return new Oder3_Customer_Fragment();
            case 3:
                return new Oder4_Customer_Fragment();
            case 4:
                return new Oder5_Customer_Fragment();
            default:
                return new Oder3_Customer_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
