package com.duan1.shopbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.MyViewHolder> {
    Context context;
    List<ProductCreate> listPC;

    public FeaturedProductAdapter(Context context, List<ProductCreate> listPC) {
        this.context = context;
        this.listPC = listPC;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        mFunction function = new mFunction();
//        holder.imageProduct.setImageBitmap(function.StringBitMap(listPC.get(position).getImageProduct()));
        holder.nameProduct.setText(listPC.get(position).getNameProduct());
        holder.priceProduct.setText(listPC.get(position).getPriceProduct());
        holder.soldProduct.setText(listPC.get(position).getSoldProduct());
        Glide.with(context)
                .load(listPC.get(position).getImageProduct())
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        if (listPC != null){
            return listPC.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageProduct;
        private TextView nameProduct;
        private TextView priceProduct;
        private TextView soldProduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProduct = itemView.findViewById(R.id.imageProduct);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            priceProduct = itemView.findViewById(R.id.priceProduct);
            soldProduct = itemView.findViewById(R.id.soldProducct);

        }
    }
}
