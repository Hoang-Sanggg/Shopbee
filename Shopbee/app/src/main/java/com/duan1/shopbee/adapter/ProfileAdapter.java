package com.duan1.shopbee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.Profile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHoder> {

    private List<Profile> mProfiles;

    public ProfileAdapter(List<Profile> mProfiles) {
        this.mProfiles = mProfiles;
    }



    @NonNull
    @Override
    public ProfileViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ProfileViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHoder holder, int position) {
        mFunction function = new mFunction();
        holder.profile_Img_item.setImageBitmap(function.StringBitMap(mProfiles.get(position).getPimg()));
        holder.profile_Name_item.setText(mProfiles.get(position).getPname());
    }

    @Override
    public int getItemCount() {
        return mProfiles.size();
    }

    public class ProfileViewHoder extends RecyclerView.ViewHolder{

        private ImageView profile_Img_item;
        private TextView profile_Name_item;

        public ProfileViewHoder(@NonNull View itemView) {
            super(itemView);

            profile_Img_item = itemView.findViewById(R.id.ivAvatar);
            profile_Name_item = itemView.findViewById(R.id.txtName);

        }
    }
}
