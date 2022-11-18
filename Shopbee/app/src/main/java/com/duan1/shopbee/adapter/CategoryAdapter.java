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

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

private List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
@Override
public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_home, parent, false);
        return new CategoryHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        mFunction function = new mFunction();
        holder.nameCategory.setText(categoryList.get(position).getNameCategory());
        holder.ivAvtCategory.setImageBitmap(function.StringBitMap(categoryList.get(position).getImageCategory()));
        }

@Override
public int getItemCount() {
        return categoryList.size();
        }

public class CategoryHolder extends RecyclerView.ViewHolder{


    private TextView nameCategory;
    private ImageView ivAvtCategory;
    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        nameCategory = itemView.findViewById(R.id.tvNameCatrgory);
        ivAvtCategory = itemView.findViewById(R.id.ivCategory);

    }
}
}