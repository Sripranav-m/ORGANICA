package com.example.organica;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Seller_post extends AppCompatActivity {

    public String seller_username;
    public String item_name;
    public String item_rate;
    public String available_units;
    public String item_category;
    private int cando=0;
    private int candor=0;
    public EditText item_name_;
    public EditText item_rate_;
    public EditText available_units_;
    public ImageView item_image_url_;
    private Uri imageuri;
    private FirebaseStorage storage;
    public DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private StorageReference storagereference;
    public DatabaseReference reference;

    public void add_item_image(View view){
        Intent i=new Intent();
        i.setType("item_images/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        candor=1;
        switch(view.getId()) {
            case R.id.vegetables:
                if (checked) {
                    item_category = "VEGETABLES";
                }
                break;
            case R.id.fruits:
                if (checked) {
                    item_category = "FRUITS";
                }
                break;
            case R.id.snacks:
                if (checked) {
                    item_category = "SNACKS";
                }
                break;
            case R.id.beverages:
                if (checked) {
                    item_category = "BEVERAGES";
                }
                break;
            default:
                candor=0;
        }
    }

    public void submit_item(View view){
        String item_name=item_name_.getText().toString();
        String item_rate=item_rate_.getText().toString();
        String available_units=available_units_.getText().toString();
        if(item_name.matches("") || item_rate.matches("") || available_units.matches("") || item_category.matches("")){
            Toast.makeText(getApplicationContext(),"Fill All The Details Properly",Toast.LENGTH_SHORT).show();
        }
        else if(cando==1 && candor==1) {
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77");
            uploadpicture();
        }
        else{
            Toast.makeText(getApplicationContext(),"Failed To upload.....",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_post);
        item_image_url_=(ImageView)findViewById(R.id.item_image);
        storage=FirebaseStorage.getInstance();
        storagereference=storage.getReference();
        item_name_=(EditText)findViewById(R.id.item_name);
        item_rate_=(EditText)findViewById(R.id.item_rate);
        available_units_=(EditText)findViewById(R.id.available_units);
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        seller_username=user.getUid();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri=data.getData();
            item_image_url_.setImageURI(imageuri);
            cando=1;
        }
    }
    private void uploadpicture(){
        final String random_key=UUID.randomUUID().toString();
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading....");
        final StorageReference riversRef = storagereference.child("item_images/"+random_key);
        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                String item_name=item_name_.getText().toString();
                                String item_rate=item_rate_.getText().toString();
                                String available_units=available_units_.getText().toString();
                                String itemcategory=item_category;
                                ItemInfo articleInfo = new ItemInfo(seller_username,item_name,item_rate,available_units,itemcategory,url);
                                String id=reference.push().getKey();
                                reference.child("ITEMS").child(item_category).child(id).setValue(articleInfo);
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"Successfully Uploaded...",Toast.LENGTH_SHORT).show();

                                try {
                                    TimeUnit.SECONDS.sleep(3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                finish();
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed To upload",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progresspercent =(100.00 *taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        System.out.println(progresspercent);
                        pd.setMessage("Percentage: "+(int)progresspercent+"%");
                        pd.show();
                    }
                })
        ;
    }
}