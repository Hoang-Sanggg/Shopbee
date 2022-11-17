package com.duan1.shopbee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Discouraged;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.LiveMain;

import java.util.List;

public class LiveMainAdapter extends RecyclerView.Adapter<LiveMainAdapter.ViewHolder> {

    private List<LiveMain> liveMainList;

    public LiveMainAdapter(List<LiveMain> liveMainList) {
        this.liveMainList = liveMainList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livefragment_mainliving,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mFunction function = new mFunction();
        holder.imgLiveMain.setImageBitmap(function.StringBitMap(liveMainList.get(position).getImgLiving()));
        holder.imgUser.setImageBitmap(function.StringBitMap(liveMainList.get(position).getImgUser()));

        holder.userName.setText(liveMainList.get(position).getUserName());
        holder.descriprion.setText(liveMainList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLiveMain;
        private TextView userName;
        private TextView descriprion;
        private ImageView imgUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLiveMain = itemView.findViewById(R.id.ivLiving);
            imgUser = itemView.findViewById(R.id.ivUser);
            userName  = itemView.findViewById(R.id.txtName);
            descriprion = itemView.findViewById(R.id.txtDescription);
        }
    }
}
