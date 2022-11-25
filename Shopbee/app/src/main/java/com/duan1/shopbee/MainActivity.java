package com.duan1.shopbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.duan1.shopbee.databinding.ActivityMainBinding;
import com.duan1.shopbee.fragment.HomeFragment;
import com.duan1.shopbee.fragment.LiveFragment;
import com.duan1.shopbee.fragment.MallFragment;
import com.duan1.shopbee.fragment.NotificationFragment;
import com.duan1.shopbee.fragment.ProfileFragment;
import com.duan1.shopbee.model.Category;
import com.duan1.shopbee.model.Flashsale;
import com.duan1.shopbee.model.LiveMain;
import com.duan1.shopbee.model.LiveStories;
import com.duan1.shopbee.model.LiveVoucher;
import com.duan1.shopbee.model.Profile;
import com.duan1.shopbee.slide_image.LivePhoto;
import com.duan1.shopbee.slide_image.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<Category> categoryList;
    List<Flashsale> flashsaleList;

    List<LiveStories> liveStoriesList;
    List<LiveVoucher> liveVoucherList;
    List<LiveMain> liveMainList;
    List<LivePhoto> livePhotoList;
    private List<Photo> listPhoto;

    List<Profile> mProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Home
        categoryList = new ArrayList<>();
        flashsaleList = new ArrayList<>();
        flashsaleList.add(new Flashsale("hehe", "hehe", "hehe", "hehe"));
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
        menu.findItem(R.id.bnHome).setIcon(R.drawable.home_4);
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

        loadFragmentHome();
        readFireStoreCategory();
        readFireStoreLiveStories();
        readFireStoreLiveVoucher();
        readFireStoreLiveMain();

    }

    public void loadFragmentHome() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, HomeFragment.newInstance(categoryList, flashsaleList, listPhoto), "MainFragment")
                .commit();
    }

    public void loadFragmentMall() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, MallFragment.newInstance("hehe", "hihi"), "MainFragment")
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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, ProfileFragment.newInstance(mProfiles, "hihi"), "MainFragment")
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



}