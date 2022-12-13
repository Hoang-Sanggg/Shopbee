package com.duan1.shopbee.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickToBuy;
import com.duan1.shopbee.callback.ClickToOrder;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.model.Order;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHoder> {

    private List<ProductCreate> orderList;
    private Context context;
    private ClickToProductSale clickToProductSale;
    private ClickToBuy clickToBuy;

    public CartAdapter(List<ProductCreate> orderList, Context context, ClickToProductSale clickToProductSale, ClickToBuy clickToBuy) {
        this.orderList = orderList;
        this.context = context;
        this.clickToProductSale = clickToProductSale;
        this.clickToBuy = clickToBuy;
    }

    @NonNull
    @Override
    public CartAdapter.CartHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartAdapter.CartHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartHoder holder, int position) {

        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String nameShopS = sharedPref.getString("username", "");

        holder.priceOrder.setText(orderList.get(position).getPriceProduct());
        holder.nameProductOrder.setText(orderList.get(position).getNameProduct());
//        holder.ivFlashSale.setImageBitmap(function.StringBitMap(flashsaleList.get(position).getImageFlashSale()));
//        holder.discountFlashSale.setText(flashsaleList.get(position).getDiscountFlashSale());
        Glide.with(context)
                    .load(orderList.get(position).getImageProduct())
                    .into(holder.ivProduct);

        holder.root_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToProductSale.onClickToProductSale(orderList, holder.getAdapterPosition());
            }
        });
//        holder.rootFlashSale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                clickToProductSale.onClickToProductSale(flashsaleList, holder.getAdapterPosition());
//            }
//        });
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToBuy.ClickToBuy(orderList, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    } //quan trong

    public class CartHoder extends RecyclerView.ViewHolder{
        private TextView idProductOrder, customer,  seller, priceOrder, priceProductOrder, numberOfOrder, nameProductOrder, statusOrder, dateOrder;
        private ImageView ivProduct;
        private LinearLayout root_Cart;
        private Button btnBuy;
        public CartHoder(@NonNull View itemView) {
            super(itemView);

            priceOrder = itemView.findViewById(R.id.Cart_price);
            nameProductOrder = itemView.findViewById(R.id.CartName);
            ivProduct = itemView.findViewById(R.id.Cart_image);
            root_Cart = itemView.findViewById(R.id.item_cart);

            btnBuy = itemView.findViewById(R.id.btnBuy);

        }
    }


}
