package com.duan1.shopbee.adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.duan1.shopbee.callback.ClickToOrder;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.model.Order;
import com.duan1.shopbee.model.ProductCreate;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHoder> {

    private List<Order> orderList;
    private Context context;
    private ClickToOrder clickToOrder;
//    private ClickToProductSale clickToProductSale;


    public OrderAdapter(List<Order> orderList, Context context, ClickToOrder clickToOrder) {
        this.orderList = orderList;
        this.context = context;
        this.clickToOrder = clickToOrder;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_product, parent, false);
        return new OrderAdapter.OrderHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderHoder holder, int position) {

        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String nameShopS = sharedPref.getString("username", "");

        if(orderList.get(position).getSeller().equals(nameShopS)){
            holder.idProductOrder.setText(orderList.get(position).getIdProductOrder());
            holder.customer.setText(orderList.get(position).getCustomer());
            holder.priceProductOrder.setText(orderList.get(position).getPriceProductOrder());
            holder.priceOrder.setText(orderList.get(position).getPriceOrder());
            holder.nameProductOrder.setText(orderList.get(position).getNameProductOrder());
//        holder.ivFlashSale.setImageBitmap(function.StringBitMap(flashsaleList.get(position).getImageFlashSale()));
//        holder.discountFlashSale.setText(flashsaleList.get(position).getDiscountFlashSale());
            Glide.with(context)
                    .load(orderList.get(position).getImageOrder())
                    .into(holder.ivProduct);
        }else{
            holder.root_order.setVisibility(View.GONE);
        }

        holder.root_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               clickToOrder.ClickToOrder(orderList, holder.getAdapterPosition());
            }
        });
//        holder.rootFlashSale.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                clickToProductSale.onClickToProductSale(flashsaleList, holder.getAdapterPosition());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    } //quan trong

    public class OrderHoder extends RecyclerView.ViewHolder{
        private TextView idProductOrder, customer,  seller, priceOrder, priceProductOrder, numberOfOrder, nameProductOrder, statusOrder, dateOrder;
        private ImageView ivProduct;
        private LinearLayout root_order;
        public OrderHoder(@NonNull View itemView) {
            super(itemView);

            idProductOrder = itemView.findViewById(R.id.txtIdOrder);
            customer = itemView.findViewById(R.id.txtCutomer);
//            seller = itemView.findViewById(R.id.txtSeller);
            priceProductOrder = itemView.findViewById(R.id.txtPriceProductOrder);
            priceOrder = itemView.findViewById(R.id.tvProductPrice_pending);
            nameProductOrder = itemView.findViewById(R.id.txtNameProductOrder);
            ivProduct = itemView.findViewById(R.id.ivAvtProduct_pending);
            root_order = itemView.findViewById(R.id.root_order);
        }
    }
}