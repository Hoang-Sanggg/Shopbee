package com.duan1.shopbee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.duan1.shopbee.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

        binding.bottomNavigation.setItemIconTintList(null);

        binding.bottomNavigation.setItemTextColor(ColorStateList.valueOf(Color.BLACK));

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bnHome:
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bnMall:
                    Toast.makeText(this, "Mall", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bnLive:
                    Toast.makeText(this, "Live", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bnNotification:
                    Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bnProfile:
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return true;
        });

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