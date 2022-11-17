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
import com.duan1.shopbee.model.LiveVoucher;

import java.util.List;

public class LiveVoucherAdapter extends RecyclerView.Adapter<LiveVoucherAdapter.ViewHolder> {

    private List<LiveVoucher> liveVoucherList;

    public LiveVoucherAdapter(List<LiveVoucher> liveVoucherList){
        this.liveVoucherList = liveVoucherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livefragment_voucher,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mFunction function = new mFunction();
        holder.imgVoucher.setImageBitmap(function.StringBitMap(liveVoucherList.get(position).getVoucherImage()));
        holder.voucherName.setText(liveVoucherList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgVoucher;
        private TextView voucherName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVoucher = itemView.findViewById(R.id.imgVoucher);
            voucherName = itemView.findViewById(R.id.txtVoucherName);
        }
    }
}
