package com.example.organica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivitySeller extends AppCompatActivity {

    EditText name, email, pass, phone, address;
    Button register;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        name = findViewById(R.id.register_seller_name);
        email = findViewById(R.id.register_seller_email);
        pass = findViewById(R.id.register_seller_pass);
        phone = findViewById(R.id.register_seller_phone);
        address = findViewById(R.id.register_seller_address);
        register = findViewById(R.id.register_seller_button);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(RegisterActivitySeller.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_name = name.getText().toString();
                String str_email = email.getText().toString();
                String str_pass = pass.getText().toString();
                String str_phone = phone.getText().toString();
                String str_address = address.getText().toString();

                if(TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_email)|| TextUtils.isEmpty(str_pass) || TextUtils.isEmpty(str_phone) || TextUtils.isEmpty(str_address)){
                    Toast.makeText(RegisterActivitySeller.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }else if(str_pass.length() < 6){
                    Toast.makeText(RegisterActivitySeller.this, "Password must have atleast 6 characters", Toast.LENGTH_SHORT).show();
                }else {
                    register(str_name, str_email, str_pass, str_phone, str_address);
                }
            }
        });
    }

    private void register(final String name, final String email, String password, final String phone, final String address){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivitySeller.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("Sellers").child(userid);
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("Name", name);
                            hashMap.put("Email", email);
                            hashMap.put("Phone", phone);
                            hashMap.put("Address", address);
                            hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/organic-app-31188.appspot.com/o/images%2Favatar.jpg?alt=media&token=ef753982-bea8-4e8a-af01-ab497ec6a65f");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        pd.dismiss();
                                        Intent intent = new Intent(RegisterActivitySeller.this, TargetActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }else{
                            pd.dismiss();
                            Toast.makeText(RegisterActivitySeller.this, "You can't use this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}