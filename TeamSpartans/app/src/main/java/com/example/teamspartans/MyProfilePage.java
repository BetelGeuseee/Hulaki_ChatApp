package com.example.teamspartans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;


public class MyProfilePage extends AppCompatActivity {
    private String tempArrayOne[],tempArrayTwo[];
    private ShirshakDatabase shirshakDatabaseSecond;
    private TextView first,second,third,fourth,fifth,sixth,seventh,eight;
    private ImageView $profileView;
    private String mainUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile_page);
        first=(TextView)findViewById(R.id.topUsername);
        second=(TextView)findViewById(R.id.runsText);
        third=(TextView)findViewById(R.id.matchText);
        fourth=(TextView)findViewById(R.id.wicketText);
        fifth=(TextView)findViewById(R.id.usernameView);
        sixth=(TextView)findViewById(R.id.genderView);
        seventh=(TextView)findViewById(R.id.positionView);
        eight=(TextView)findViewById(R.id.jerseyView);
        $profileView=(ImageView)findViewById(R.id.profileView);

        Intent intent = getIntent();
        mainUsername=intent.getStringExtra("dummyName");
        shirshakDatabaseSecond=new ShirshakDatabase(this);

         first.setText(shirshakDatabaseSecond.getFirstAndLast(mainUsername));
         fifth.setText(mainUsername);

        String dummyOne=shirshakDatabaseSecond.getGenPosJersey(mainUsername);
        tempArrayOne=dummyOne.split(",");
        sixth.setText(tempArrayOne[0]);
        seventh.setText(tempArrayOne[1]);
        eight.setText(tempArrayOne[2]);

        String dummyTwo=shirshakDatabaseSecond.getRunsMatchesWickets(mainUsername);
        tempArrayTwo=dummyTwo.split(",");
        second.setText(tempArrayTwo[0]);
        fourth.setText(tempArrayTwo[1]);
        third.setText(tempArrayTwo[2]);
    }
}