package com.duan1.shopbee;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.duan1.shopbee.model.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://shopbee-936e3-default-rtdb.firebaseio.com/");

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String linkDL = "";
    private ImageView imv_choose;
    private TextInputEditText   edt_username, edt_password, edt_enter_password, edt_email, edt_phone_number;
    private TextInputLayout til_username, til_password, til_enter_password, til_email, til_phone_number;
    private RadioButton rdo_male, rdo_female;
    private Button btn_cancel_register;
    private TextView txtRegister_Number_Phone;
    final int REQUESTCODE_READ_EXTERNAL_STORAGE = 120;
    private int dem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imv_choose = findViewById(R.id.btn_choose_avartar);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        edt_enter_password = findViewById(R.id.edt_enter_password);
        edt_email = findViewById(R.id.edt_email);
        edt_phone_number = findViewById(R.id.edt_phone_number);

        rdo_male = findViewById(R.id.rdo_male);
        rdo_female = findViewById(R.id.rdo_female);

        til_username = findViewById(R.id.TextInputLayOutUsername);
        til_password = findViewById(R.id.TextInputLayOutPassword);
        til_enter_password = findViewById(R.id.TextInputLayOutEnterPassword);
        til_email = findViewById(R.id.TextInputLayOutEmail);
        til_phone_number = findViewById(R.id.TextInputLayOutPhoneNumber);

        btn_cancel_register = findViewById(R.id.btn_cancel_register);
        btn_cancel_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        txtRegister_Number_Phone = findViewById(R.id.txtRegister_Number_Phone);
        txtRegister_Number_Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, RegisterNumberPhoneActivity.class));
            }
        });


        edt_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate_username();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate_password();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edt_enter_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate_enter_password();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate_email();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validate_phone_number();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    public void onClickRegister(View view){
        String username = edt_username.getText().toString();
        if(linkDL.equals("")){
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Notification")
                    .setMessage("Please choose an avatar")
                    .setPositiveButton("OK", null)
                    .show();
        }else{
            if(validate()){
                ArrayList<User> list = new ArrayList<>();
                db.collection("users")
                        .whereEqualTo("username", username)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Map<String, Object> map = document.getData();
                                        String username = map.get("username").toString();
                                        User user = new User(username);
                                        list.add(user);
                                    }
                                    Log.d("Lisst", list.size()+"");
                                    if (list.size() == 0) {
                                        String username = edt_username.getText().toString();
                                        String password = edt_password.getText().toString();
                                        String re_password = edt_enter_password.getText().toString();
                                        String email = edt_email.getText().toString();
                                        String phone_number = edt_phone_number.getText().toString();
                                        String link = linkDL;
                                        String gender = "";
                                        String gender_pet = "";
                                        if(rdo_male.isChecked()){
                                            gender = rdo_male.getText().toString();
                                        }else {
                                            gender = rdo_female.getText().toString();
                                        }



                                        // adddate realtime database

                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot. child("user").hasChild(phone_number)){
                                                    Toast.makeText(RegisterActivity.this, "Number phone is already", Toast.LENGTH_SHORT).show();
                                                }else{

                                                    databaseReference.child("user").child(username).child("email").setValue(email);
                                                    databaseReference.child("user").child(username).child("name").setValue(username);
                                                    databaseReference.child("user").child(username).child("avt").setValue(phone_number);
                                                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                        // adddate realtime database

                                        Map<String, Object> user = new HashMap<>();
                                        user.put("avatar", link);
                                        user.put("email", email);
                                        user.put("gender", gender);
                                        user.put("kycCode", "0");
                                        user.put("latitude", 0);
                                        user.put("longitude", 0);
                                        user.put("password", password);
                                        user.put("phone", phone_number);
                                        user.put("username", username);

                                        // Add a new document with a generated ID
                                        db.collection("users")
                                                .add(user)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        new AlertDialog.Builder(RegisterActivity.this)
                                                                .setTitle("Notification")
                                                                .setMessage("Account added successfully")
                                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                })
                                                                .show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        new AlertDialog.Builder(RegisterActivity.this)
                                                                .setTitle("Notification")
                                                                .setMessage("Add account failed")
                                                                .setPositiveButton("OK", null)
                                                                .show();
                                                    }
                                                });
                                    } else {
                                        for (int i = 0; i < list.size(); i++) {
                                            String username1 = list.get(i).getUsername();
                                            if (username1.equalsIgnoreCase(username)) {
                                                dem = 1;
                                                new AlertDialog.Builder(RegisterActivity.this)
                                                        .setTitle("Notification")
                                                        .setMessage("Add account failed\nAccount already exists, choose another account name")
                                                        .setPositiveButton("OK", null)
                                                        .show();
                                                break;
                                            }
                                        }
                                        if (dem == 0) {
                                            String username = edt_username.getText().toString();
                                            String password = edt_password.getText().toString();
                                            String re_password = edt_enter_password.getText().toString();
                                            String email = edt_email.getText().toString();
                                            String phone_number = edt_phone_number.getText().toString();
                                            String link = linkDL;
                                            String gender = "";
                                            String gender_pet = "";
                                            if(rdo_male.isChecked()){
                                                gender = rdo_male.getText().toString();
                                            }else {
                                                gender = rdo_female.getText().toString();
                                            }


                                            Map<String, Object> user = new HashMap<>();
                                            user.put("avatar", link);
                                            user.put("email", email);
                                            user.put("gender", gender);
                                            user.put("kycCode", "0");
                                            user.put("latitude", 0);
                                            user.put("longitude", 0);
                                            user.put("password", password);
                                            user.put("phone", phone_number);
                                            user.put("username", username);

                                            // Add a new document with a generated ID
                                            db.collection("users")
                                                    .add(user)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {
                                                            new AlertDialog.Builder(RegisterActivity.this)
                                                                    .setTitle("Notification")
                                                                    .setMessage("Account added successfully")
                                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialogInterface, int i) {
                                                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                                            startActivity(intent);
                                                                            finish();
                                                                        }
                                                                    })
                                                                    .show();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            new AlertDialog.Builder(RegisterActivity.this)
                                                                    .setTitle("Notification")
                                                                    .setMessage("add account failed")
                                                                    .setPositiveButton("OK", null)
                                                                    .show();
                                                        }
                                                    });
                                        }
                                    }
                                }
                            }
                        });
            }
        }
    }

    public Boolean validate(){
        String name = til_username.getEditText().getText().toString().trim();
        String password = til_password.getEditText().getText().toString().trim();
        String enter_password = til_enter_password.getEditText().getText().toString().trim();
        String email = til_email.getEditText().getText().toString().trim();
        String number = til_phone_number.getEditText().getText().toString().trim();

        if( name.isEmpty() || password.isEmpty() || enter_password.isEmpty() || email.isEmpty() ||
            number.isEmpty()){
            return false;
        }
        return true;
    }

    private boolean validate_username(){
        String name = til_username.getEditText().getText().toString().trim();
        if(name.isEmpty()){
            edt_username.setError("Please enter your account name");
            return false;
        }else{
            til_username.setError(null);
            til_username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validate_password(){
        String password = til_password.getEditText().getText().toString().trim();
        if(password.isEmpty()){
            edt_password.setError("Please enter password");
            return false;
        }else{
            til_password.setError(null);
            til_password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validate_enter_password(){
        String password = til_password.getEditText().getText().toString().trim();
        String enter_password = til_enter_password.getEditText().getText().toString().trim();

        if(enter_password.isEmpty()){
            edt_enter_password.setError("Please enter password");
            return false;
        }else if(!password.equalsIgnoreCase(enter_password)){
            edt_enter_password.setError("Password does not match");
            return false;
        } else{
            til_enter_password.setError(null);
            til_enter_password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validate_email(){
        String email = til_email.getEditText().getText().toString().trim();
        if(email.isEmpty()){
            edt_email.setError("Please enter email");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_email.setError("Email invalidate");
            return false;
        }else{
            til_email.setError(null);
            til_email.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validate_phone_number() {
        String number = til_phone_number.getEditText().getText().toString().trim();

        if(number.isEmpty()){
            edt_phone_number.setError("Please enter number phone");
            return false;
        }else if(number.length()>13 || number.length()<10){
            edt_phone_number.setError("Incorrect phone number");
            return false;
        }else if(!Patterns.PHONE.matcher(number).matches()){
            edt_phone_number.setError("Incorrect phone number format");
            return false;
        }else{
            til_phone_number.setError(null);
            til_phone_number.setErrorEnabled(false);
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSelectPicture(View view) {
        Boolean isPermissionAllowed = ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
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
                    try (InputStream inputStream = getContentResolver().openInputStream(uri)) {
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        imv_choose.setImageBitmap(bitmap);
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