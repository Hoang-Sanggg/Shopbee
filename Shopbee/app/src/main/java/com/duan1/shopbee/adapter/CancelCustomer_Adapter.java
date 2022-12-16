package com.duan1.shopbee.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickNextStatus;
import com.duan1.shopbee.callback.ClickToOrder;
import com.duan1.shopbee.callback.SelectTab;
import com.duan1.shopbee.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CancelCustomer_Adapter extends RecyclerView.Adapter<CancelCustomer_Adapter.OrderHoder> {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");

    private List<Order> orderList;
    private Context context;
    private ClickToOrder clickToOrder;
    private ClickNextStatus clickNextStatus;
    private SelectTab selectTab;
//    private ClickToProductSale clickToProductSale;


    public CancelCustomer_Adapter(List<Order> orderList, Context context, ClickToOrder clickToOrder, ClickNextStatus clickNextStatus) {
        this.orderList = orderList;
        this.context = context;
        this.clickToOrder = clickToOrder;
        this.clickNextStatus = clickNextStatus;

    }

    @NonNull
    @Override
    public CancelCustomer_Adapter.OrderHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_product, parent, false);
        return new CancelCustomer_Adapter.OrderHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CancelCustomer_Adapter.OrderHoder holder, int position) {

        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String nameShopS = sharedPref.getString("username", "");

        if(String.valueOf(orderList.get(position).getSeller()).equals(nameShopS)==true && String.valueOf(orderList.get(position).getCancelCustomer()).equals("1")==true){
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
        holder.btnDeliveryPending.setText("Vận chuyển");
        holder.btnDeliveryPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("order").child(orderList.get(holder.getAdapterPosition()).getIdProductOrder()).child("cancelSeller").setValue("1");
                databaseReference.child("order").child(orderList.get(holder.getAdapterPosition()).getIdProductOrder()).child("cancelCustomer").setValue("0");
                clickNextStatus.onClickNextStatus(orderList, holder.getAdapterPosition(), 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    } //quan trong

    public class OrderHoder extends RecyclerView.ViewHolder{
        private TextView idProductOrder, customer,  seller, priceOrder, priceProductOrder, numberOfOrder, nameProductOrder, statusOrder, dateOrder;
        private ImageView ivProduct;
        private LinearLayout root_order;
        private Button btnDeliveryPending;
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

            btnDeliveryPending = itemView.findViewById(R.id.btnDeliveryPending);
        }
    }
}