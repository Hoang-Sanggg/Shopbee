package com.duan1.shopbee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public class AddMyProductAdapter extends RecyclerView.Adapter<AddMyProductAdapter.MyProductViewHodel> {

    private Context mContext;
    private List<ProductCreate> mListMyProduct;

    public AddMyProductAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public AddMyProductAdapter(Context mContext, List<ProductCreate> mListMyProduct) {
        this.mContext = mContext;
        this.mListMyProduct = mListMyProduct;
    }

    public void setData(List<ProductCreate> list){
        this.mListMyProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyProductViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_product, parent, false);
        return new MyProductViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductViewHodel holder, int position) {
        mFunction function = new mFunction();
        holder.tvThongTinSP_My_Product.setText(mListMyProduct.get(position).getNameProduct());
        holder.txtGiaSP_My_Product.setText(mListMyProduct.get(position).getPriceProduct());
        holder.txtKhoHang_My_Product.setText(mListMyProduct.get(position).getWarehouse());
        holder.txtDaBan_My_Product.setText(mListMyProduct.get(position).getSoldProduct());
        /** holder.txtLuotThich_My_Product.setText(mListMyProduct.get(position).getLuotThich());
        holder.txtLuotXem_My_Product.setText(mListMyProduct.get(position).getLuotThich()); */

        Glide.with(mContext)
                .load(mListMyProduct.get(position).getImageProduct())
                .into(holder.ivHinhSP_My_Product);
    }

    @Override
    public int getItemCount() {
        if (mListMyProduct != null){
            return mListMyProduct.size();
        }
        return 0;
    }

    public class MyProductViewHodel extends RecyclerView.ViewHolder {

        private ImageView ivHinhSP_My_Product;
        private TextView tvThongTinSP_My_Product, txtGiaSP_My_Product, txtKhoHang_My_Product, txtDaBan_My_Product, txtLuotThich_My_Product, txtLuotXem_My_Product;
        private ImageView ivUuDai_My_Product;
        private Button btnAn_My_Product, btnSua_My_Product, btnThem_My_Product;

        public MyProductViewHodel(@NonNull View itemView) {
            super(itemView);

            ivHinhSP_My_Product = itemView.findViewById(R.id.ivMyProduct);
            tvThongTinSP_My_Product = itemView.findViewById(R.id.tvThongTinSP_My_Product);
            txtGiaSP_My_Product = itemView.findViewById(R.id.txtGiaSP_My_Product);
            txtKhoHang_My_Product = itemView.findViewById(R.id.txtKhoHang_My_Product);
            txtDaBan_My_Product = itemView.findViewById(R.id.txtDaBan_My_Product);
            txtLuotThich_My_Product = itemView.findViewById(R.id.txtLuotThich_My_Product);
            txtLuotXem_My_Product = itemView.findViewById(R.id.txtLuotXem_My_Product);
            ivUuDai_My_Product = itemView.findViewById(R.id.ivUuDai_My_Product);
            btnAn_My_Product = itemView.findViewById(R.id.btnAn_My_Product);
            btnSua_My_Product = itemView.findViewById(R.id.btnSua_My_Product);
            btnThem_My_Product = itemView.findViewById(R.id.btnThem_My_Product);
        }
    }
}
