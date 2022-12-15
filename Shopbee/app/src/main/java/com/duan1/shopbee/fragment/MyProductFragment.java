package com.duan1.shopbee.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.duan1.shopbee.R;
import com.duan1.shopbee.adapter.AddMyProductAdapter;
import com.duan1.shopbee.adapter.CategoryAdapter;
import com.duan1.shopbee.callback.ClickToDeleteProduct;
import com.duan1.shopbee.callback.ClickToProductSale;
import com.duan1.shopbee.callback.ClickToUpdateProduct;
import com.duan1.shopbee.callback.HideBottomNav;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.model.ProductCreate;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hbb20.CountryCodePicker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MyProductFragment extends Fragment implements ClickToProductSale, ClickToDeleteProduct, ClickToUpdateProduct {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText tvNameProduct;
    EditText tvDecription;
    ImageView imageView;
    String linkDL, phiVanChuyen;
    TextView txtNewIndustry, txtNewBrand, txtStatus, txtBaoHanh, txtShip, back_new_pro;
    LinearLayout edtindustry, edtBrand, edtBaoHanh, edtStatus, edtTransfree;
    EditText  edtPrice, edtStorage;
    CountryCodePicker countryCodePicker;

    final String[] industry = new String[1];

    TextView txtSoLuong;
    private List<ProductCreate> productCreateList;

    private AddMyProductAdapter addMyProductAdapter;

    private RecyclerView rcyMyProduct;
    Button btnDelete;

    final int REQUESTCODE_READ_EXTERNAL_STORAGE = 120;


    public MyProductFragment() {
    }

    public MyProductFragment(List<ProductCreate> productCreateList) {
        this.productCreateList = productCreateList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProductFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static MyProductFragment newInstance(String param1, String param2, ) {
//        MyProductFragment fragment = new MyProductFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView btn_back_myProduct = view.findViewById(R.id.btn_back_myProduct);

//        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//        int soLuong = sharedPref.getInt("soLuong", 0);

        rcyMyProduct = view.findViewById(R.id.rcyMyProduct);

        Button btnAdd = view.findViewById(R.id.btn_addNewProduct);

        txtSoLuong = view.findViewById(R.id.txtSoLuong);


        btnDelete = view.findViewById(R.id.btnAn_My_Product);

//        txtSoLuong.setText("( "+String.valueOf(soLuong)+" )");
//
//        Log.d(">>>>>>", "size: "+soLuong);

//        txtSoLuong.setText("( "+String.valueOf(soLuong)+" )");
//
//        Log.d(">>>>>>", "size: "+soLuong);


        loadData();

        btn_back_myProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAddNewMyProduct(view);
            }
        });

       




    }
    public void onClickAddNewMyProduct(View view) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new NewProductFragment(), "MainFragment")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onClickToProductSale(List<ProductCreate> flashsaleList, int position) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new FragmentProduct(flashsaleList.get(position).getIdProduct(),flashsaleList.get(position).getNameProduct(), flashsaleList.get(position).getDescription(), flashsaleList.get(position).getIndustry(), flashsaleList.get(position).getPriceProduct(), flashsaleList.get(position).getProductdetail(), flashsaleList.get(position).getWarehouse(),flashsaleList.get(position).getTransportfee(), flashsaleList.get(position).getStatus(), flashsaleList.get(position).getNameShop(), flashsaleList.get(position).getSoldProduct(), flashsaleList.get(position).getBrandProduct(), flashsaleList.get(position).getOriginProduct(), flashsaleList.get(position).getBaoHanhSp(), flashsaleList.get(position).getShippingProduct(), flashsaleList.get(position).getPriceFlashSale(), flashsaleList.get(position).getDiscountFlashSale(), flashsaleList.get(position).getSoldFlashSale(), flashsaleList.get(position).getImageProduct()), "MainFragment")
                .addToBackStack(null)
                .commit();
    }

    public void onClickToDeleteProduct(List<ProductCreate> flashsalelist,int position){
        databaseReference.child("product").child(String.valueOf(flashsalelist.get(position).getIdProduct())).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Bạn vừa xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });
    }

    // updata nè





    private void loadData(){
        rcyMyProduct.setHasFixedSize(true);
        rcyMyProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        addMyProductAdapter = new AddMyProductAdapter(getContext(), productCreateList, MyProductFragment.this,MyProductFragment.this, MyProductFragment.this);
        rcyMyProduct.setAdapter(addMyProductAdapter);
    }

    @Override
    public void onClickToUpdateroduct(List<ProductCreate> flashsaleList, int position) {
        final Dialog dialogEr = new Dialog(getContext());
        dialogEr.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEr.setCancelable(true);
        dialogEr.setContentView(R.layout.dialog_update_product);


        tvNameProduct = dialogEr.findViewById(R.id.edtNewNameProduct);
        tvDecription = dialogEr.findViewById(R.id.edtNewDescription);


        edtindustry = dialogEr.findViewById(R.id.edtindustry);
        edtBrand = dialogEr.findViewById(R.id.lnNewBrand);
        edtBaoHanh = dialogEr.findViewById(R.id.lnBaoHanhSp);
        edtStatus = dialogEr.findViewById(R.id.lnNewStatus);
        edtTransfree =dialogEr.findViewById(R.id.lnTransportfee);

        countryCodePicker = dialogEr.findViewById(R.id.country);
        back_new_pro = dialogEr.findViewById(R.id.back_new_pro);



        edtPrice = dialogEr.findViewById(R.id.edtNewPrice);
        edtStorage = dialogEr.findViewById(R.id.edtNewStorage);

        imageView = dialogEr.findViewById(R.id.ivNewProduct1);
        txtNewIndustry = dialogEr.findViewById(R.id.txtNewIndustry);
        txtNewBrand = dialogEr.findViewById(R.id.txtBrand);
        txtBaoHanh = dialogEr.findViewById(R.id.txtBaoHanh);
        txtStatus = dialogEr.findViewById(R.id.txtNewStatus);
        txtShip = dialogEr.findViewById(R.id.txtShip);


        Glide.with(getContext())
                .load(flashsaleList.get(position).getImageProduct())
                .into(imageView);

        tvNameProduct.setText(flashsaleList.get(position).getName());
        tvDecription.setText(flashsaleList.get(position).getDescription());
        txtNewIndustry.setText(flashsaleList.get(position).getIndustry());
        txtBaoHanh.setText(flashsaleList.get(position).getBaoHanhSp());
        txtStatus.setText(flashsaleList.get(position).getStatus());
        txtShip.setText(flashsaleList.get(position).getShippingProduct());
        txtNewBrand.setText(flashsaleList.get(position).getBrandProduct());
        edtPrice.setText(flashsaleList.get(position).getPriceProduct());
        edtStorage.setText(flashsaleList.get(position).getWarehouse());



        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                onSelectPicture(view);
            }
        });

        edtindustry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogIndustry = new AlertDialog.Builder(getContext());
                dialogIndustry.setTitle("Ngành hàng");
                String[] types = {"Thời trang nữ", "Thời trang nam", "Điện thoại và phụ kiện", ""};
                dialogIndustry.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch(which){
                            case 0:
                                dialog_catelory(view, "Thời trang nữ", new String[]{"Áo", "Trang sức","Giày","Quần"}, txtNewIndustry);
                                break;
                            case 1:
                                dialog_catelory(view, "Thời trang nam", new String[]{"Áo", "Phụ kiện", "Giày", "Nón"}, txtNewIndustry);
                                break;
                            case 2:
                                dialog_catelory(view, "Điện thoại và phụ kiện", new String[]{"Cáp sạc", "Củ sạc", "Iphone", "SamSung","Tai Nghe"}, txtNewIndustry);
                                // them case
                        }
                    }
                });
                dialogIndustry.show();
            }

        });

        edtBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogIndustry = new AlertDialog.Builder(getContext());
                dialogIndustry.setTitle("Thương hiệu");
                String[] types = {"Adidas", "Nike","Akko","Frenzy"};
                dialogIndustry.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch(which){
                            case 0:
                                industry[0] = types[which];
                                break;
                            case 1:
                                dialog_catelory(view, "Adidas", new String[]{"Giày", "Phụ kiện", "Áo", "Quần"}, txtNewBrand);
                                break;
                            case 2:
                                dialog_catelory(view, "Nike", new String[]{"Giày", "Phụ kiện", "Áo", "Quần"}, txtNewBrand);
                                break;
                            case 3:
                                dialog_catelory(view, "Akko", new String[]{"Bàn Phím", "Phụ kiện", "Keycap", "Kit"}, txtNewBrand);
                                break;
                            case 4:
                                dialog_catelory(view, "Frenzy", new String[]{"Giày", "Phụ kiện", "Áo", "Quần"}, txtNewBrand);
                                break;


                            // them case
                        }
                        txtNewBrand.setText(String.valueOf(industry[0]));
                    }

                });
                dialogIndustry.show();
            }
        });


        edtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_catelory(view, "Tình trạng hàng hóa", new String[]{"Mới", "Đã qua sử dụng"}, txtStatus);
            }
        });

        edtBaoHanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_catelory(view, "Thời gian bảo hành", new String[]{"Không bảo hành","1 tháng", "3 tháng", "6 tháng", "1 năm", "2 năm"}, txtBaoHanh);
            }
        });

        edtTransfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog(Gravity.CENTER);
            }
        });

        back_new_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askBack();
            }
        });







        Button button = dialogEr.findViewById(R.id.btn_addProduct);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameProduct = tvNameProduct.getText().toString();
                String decription = tvDecription.getText().toString();
                String maSp = flashsaleList.get(position).getIdProduct();
                String Status = txtStatus.getText().toString();
                String BaoHanh = txtBaoHanh.getText().toString();
                String price = edtPrice.getText().toString();
                String storage = edtStorage.getText().toString();
                String origin = countryCodePicker.getSelectedCountryName();

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("product").child(maSp).child("nameProduct").setValue(nameProduct);
                        databaseReference.child("product").child(maSp).child("description").setValue(decription);
                        databaseReference.child("product").child(maSp).child("industry").setValue(txtNewIndustry.getText());
                        databaseReference.child("product").child(maSp).child("priceProduct").setValue(price);
                        databaseReference.child("product").child(maSp).child("productdetail").setValue("6");
                        databaseReference.child("product").child(maSp).child("warehouse").setValue(storage);
                        databaseReference.child("product").child(maSp).child("transportfee").setValue(phiVanChuyen);
                        databaseReference.child("product").child(maSp).child("status").setValue(Status);
                        databaseReference.child("product").child(maSp).child("soldProduct").setValue("11");
                        databaseReference.child("product").child(maSp).child("brandProduct").setValue(txtNewBrand.getText());
                        databaseReference.child("product").child(maSp).child("originProduct").setValue(origin);
                        databaseReference.child("product").child(maSp).child("baoHanhSp").setValue(BaoHanh);
                        databaseReference.child("product").child(maSp).child("shippingProduct").setValue("15");
                        databaseReference.child("product").child(maSp).child("priceFlashSale").setValue(price);
                        databaseReference.child("product").child(maSp).child("discountFlashSale").setValue("18");
                        databaseReference.child("product").child(maSp).child("soldFlashSale").setValue("18");
                        databaseReference.child("product").child(maSp).child("imageProduct").setValue(linkDL);
                        databaseReference.child("product").child(maSp).child("idProduct").setValue(maSp);

