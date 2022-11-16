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
import com.duan1.shopbee.model.LiveStories;

import java.util.List;

public class LiveStoriesAdapter  extends RecyclerView.Adapter<LiveStoriesAdapter.ViewHolder>{
    private List<LiveStories> liveStoriesList;

    public LiveStoriesAdapter(List<LiveStories> liveStoriesList){
        this.liveStoriesList = liveStoriesList;
    }

    @NonNull
    @Override
    public LiveStoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livefragment_livestories,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiveStoriesAdapter.ViewHolder holder, int position) {
        mFunction function = new mFunction();
        holder.ivLiveStories.setImageBitmap(function.StringBitMap(liveStoriesList.get(position).getImage()));
        holder.nameLiveStories.setText(liveStoriesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nameLiveStories;
        private ImageView ivLiveStories;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLiveStories = itemView.findViewById(R.id.txtName);
            ivLiveStories = itemView.findViewById(R.id.ivAvatar);
        }
    }
}
