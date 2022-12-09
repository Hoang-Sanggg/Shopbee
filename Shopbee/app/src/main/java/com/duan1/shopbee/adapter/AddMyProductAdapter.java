package com.duan1.shopbee.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.callback.ClickToDeleteProduct;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.fragment.NewProductFragment;
import com.duan1.shopbee.function.mFunction;
import com.duan1.shopbee.model.ProductCreate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMyProductAdapter extends RecyclerView.Adapter<AddMyProductAdapter.MyProductViewHodel> {

    private Context mContext;
    private List<ProductCreate> mListMyProduct;
    private ClickToProductSale clickToProduct;
    private ClickToDeleteProduct clickToDeleteProduct;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");


    public AddMyProductAdapter(Context mContext) {
        this.mContext = mContext;
    }


    SharedPreferences sharedPref;


    public AddMyProductAdapter(Context mContext, List<ProductCreate> mListMyProduct, ClickToProductSale clickToProduct, ClickToDeleteProduct clickToDeleteProduct) {
        this.mContext = mContext;
        sharedPref = this.mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        this.mListMyProduct = mListMyProduct;
        this.clickToProduct = clickToProduct;
        this.clickToDeleteProduct = clickToDeleteProduct;
//        notifyDataSetChanged();
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
//                soLuong = sharedPref.getInt("soLuong", 0);
//                soLuong+=1;
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
//        SharedPreferences.Editor editor1 = sharedPref.edit();
//        editor1.putInt("soLuong", soLuong);
//        editor1.commit();

        holder.item_lnMyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToProduct.onClickToProductSale(mListMyProduct, holder.getAdapterPosition());
            }
        });
        holder.btnXoa_My_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askBack(holder);
            }
        });
    }

    private void askBack(MyProductViewHodel holder){
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(mContext);
        b.setIcon(R.drawable.attention_warning_14525);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có chắc chắn muốn xóa không?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                clickToDeleteProduct.onClickToDeleteProduct(mListMyProduct,holder.getAdapterPosition());
            }
        });
        b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        android.app.AlertDialog al = b.create();
        al.show();
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
        private Button btnAn_My_Product, btnSua_My_Product, btnXoa_My_Product;
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
            btnAn_My_Product = itemView.findViewById(R.id.btnAn_My_Product);
            btnSua_My_Product = itemView.findViewById(R.id.btnEdit_My_Product);
            btnXoa_My_Product = itemView.findViewById(R.id.btnXoa);

            item_lnMyProduct = itemView.findViewById(R.id.item_lnMyProduct);

        }
    }
}
