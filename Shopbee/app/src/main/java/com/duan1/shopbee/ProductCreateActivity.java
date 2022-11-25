package com.duan1.shopbee;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.duan1.shopbee.model.ProductCreate;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.FirestoreClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCreateActivity extends AppCompatActivity {
    ProductCreate productCreate;
    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    CollectionReference collectionReference = db.collection("product");
    DocumentReference docref = db.collection("product").document();
    TextView textviewdata;
    Button btnPush,btnPull, btnPullbyID;
    long maxId = 0;
    EditText edtProductName, edtProductDetail, edtIndustry, edtDescription, edtStatus, edtTransportfee, edtWarehouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_create);




        edtProductName = findViewById(R.id.edtProductName);
        edtProductDetail = findViewById(R.id.edtProductDetail);
        edtIndustry = findViewById(R.id.edtIndustry);
        edtDescription = findViewById(R.id.edtDescription);
        edtStatus = findViewById(R.id.edtStatus);
        edtTransportfee = findViewById(R.id.edtTransportfee);
        edtWarehouse = findViewById(R.id.edtWarehouse);
        textviewdata = findViewById(R.id.txtViewData);
        btnPush = findViewById(R.id.btnPush);
        btnPull = findViewById(R.id.btnPull);
        btnPullbyID = findViewById(R.id.btnPullbyID);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductDatatoFirestore();
            }
        });
        btnPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readProductDatafromFirestore();
            }
        });
        btnPullbyID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readProductDatafromFirestorebyID();
            }
        });


    }

    public void addProductDatatoFirestore() {


//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    maxId = (snapshot.getChildrenCount());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        productCreate = new ProductCreate();

        Map<String, Object> product = new HashMap<>();
        String name = edtProductName.getText().toString();
        String detail = edtProductDetail.getText().toString();
        String industry = edtIndustry.getText().toString();
        String description = edtDescription.getText().toString();
        String status = edtStatus.getText().toString();
        String transportfee = edtTransportfee.getText().toString();
        String warehouse = edtWarehouse.getText().toString();
        product.put("name", name);
        product.put("detail", detail);
        product.put("industry", industry);
        product.put("description", description);
        product.put("status", status);
        product.put("warehouse", warehouse);
        product.put("transportfee", transportfee);

        //   Toast.makeText(ProductCreateActivity.this, "Tạo sản phẩm thành công", Toast.LENGTH_LONG).show();

        db.collection("product")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }

                });
    }

    public void readProductDatafromFirestorebyID(){
        db.collection("product")
                .whereEqualTo("id","01")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.getString("name");
                                String industry = document.getString("industry");
                                String description = document.getString("description");
                                String detail = document.getString("detail");
                                String status = document.getString("status");
                                String warehouse = document.getString("warehouse");
                                String transportfee = document.getString("transportfee");
                                String id = document.getString("id");
                                textviewdata.setText("id: " + id + "\n" + "name: "+ name + "\n" + "industry: " + industry + "\n" + "description: " + description + "\n" + "detail: " + detail + "\n" + "status: " + status + "\n" + "warehouse: " + warehouse + "\n" + "transportfee: " + transportfee);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    public void readProductDatafromFirestore() {
//        DocumentReference docref = db.collection("product").document("abc");
        db.collection("product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.getString("name");
                                String industry = document.getString("industry");
                                String description = document.getString("description");
                                String detail = document.getString("detail");
                                String status = document.getString("status");
                                String warehouse = document.getString("warehouse");
                                String transportfee = document.getString("transportfee");
                                String id = document.getString("id");
                                textviewdata.setText("id: " + id + "\n" + "name: "+ name + "\n" + "industry: " + industry + "\n" + "description: " + description + "\n" + "detail: " + detail + "\n" + "status: " + status + "\n" + "warehouse: " + warehouse + "\n" + "transportfee: " + transportfee);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

//     counters/${ID}
//    public class Counter {
//        int numShards;
//
//        public Counter(int numShards) {
//            this.numShards = numShards;
//        }
//    }
//
//    counters/${ID}/shards/${NUM}
//    public class Shard {
//        int count;
//
//        public Shard(int count) {
//            this.count = count;
//        }
//    }
//
//    public Task<Void> createCounter(final DocumentReference ref, final int numShards) {
//        // Initialize the counter document, then initialize each shard.
//        return ref.set(new Counter(numShards))
//                .continueWithTask(new Continuation<Void, Task<Void>>() {
//                    @Override
//                    public Task<Void> then(@NonNull Task<Void> task) throws Exception {
//                        if (!task.isSuccessful()) {
//                            throw task.getException();
//                        }
//
//                        List<Task<Void>> tasks = new ArrayList<>();
//
//                        // Initialize each shard with count=0
//                        for (int i = 0; i < numShards; i++) {
//                            Task<Void> makeShard = ref.collection("shards")
//                                    .document(String.valueOf(i))
//                                    .set(new Shard(0));
//
//                            tasks.add(makeShard);
//                        }
//
//                        return Tasks.whenAll(tasks);
//                    }
//                });
//    }
//
//    public Task<Void> incrementCounter(final DocumentReference ref, final int numShards) {
//        int shardId = (int) Math.floor(Math.random() * numShards);
//        DocumentReference shardRef = ref.collection("shards").document(String.valueOf(shardId));
//
//        return shardRef.update("count", FieldValue.increment(1));
//    }
}