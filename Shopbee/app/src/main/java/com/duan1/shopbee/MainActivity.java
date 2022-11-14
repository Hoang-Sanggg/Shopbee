package com.duan1.shopbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFireStoreUserStories();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigation.getOrCreateBadge(R.id.bnNotification).setNumber(99);

        binding.bottomNavigation.setItemIconTintList(null);

        binding.bottomNavigation.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bnHome:
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    loadFragmentHome();
                    break;
                case R.id.bnMall:
                    Toast.makeText(this, "Mall", Toast.LENGTH_SHORT).show();
                    loadFragmentMall();
                    break;
                case R.id.bnLive:
                    Toast.makeText(this, "Live", Toast.LENGTH_SHORT).show();
                    loadFragmentLive();
                    break;
                case R.id.bnNotification:
                    Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                    loadFragmentNotification();
                    break;
                case R.id.bnProfile:
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                    loadFragmentProfile();
                    break;
                default:
                    break;
            }
            return true;
        });

        loadFragmentHome();

    }

    public void loadFragmentHome() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, HomeFragment.newInstance("hehe", "hihi"), "MainFragment")
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
                .replace(R.id.frame_layout, LiveFragment.newInstance("hehe", "hihi"), "MainFragment")
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
                .replace(R.id.frame_layout, ProfileFragment.newInstance("hehe", "hihi"), "MainFragment")
                .commit();
    }


    public void readFireStoreUserStories(){
        FirebaseFirestore.getInstance()
                .collection("user")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots =  queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot :snapshots){
                            Log.d("user", "onSuccess: "+snapshot.getString("username"));
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