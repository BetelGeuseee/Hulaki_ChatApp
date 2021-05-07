package com.example.teamspartans;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditingActivity extends AppCompatActivity {
    private ShirshakDatabase shirshakDatabaseFourth;
    private EditText updateUser,updateRun,updateWickets,updateMatches,deleteUser;
    private Button buttonRun,buttonWickets,buttonMatches,buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);
        shirshakDatabaseFourth=new ShirshakDatabase(this);
        updateUser=(EditText)findViewById(R.id.changeUsername);
        updateRun=(EditText)findViewById(R.id.changeRuns);
        updateWickets=(EditText)findViewById(R.id.changeWicket);
        updateMatches=(EditText)findViewById(R.id.changeMatches);
        deleteUser=(EditText)findViewById(R.id.deleteUsername);
        buttonRun=(Button)findViewById(R.id.runButton);
        buttonWickets=(Button)findViewById(R.id.wicketButton);
        buttonMatches=(Button)findViewById(R.id.matchButton);
        buttonDelete=(Button)findViewById(R.id.deleteButton);

        buttonRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean hahaha = shirshakDatabaseFourth.updateRuns(updateUser.getText().toString(), Integer.parseInt(updateRun.getText().toString()));
                    if (hahaha == true)
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Cannot be Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Please fill the void", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonWickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean hahaha = shirshakDatabaseFourth.updateWickets(updateUser.getText().toString(), Integer.parseInt(updateWickets.getText().toString()));
                    if (hahaha == true)
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Cannot be Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Please fill the void", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    boolean hahaha = shirshakDatabaseFourth.updateMatches(updateUser.getText().toString(), Integer.parseInt(updateMatches.getText().toString()));
                    if (hahaha == true)
                        Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Cannot be Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Please fill the void", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteUser.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill the void", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean hahaha = shirshakDatabaseFourth.deleteUsername(deleteUser.getText().toString());
                    if (hahaha == true)
                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Cannot be deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }