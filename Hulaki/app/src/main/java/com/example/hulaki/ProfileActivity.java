package com.example.hulaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.hulaki.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseStorage storage;
    Uri selectedImage;
    StorageReference reference;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Uploading Profile in Database");
        dialog.setCancelable(false);
        binding.profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });
        binding.continueProfilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checker = binding.nameBoxTwo.getText().toString();
                if(checker.isEmpty()){
                    binding.nameBoxTwo.setError("Please enter the name");
                    return;
                }
                dialog.show();
                if(selectedImage!=null){
                   reference = storage.getReference().child("Profiles").child(auth.getUid());
                    reference.putFile(selectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                              reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                  @Override
                                  public void onSuccess(Uri uri) {
                                     String imageUri = uri.toString();
                                     String uid = auth.getUid();
                                     String phone=auth.getCurrentUser().getPhoneNumber();
                                     String name=binding.nameBoxTwo.getText().toString();
                                     UsersModel model = new UsersModel(uid,name,imageUri,phone);
                                     database.getReference().child("Users").child(uid).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                         @Override
                                         public void onSuccess(Void aVoid) {
                                             dialog.dismiss();
                                           Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                                           startActivity(intent);
                                           finish();
                                         }
                                     });
                                  }
                              });
                            }
                        }
                    });
                }else{
                    String uid = auth.getUid();
                    String phone=auth.getCurrentUser().getPhoneNumber();
                    String name=binding.nameBoxTwo.getText().toString();
                    UsersModel model = new UsersModel(uid,name,"No image",phone);
                    database.getReference().child("Users").child(uid).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            dialog.dismiss();
                            Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(data.getData()!=null){
                binding.profileImage.setImageURI(data.getData());
                selectedImage=data.getData();
            }
        }
    }
}