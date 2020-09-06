package com.example.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivityBuyer extends AppCompatActivity {

    EditText email, password;
    Button login, signup;
    ImageButton back;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_buyer);

        email = findViewById(R.id.login_buyer_email);
        password = findViewById(R.id.login_buyer_pass);
        login = findViewById(R.id.buyer_log_in);
        signup = findViewById(R.id.buyer_sign_up);
        back =  findViewById(R.id.login_back);

        auth = FirebaseAuth.getInstance();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivityBuyer.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivityBuyer.this, RegisterActivityBuyer.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(LoginActivityBuyer.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_email = email.getText().toString();
                String str_pass = password.getText().toString();

                if(TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_pass)){
                    pd.dismiss();
                    Toast.makeText(LoginActivityBuyer.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else{
                    auth.signInWithEmailAndPassword(str_email, str_pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Buyers");
                                        ref.orderByChild("id").equalTo(auth.getCurrentUser().getUid())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        int chk = 0;
                                                        for(DataSnapshot ds: snapshot.getChildren()){
                                                            String accountType = ""+ds.child("accountType").getValue();
                                                            if(accountType.equals("buyer")){
                                                                chk = 1;
                                                                pd.dismiss();
                                                                Intent intent = new Intent(LoginActivityBuyer.this, Home.class);
                                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                startActivity(intent);
                                                            }else{
                                                                break;
                                                            }
                                                        }
                                                        if(chk==0) {
                                                            pd.dismiss();
                                                            auth.signOut();
                                                            Toast.makeText(LoginActivityBuyer.this, "Authentication failed!!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {
                                                        pd.dismiss();
                                                    }
                                                });
                                    }else{
                                        pd.dismiss();
                                        Toast.makeText(LoginActivityBuyer.this, "Authentication failed!!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}