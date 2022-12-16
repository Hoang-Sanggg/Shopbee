package com.duan1.shopbee.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duan1.shopbee.Cart;
import com.duan1.shopbee.DetailSearchActivity;
import com.duan1.shopbee.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartsSearchAdapter extends RecyclerView.Adapter<CartsSearchAdapter.CartViewHolder> implements Filterable {

    private List<Cart> mListCart;
    private List<Cart> mListCartold;
    private Context mContext;

    public CartsSearchAdapter(Context context, List<Cart> mListCart){
        this.mContext = context;
        this.mListCart = mListCart;
        this.mListCartold = mListCart;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        return new CartViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        final Cart cart = mListCart.get(position);
        if (cart == null){
            return;
        }

        holder.imgCart.setImageResource(cart.getImage());
        holder.tvName.setText(cart.getName());
        holder.tvAddress.setText(cart.getAddress());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDeteil(cart);
            }
        });
    }
    private void onClickGotoDeteil(Cart cart) {
        Intent intent = new Intent(mContext, DetailSearchActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_cart", (Serializable) cart);
        intent.putExtras(bundle);
        mContext.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        if (mListCart != null){
            return mListCart.size();
        }
        return 0;
    }



    public class CartViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout layoutItem;
        private CircleImageView imgCart;
        private TextView tvName;
        private TextView tvAddress;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCart = itemView.findViewById(R.id.img_user);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            layoutItem = itemView.findViewById(R.id.layout_item);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mListCart = mListCartold;
                }else {
                    List<Cart> list = new ArrayList<>();
                    for (Cart cart: mListCartold){
                        if (cart.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(cart);
                        }
                    }

                    mListCart = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListCart;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListCart = (List<Cart>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
