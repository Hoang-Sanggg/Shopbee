package com.duan1.shopbee.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.ProductCreate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMyProductAdapter extends RecyclerView.Adapter<AddMyProductAdapter.MyProductViewHodel> {

    private Context mContext;
    private List<ProductCreate> mListMyProduct;
    private ClickToProductSale clickToProduct;

    public AddMyProductAdapter(Context mContext) {
        this.mContext = mContext;
    }


    SharedPreferences sharedPref;


    public AddMyProductAdapter(Context mContext, List<ProductCreate> mListMyProduct, ClickToProductSale clickToProduct) {
        this.mContext = mContext;
        sharedPref = this.mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        this.mListMyProduct = mListMyProduct;
        this.clickToProduct = clickToProduct;
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
    public void onBindViewHolder(@NonNull MyProductViewHodel holder, @SuppressLint("RecyclerView") int position) {
        mFunction function = new mFunction();
        String nameUser = sharedPref.getString("username", "null");
        int soLuong = 0;
            if(mListMyProduct.get(position).getNameShop().equals(nameUser)==true){
                soLuong = sharedPref.getInt("soLuong", 0);
                soLuong+=1;
                holder.tvThongTinSP_My_Product.setText(mListMyProduct.get(position).getNameProduct());
                holder.txtGiaSP_My_Product.setText(mListMyProduct.get(position).getPriceProduct());
                holder.txtKhoHang_My_Product.setText(mListMyProduct.get(position).getWarehouse());
                holder.txtDaBan_My_Product.setText(mListMyProduct.get(position).getSoldProduct());
                /** holder.txtLuotThich_My_Product.setText(mListMyProduct.get(position).getLuotThich());
                 holder.txtLuotXem_My_Product.setText(mListMyProduct.get(position).getLuotThich()); */

                Glide.with(mContext)
                        .load(mListMyProduct.get(position).getImageProduct())
                        .into(holder.ivHinhSP_My_Product);

            }else{
                holder.item_lnMyProduct.setVisibility(View.GONE);
            }
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putInt("soLuong", soLuong);
        editor1.commit();

        holder.item_lnMyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToProduct.onClickToProductSale(mListMyProduct, holder.getAdapterPosition());
            }
        });
        holder.btnSua_My_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("user_info");
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.ivHinhSP_My_Product.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
                        .setExpanded(true,1100)
                        .create();

                View view1=dialogPlus.getHolderView();

                EditText uName= view1.findViewById(R.id.edtEditNameProduct);
                EditText uDescription= view1.findViewById(R.id.edtEditDescription);
                TextView uIndustry= view1.findViewById(R.id.txtEditIndustry);
                TextView uBrand= view1.findViewById(R.id.txtEditBrand);
                CountryCodePicker uCountry = view1.findViewById(R.id.Editcountry);
                TextView uBaoHanh = view1.findViewById(R.id.txtEditBaoHanh);
                EditText uPrice = view1.findViewById(R.id.edtEditPrice);
                EditText uStorage = view1.findViewById(R.id.edtEditStorage);
                TextView uShip = view1.findViewById(R.id.txtEditShip);
                TextView uStatus = view1.findViewById(R.id.txtEditStatus);
                Button uOrder = view1.findViewById(R.id.btnEditOrder);



                LinearLayout submit= view1.findViewById(R.id.btnSubmit);

                uName.setText(mListMyProduct.get(position).getNameProduct());
                uDescription.setText(mListMyProduct.get(position).getDescription());
                uIndustry.setText(mListMyProduct.get(position).getIndustry());
                uBrand.setText(mListMyProduct.get(position).getBrandProduct());
                uBaoHanh.setText(mListMyProduct.get(position).getBaoHanhSp());
                uPrice.setText(mListMyProduct.get(position).getPriceProduct());
                uStorage.setText(mListMyProduct.get(position).getWarehouse());
                uShip.setText(mListMyProduct.get(position).getShippingProduct());
                uStatus.setText(mListMyProduct.get(position).getStatus());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("name",uName.getText().toString());
                        map.put("description",uDescription.getText().toString());
                        map.put("industry",uIndustry.getText().toString());
                        map.put("brand", uBrand.getText().toString());
                        map.put("baohanh", uBaoHanh.getText().toString());
                        map.put("price", uPrice.getText().toString());
                        map.put("storage", uStatus.getText().toString());
                        map.put("ship", uShip.getText().toString());
                        map.put("status", uStatus.getText().toString());

//                        FirebaseDatabase.getInstance().getReference().child("students")
//                                .child(getRef(position).getKey()).updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void aVoid) {
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        dialogPlus.dismiss();
//                                    }
//                                });
                    }
                });
            }
        });

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
        private Button btnXoa_My_Product, btnSua_My_Product, btnThem_My_Product;
        private LinearLayout item_lnMyProduct;

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
            btnXoa_My_Product = itemView.findViewById(R.id.btnDel_My_Product);
            btnSua_My_Product = itemView.findViewById(R.id.btnEdit_My_Product);
            btnThem_My_Product = itemView.findViewById(R.id.btnThem_My_Product);

            item_lnMyProduct = itemView.findViewById(R.id.item_lnMyProduct);

        }
    }
}
