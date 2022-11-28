package com.duan1.shopbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.function.mFunction;

import com.duan1.shopbee.model.Flashsale;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.FlashSaleHoder> {

    private List<ProductCreate> flashsaleList;
    private Context context;
    private ClickToProductSale clickToProductSale;

    public FlashSaleAdapter(List<ProductCreate> flashsaleList, Context context, ClickToProductSale clickToProductSale) {
        this.flashsaleList = flashsaleList;
        this.context = context;
        this.clickToProductSale = clickToProductSale;
    }

    @NonNull
    @Override
    public FlashSaleAdapter.FlashSaleHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale, parent, false);
        return new FlashSaleAdapter.FlashSaleHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlashSaleAdapter.FlashSaleHoder holder, int position) {
        mFunction function = new mFunction();
        holder.priceFlashSale.setText(flashsaleList.get(position).getPriceFlashSale());
        holder.soldFlashSale.setText(flashsaleList.get(position).getSoldFlashSale());
//        holder.ivFlashSale.setImageBitmap(function.StringBitMap(flashsaleList.get(position).getImageFlashSale()));
//        holder.discountFlashSale.setText(flashsaleList.get(position).getDiscountFlashSale());

        holder.rootFlashSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToProductSale.onClickToProductSale(flashsaleList, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return flashsaleList.size();
    } //quan trong

    public class FlashSaleHoder extends RecyclerView.ViewHolder{


        private TextView priceFlashSale, discountFlashSale, soldFlashSale;
        private ImageView ivFlashSale;
        private LinearLayout rootFlashSale;
        public FlashSaleHoder(@NonNull View itemView) {
            super(itemView);
            priceFlashSale = itemView.findViewById(R.id.tvPriceFlashSale);
//            discountFlashSale = itemView.findViewById(R.id.);
            soldFlashSale = itemView.findViewById(R.id.tvSold);
            ivFlashSale = itemView.findViewById(R.id.ivFlashSale);
            rootFlashSale = itemView.findViewById(R.id.rootFlashSale);
        }
    }
}
