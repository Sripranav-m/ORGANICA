package com.example.organica;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddArticle extends AppCompatActivity {

    public String username;
    public String Heading;
    public String Content;

    public int cando=0;

    public EditText heading;
    public EditText content;


    private ImageView uploadedimage;

    private Uri imageuri;





    private FirebaseStorage storage;

    public DatabaseReference databaseReference;

    private StorageReference storagereference;

    public DatabaseReference reference;

    public void upload(View view){
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }
    public void submit(View view){
        String Content=content.getText().toString();
        String Heading=heading.getText().toString();

        if(Content.matches("") || Heading.matches("")){
            Toast.makeText(getApplicationContext(),"Write Heading and Content.....",Toast.LENGTH_SHORT).show();
        }
        else if(Content.matches("") ){
            Toast.makeText(getApplicationContext(),"Write Content.....",Toast.LENGTH_SHORT).show();
        }
        else if(Heading.matches("")){
            Toast.makeText(getApplicationContext(),"Write Heading .....",Toast.LENGTH_SHORT).show();
        }
        else if(cando==1) {
            uploadpicture();
        }
        else{
            Toast.makeText(getApplicationContext(),"Failed To upload.....",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        uploadedimage=(ImageView)findViewById(R.id.uploadedimage);
        storage=FirebaseStorage.getInstance();
        storagereference=storage.getReference();
        content=(EditText)findViewById(R.id.content);
        heading=(EditText)findViewById(R.id.heading);
        reference = FirebaseDatabase.getInstance().getReference();
        username="sripranav";///////////////////////////AS OF NOW ,, , , , , USERNAME IS DUMMY
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageuri=data.getData();
            uploadedimage.setImageURI(imageuri);
            cando=1;
        }
    }

    private void uploadpicture(){
        final String randomkey=UUID.randomUUID().toString();
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading....");
        final StorageReference riversRef = storagereference.child("images/"+randomkey);
        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                String Content=content.getText().toString();
                                String Heading=heading.getText().toString();
                                ArticlesInfo articleInfo = new ArticlesInfo(username,Heading,Content,url);
                                String id=reference.push().getKey();
                                reference.child("ARTICLES").child(id).setValue(articleInfo);
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"Successfully Uploaded...",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(AddArticle.this,MainActivity.class);
                                startActivity(i);
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