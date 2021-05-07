package com.example.teamspartans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ProfilePage extends AppCompatActivity {
    private String $tempName;
    private ImageView profilePicture;
    private Button $profileButton,$spartansButton,$editPlayerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Intent intent = getIntent();
        $tempName = intent.getStringExtra("tempname");
        profilePicture = (ImageView) findViewById(R.id.propicture);
        $profileButton = (Button) findViewById(R.id.profileButton);
        $spartansButton=(Button)findViewById(R.id.spartansButton);
        $editPlayerButton=(Button)findViewById(R.id.editPlayerButton);

        $editPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncryptedDialog dialog = new EncryptedDialog();
                dialog.show(getSupportFragmentManager(),"encrypted dialog");
            }
        });
        $spartansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAnother = new Intent(getApplicationContext(),SpartansList.class);
                startActivity(gotoAnother);
            }
        });
        $profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfilePage.class);
                intent.putExtra("dummyName", $tempName);
                startActivity(intent);
            }
        });
    }
}