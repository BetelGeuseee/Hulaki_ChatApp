package com.example.hulaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hulaki.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        dialog= new ProgressDialog(this);
        dialog.setTitle("Authentication");
        dialog.setMessage("Authenticating....");
        dialog.setCancelable(false);
        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
            }
        });
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                createAccount(binding.emailReg.getText().toString(),binding.passwordReg.getText().toString());
            }
        });
    }
    private void createAccount(String email,String password){
         auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     dialog.dismiss();
                     Toast.makeText(RegisterActivity.this,"User created Successfully",Toast.LENGTH_SHORT).show();
                     String id = task.getResult().getUser().getUid();
                     UsersModel user = new UsersModel(binding.usernameReg.getText().toString(),binding.emailReg.getText().toString(),binding.passwordReg.getText().toString(),id,"No image");
                     database.getReference().child("Users").child(id).setValue(user);
                     Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                     startActivity(intent);
                     finish();
                 }else{
                     Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                 }
             }
         });
    }

}