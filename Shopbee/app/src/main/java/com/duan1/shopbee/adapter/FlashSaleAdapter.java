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

import com.duan1.shopbee.model.Flashsale;

import java.util.List;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.FlashSaleHoder> {

    private List<Flashsale> flashsaleList;

    public FlashSaleAdapter(List<Flashsale> flashsaleList) {
        this.flashsaleList = flashsaleList;
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
        holder.ivFlashSale.setImageBitmap(function.StringBitMap(flashsaleList.get(position).getImageFlashSale()));
//        holder.discountFlashSale.setText(flashsaleList.get(position).getDiscountFlashSale());
    }

    @Override
    public int getItemCount() {
        return flashsaleList.size();
    } //quan trong

    public class FlashSaleHoder extends RecyclerView.ViewHolder{


        private TextView priceFlashSale, discountFlashSale, soldFlashSale;
        private ImageView ivFlashSale;
        public FlashSaleHoder(@NonNull View itemView) {
            super(itemView);
            priceFlashSale = itemView.findViewById(R.id.tvPriceFlashSale);
//            discountFlashSale = itemView.findViewById(R.id.);
            soldFlashSale = itemView.findViewById(R.id.tvSold);
            ivFlashSale = itemView.findViewById(R.id.ivFlashSale);
        }
    }
}
