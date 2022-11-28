package com.duan1.shopbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.fragment.MallFragment;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.CategoryMall;

import java.util.List;



public class CategoryMallAdapter extends RecyclerView.Adapter<CategoryMallAdapter.CategoryMallViewHolder> {

    private List<CategoryMall> categoryMallList;

    public CategoryMallAdapter(List<CategoryMall> categoryMallList) {
        this.categoryMallList = categoryMallList;
    }

    @NonNull
    @Override
    public CategoryMallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_mall, parent, false);
        return new CategoryMallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMallViewHolder holder, int position) {
        mFunction function = new mFunction();
        holder.mall_Name_item.setText(categoryMallList.get(position).getNameCategoryMall());
        holder.mall_Img_item.setImageBitmap(function.StringBitMap(categoryMallList.get(position).getImageCategoryMall()));

    }

    @Override
    public int getItemCount() {
        return categoryMallList.size();
    }


    public class CategoryMallViewHolder extends RecyclerView.ViewHolder {

        private TextView mall_Name_item;
        private ImageView mall_Img_item;

        public CategoryMallViewHolder(@NonNull View itemView) {
            super(itemView);
            mall_Img_item = itemView.findViewById(R.id.ivCategoryMall);
            mall_Name_item = itemView.findViewById(R.id.txtNameCatrgoryMall);
        }
    }
}
