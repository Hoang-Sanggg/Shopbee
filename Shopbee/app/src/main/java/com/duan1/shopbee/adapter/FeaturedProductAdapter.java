package com.duan1.shopbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.MyViewHolder> {

    private List<ProductCreate> listPC;
    private Context context;
    private ClickToProductSale clickToProduct;

    public FeaturedProductAdapter(List<ProductCreate> listPC, Context context, ClickToProductSale clickToProduct) {
        this.listPC = listPC;
        this.context = context;
        this.clickToProduct = clickToProduct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.imageProduct.setImageBitmap(function.StringBitMap(listPC.get(position).getImageProduct()));



        holder.nameProduct.setText(listPC.get(position).getNameProduct());
        holder.priceProduct.setText(listPC.get(position).getPriceProduct());
        holder.soldProduct.setText(listPC.get(position).getSoldProduct());
        Glide.with(context)
                .load(listPC.get(position).getImageProduct())
                .into(holder.imageProduct);
        holder.rootProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToProduct.onClickToProductSale(listPC, holder.getAdapterPosition());
            }
        });

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
        private LinearLayout rootProduct;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProduct = itemView.findViewById(R.id.imageProduct);
            nameProduct = itemView.findViewById(R.id.nameProduct);
            priceProduct = itemView.findViewById(R.id.priceProduct);
            soldProduct = itemView.findViewById(R.id.soldProducct);
            rootProduct = itemView.findViewById(R.id.rootProduct);
        }
    }
}