//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("nameProduct").setValue(nameProduct);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("description").setValue(decription);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("industry").setValue(txtNewIndustry.getText());
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("priceProduct").setValue("5");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("productdetail").setValue("6");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("warehouse").setValue(storage);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("transportfee").setValue("8");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("status").setValue(Status);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("nameShop").setValue(name);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("soldProduct").setValue("11");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("brandProduct").setValue(txtNewBrand.getText());
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("originProduct").setValue("13");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("baoHanhSp").setValue(BaoHanh);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("shippingProduct").setValue("15");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("priceFlashSale").setValue(price);
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("discountFlashSale").setValue("18");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("soldFlashSale").setValue("18");
//                        databaseReference.child("product").child(name).child("productShop").child(maSp).child("imageProduct").setValue(linkDL);

                        Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        dialogEr.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



        Window window = dialogEr.getWindow();
        if (window==null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);


        dialogEr.show();

    }

    private void askBack(){
        android.app.AlertDialog.Builder b = new android.app.AlertDialog.Builder(getContext());
        b.setIcon(R.drawable.attention_warning_14525);
        b.setTitle("Xác nhận");
        b.setMessage("Bạn có chắc chắn muốn hủy không ?");
        b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
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

    private void dialog_catelory(View view,String title, String[] list, TextView textView){
        AlertDialog.Builder dialogIndustry = new AlertDialog.Builder(getContext());
        dialogIndustry.setTitle(title);
        String[] types = list;
        dialogIndustry.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch(which){
                    case 0:
                        industry[0] = types[which];
                        break;
                    case 1:
                        industry[0] = types[which];
                        break;
                    case 2:
                        industry[0] = types[which];
                        break;
                    case 3:
                        industry[0] = types[which];
                        break;
                }
                textView.setText(String.valueOf(industry[0]));
            }
        });
        dialogIndustry.show();

    }

    private void dialog(int gravity){
        final Dialog dialog1 = new Dialog(getContext());
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.layout_copymdh);

        Window window = dialog1.getWindow();
        if (window==null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);

        if (Gravity.CENTER == gravity){
            dialog1.setCancelable(true);
        }else{

        }

        Button btnTamTinh = dialog1.findViewById(R.id.btnTamTinh);
        TextView txtTamTinh = dialog1.findViewById(R.id.txtPhiVanChuyen);
        EditText edtTrongLuong = dialog1.findViewById(R.id.edtTrongLuong);
        Button btnAddTransfree = dialog1.findViewById(R.id.btnSucess);

        btnTamTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trongLuong = edtTrongLuong.getText().toString();
                txtTamTinh.setText(transFree(trongLuong));
            }
        });

        btnAddTransfree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trongLuong = edtTrongLuong.getText().toString();
                txtShip.setText(transFree(trongLuong));
                phiVanChuyen = transFree(trongLuong);
                dialog1.dismiss();
            }
        });


        dialog1.show();
    }

    private String transFree(String khoiLuong){
        String transFreeString = "";
        if(Integer.parseInt(khoiLuong) < 500){
            transFreeString += "15000";
        }else if(Integer.parseInt(khoiLuong) > 500 && Integer.parseInt(khoiLuong) < 1000 ){
            transFreeString += "25000";
        }else{
            transFreeString += "35000";
        }
        return transFreeString;
    }

    private void readData(){

        databaseReference.child("product").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productCreateList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ProductCreate productCreate = postSnapshot.getValue(ProductCreate.class);
                    productCreateList.add(productCreate);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSelectPicture(View view) {
        Boolean isPermissionAllowed = ActivityCompat.checkSelfPermission(
                getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (isPermissionAllowed) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            selectCapture.launch(intent);
        } else {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUESTCODE_READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUESTCODE_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    selectCapture.launch(intent);
                }
                break;
            }
            default:
                break;
        }
    }


    ActivityResultLauncher<Intent> selectCapture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    Uri uri = intent.getData();
                    Bitmap bitmap;
                    try (InputStream inputStream = getContext().getContentResolver().openInputStream(uri)) {
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.setImageBitmap(bitmap);
                        uploadToFirebase(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    private void uploadToFirebase(Bitmap bitmap){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference imgaeReference = storageReference.child(Calendar.getInstance().getTimeInMillis()+ ".jpg");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] bytes = outputStream.toByteArray();
        UploadTask uploadTask = imgaeReference.putBytes(bytes);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (task.isSuccessful()){
                    return imgaeReference.getDownloadUrl();
                }
                return null;
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri dowloadUri = task.getResult();
                    linkDL = dowloadUri +"";
                }
            }
        });
    }

}