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
import com.duan1.shopbee.model.Category;
import com.duan1.shopbee.model.Profilel;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileHolder> {

    private List<Profilel> profilelList;


    public ProfileAdapter(List<Profilel> profilelList) {
        this.profilelList = profilelList;
    }

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder holder, int position) {
        mFunction function = new mFunction();
        holder.nameProfile.setText(profilelList.get(position).getNameProfile());
        holder.ivAvtProfile.setImageBitmap(function.StringBitMap(profilelList.get(position).getImgProfile()));
    }



    @Override
    public int getItemCount() {
        return profilelList.size();
    }

    public class ProfileHolder extends RecyclerView.ViewHolder{


        private TextView nameProfile;
        private ImageView ivAvtProfile;
        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            nameProfile = itemView.findViewById(R.id.profile_reitem);
            ivAvtProfile = itemView.findViewById(R.id.profile_reimg);

        }
    }
}
