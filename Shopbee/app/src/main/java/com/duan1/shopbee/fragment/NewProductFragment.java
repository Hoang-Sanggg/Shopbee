package com.duan1.shopbee.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duan1.shopbee.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewProductFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final int REQUESTCODE_READ_EXTERNAL_STORAGE = 120;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imageView;
    String linkDL;

    final String[] c = new String[1];

    public NewProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewProductFragment newInstance(String param1, String param2) {
        NewProductFragment fragment = new NewProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        return inflater.inflate(R.layout.fragment_new_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText tvNameProduct = view.findViewById(R.id.edtNewNameProduct);
        EditText tvDecription = view.findViewById(R.id.edtNewDescription);
        LinearLayout edtindustry = view.findViewById(R.id.edtindustry);
        imageView = view.findViewById(R.id.ivNewProduct1);
        Button button = view.findViewById(R.id.btn_addProduct);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String nameProduct = tvNameProduct.getText().toString();
                String decription = tvDecription.getText().toString();
                String maSp = RandomMaDonHang(9);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("nameProduct").setValue(nameProduct);
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("description").setValue(decription);
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("industry").setValue(c[0]);
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("priceProduct").setValue("5");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("productdetail").setValue("6");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("warehouse").setValue("7");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("transportfee").setValue("8");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("status").setValue("9");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("nameShop").setValue("10");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("soldProduct").setValue("11");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("brandProduct").setValue("12");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("originProduct").setValue("13");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("baoHanhSp").setValue("15");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("shippingProduct").setValue("15");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("brandProduct").setValue("16");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("priceFlashSale").setValue("17");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("discountFlashSale").setValue("18");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("soldFlashSale").setValue("18");
                        databaseReference.child("product").child("nameShop").child("productShop").child(maSp).child("imageProduct").setValue(linkDL);
                        Toast.makeText(getContext() , "Thêm thành công", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

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
                String[] types = {"By Zip", "By Category"};
                dialogIndustry.setItems(types, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch(which){
                            case 0:
                                 dialog_catelory(view, "By Zip", new String[]{"By Zip", "By Category"});
                                break;
                            case 1:
                                 dialog_catelory(view, "By Category", new String[]{"By Zip", "By Category"});
                                break;
                        }
                    }
                });
                dialogIndustry.show();
            }

        });

    }

    private void dialog_catelory(View view,String title, String[] list){
        AlertDialog.Builder dialogIndustry = new AlertDialog.Builder(getContext());
        dialogIndustry.setTitle(title);
        String[] types = list;
        final String[] category = {""};
        dialogIndustry.setItems(types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                switch(which){
                    case 0:
                        c[0] = types[which];
                        Toast.makeText(getContext(), types[which], Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        c[0] = types[which];
                        Toast.makeText(getContext(), types[which], Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        dialogIndustry.show();
    }
    public static String RandomMaDonHang(int len)
    {
        // ASCII range – alphanumeric (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // each iteration of the loop randomly chooses a character from the given
        // ASCII range and appends it to the `StringBuilder` instance

        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
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