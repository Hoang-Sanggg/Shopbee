package com.duan1.shopbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.duan1.shopbee.callback.HideBottomNav;
import com.duan1.shopbee.callback.ShowAddProduct;
import com.duan1.shopbee.callback.ShowBottomNav;
import com.duan1.shopbee.databinding.ActivityMainBinding;
import com.duan1.shopbee.fragment.FragmentProduct;
import com.duan1.shopbee.fragment.HomeFragment;
import com.duan1.shopbee.fragment.LiveFragment;
import com.duan1.shopbee.fragment.MallFragment;
import com.duan1.shopbee.fragment.NotificationFragment;
import com.duan1.shopbee.fragment.ProfileFragment;
import com.duan1.shopbee.model.Category;
import com.duan1.shopbee.model.CategoryMall;
import com.duan1.shopbee.model.Flashsale;
import com.duan1.shopbee.model.LiveMain;
import com.duan1.shopbee.model.LiveStories;
import com.duan1.shopbee.model.LiveVoucher;
import com.duan1.shopbee.model.User;
import com.duan1.shopbee.slide_image.LivePhoto;
import com.duan1.shopbee.model.ProductCreate;
import com.duan1.shopbee.slide_image.MallBanner;
import com.duan1.shopbee.model.Profile;
import com.duan1.shopbee.slide_image.LivePhoto;
import com.duan1.shopbee.slide_image.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements HideBottomNav, ShowBottomNav, ShowAddProduct {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");

    ActivityMainBinding binding;
    List<Category> categoryList;
    List<ProductCreate> flashsaleList;

    List<CategoryMall> categoryMallList;
    private List<MallBanner> mallBannerList;

    List<LiveStories> liveStoriesList;
    List<LiveVoucher> liveVoucherList;
    List<LiveMain> liveMainList;
    List<LivePhoto> livePhotoList;
    private List<Photo> listPhoto;
    private String name;
    List<Profile> mProfiles;

    LinearLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///




        ///

//        name = getIntent().getStringExtra("username");
//        if (name == null) {
//            name = LoginActivity.USERNAME;
//        }
        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String name = sharedPref.getString("username", "");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        //Home
        categoryList = new ArrayList<>();
        flashsaleList = new ArrayList<>();
        //Live
        liveMainList = new ArrayList<>();
        liveStoriesList = new ArrayList<>();
        liveVoucherList = new ArrayList<>();
        //profile
        mProfiles = new ArrayList<>();
        mProfiles.add(new Profile("hehe", "hehe"));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.getOrCreateBadge(R.id.bnNotification).setNumber(99);

        binding.bottomNavigation.setItemIconTintList(null);


        Menu menu = binding.bottomNavigation.getMenu();
        menu.findItem(R.id.bnHome).setIcon(R.drawable
                .home_4);
        menu.findItem(R.id.bnMall).setIcon(R.drawable.shopping_bag);
        menu.findItem(R.id.bnNotification).setIcon(R.drawable.notification);
        menu.findItem(R.id.bnLive).setIcon(R.drawable.video_camera);
        menu.findItem(R.id.bnProfile).setIcon(R.drawable.user);

        binding.bottomNavigation.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bnHome:
                    item.setIcon(R.drawable.home_4);
                    menu.findItem(R.id.bnMall).setIcon(R.drawable.shopping_bag);
                    menu.findItem(R.id.bnNotification).setIcon(R.drawable.notification);
                    menu.findItem(R.id.bnLive).setIcon(R.drawable.video_camera);
                    menu.findItem(R.id.bnProfile).setIcon(R.drawable.user);
                    loadFragmentHome();
                    break;
                case R.id.bnMall:
                    item.setIcon(R.drawable.shopping_bag_2);
                    menu.findItem(R.id.bnHome).setIcon(R.drawable.home);
                    menu.findItem(R.id.bnNotification).setIcon(R.drawable.notification);
                    menu.findItem(R.id.bnLive).setIcon(R.drawable.video_camera);
                    menu.findItem(R.id.bnProfile).setIcon(R.drawable.user);
                    loadFragmentMall();
                    break;
                case R.id.bnLive:
                    item.setIcon(R.drawable.video_player_2);
                    menu.findItem(R.id.bnHome).setIcon(R.drawable.home);
                    menu.findItem(R.id.bnNotification).setIcon(R.drawable.notification);
                    menu.findItem(R.id.bnMall).setIcon(R.drawable.shopping_bag);
                    menu.findItem(R.id.bnProfile).setIcon(R.drawable.user);
                    loadFragmentLive();
                    break;
                case R.id.bnNotification:
                    item.setIcon(R.drawable.notification_2);
                    menu.findItem(R.id.bnHome).setIcon(R.drawable.home);
                    menu.findItem(R.id.bnLive).setIcon(R.drawable.video_camera);
                    menu.findItem(R.id.bnLive).setIcon(R.drawable.video_camera);
                    menu.findItem(R.id.bnProfile).setIcon(R.drawable.user);
                    loadFragmentNotification();
                    break;
                case R.id.bnProfile:
                    item.setIcon(R.drawable.user_2);
                    menu.findItem(R.id.bnHome).setIcon(R.drawable.home);
                    menu.findItem(R.id.bnNotification).setIcon(R.drawable.notification);
                    menu.findItem(R.id.bnLive).setIcon(R.drawable.video_camera);
                    menu.findItem(R.id.bnNotification).setIcon(R.drawable.notification);
                    loadFragmentProfile();
                    break;
                default:
                    break;
            }
            return true;
        });
        readData();
        loadFragmentHome();
        readFireStoreCategory();
        readFireStoreLiveStories();
        readFireStoreLiveVoucher();
        readFireStoreLiveMain();
//        readFireStoreFlashSales();
//        addData();

    }

    public void loadFragmentHome() {
        Log.d(">>>", "loadFragmentHome: "+ flashsaleList.size());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, HomeFragment.newInstance(categoryList, flashsaleList, listPhoto, this, this), "MainFragment")
                .commit();
    }

    public void loadFragmentMall() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, MallFragment.newInstance(categoryMallList,flashsaleList, mallBannerList, this, this), "MainFragment")
                .commit();
    }

    public void loadFragmentLive() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, LiveFragment.newInstance(liveStoriesList, liveVoucherList, liveMainList,livePhotoList), "LiveFragment")
                .commit();
    }

    public void loadFragmentNotification() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, NotificationFragment.newInstance("hehe", "hihi"), "MainFragment")
                .commit();
    }

    public void loadFragmentProfile() {
        Log.d(">>>", "loadFragmentProfile: "+ flashsaleList.size());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, ProfileFragment.newInstance(mProfiles, flashsaleList, this, this), "MainFragment")
                .commit();
    }


    public void readFireStoreCategory() {
        FirebaseFirestore.getInstance()
                .collection("category")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : snapshots) {
                            categoryList.add(new Category(snapshot.getString("nameCategory"), snapshot.getString("imageCategory")));
                        }
                        loadFragmentHome();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("user", "khong tim ra ");
                    }
                });
    }

    public void readFireStoreFlashSales() {
        FirebaseFirestore.getInstance()
                .collection("product")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : snapshots) {
                                flashsaleList.add(new ProductCreate(snapshot.getString("idProduct"),snapshot.getString("nameProduct"),snapshot.getString("description"),snapshot.getString("industry"),snapshot.getString("priceProduct"),snapshot.getString("productdetail"),snapshot.getString("wirehouse"),snapshot.getString("transportfree"),snapshot.getString("status"),snapshot.getString("nameShop"),snapshot.getString("soldProduct"),snapshot.getString("brandProduct"),snapshot.getString("originProduct"),snapshot.getString("baoHanhSP"),snapshot.getString("shippingProduct"),snapshot.getString("priceFlashSale"),snapshot.getString("discountFlashSale"),snapshot.getString("soldFlashSale"),snapshot.getString("imageProduct")));
                           // Toast.makeText(MainActivity.this, ""+snapshot.getString("idProduct")+snapshot.getString("description")+snapshot.getString("industry")+snapshot.getString("priceProduct")+snapshot.getString("productdetail")+snapshot.getString("wirehouse")+snapshot.getString("transportfree")+snapshot.getString("status")+snapshot.getString("nameShop")+snapshot.getString("soldProduct")+snapshot.getString("brandProduct")+snapshot.getString("originProduct")+snapshot.getString("baoHanhSP")+snapshot.getString("shippingProduct")+snapshot.getString("priceFlashSale")+snapshot.getString("discountFlashSale")+snapshot.getString("soldFlashSale")+snapshot.getString("imageProduct"), Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("user", "khong tim ra ");
                    }
                });


    }

    public void readFireStoreLiveStories() {
        FirebaseFirestore.getInstance()
                .collection("livestories")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : snapshots) {
                            liveStoriesList.add(new LiveStories(snapshot.getString("imageLiveStories"), snapshot.getString("nameLiveStories")));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("user", "khong tim ra ");
                    }
                });


    }
    public void readFireStoreLiveVoucher() {
        FirebaseFirestore.getInstance()
                .collection("livevoucher")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : snapshots) {
                            liveVoucherList.add(new LiveVoucher(snapshot.getString("imageLiveVoucher"), snapshot.getString("nameLiveVoucher")));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("user", "khong tim ra ");
                    }
                });


    }
    public void readFireStoreLiveMain() {
        FirebaseFirestore.getInstance()
                .collection("livemain")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot snapshot : snapshots) {
                            liveMainList.add(new LiveMain(snapshot.getString("userimageLiveMain"), snapshot.getString("imageLiveMain"), snapshot.getString("usernameLiveMain"), snapshot.getString("descriptionLiveMain")));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("user", "khong tim ra ");
                    }
                });
    }


    @Override
    public void hideBottomNav() {
        toolbar = findViewById(R.id.linearLayout1);
        binding.bottomNavigation.setVisibility(View.INVISIBLE);
        toolbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showBottomNav() {
        binding.bottomNavigation.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.linearLayout1);
        toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddProduct() {
        binding.bottomNavigation.setVisibility(View.VISIBLE);
    }



    private void readData(){

        databaseReference.child("product").child("nameShop").child("productShop").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flashsaleList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    ProductCreate productCreate = postSnapshot.getValue(ProductCreate.class);
                    flashsaleList.add(productCreate);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}