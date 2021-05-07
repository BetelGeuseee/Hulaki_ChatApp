package com.example.teamspartans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AllPlayer extends AppCompatActivity {
    private String[] forRunsMatWic,forGenPosJersey;
    private TextView one,two,three,four,five,six,seven,eight;
    private ShirshakDatabase shirshakDatabaseFifth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_player);
        Intent intent=getIntent();
        String $finalUser=intent.getStringExtra("hello");

        one=(TextView)findViewById(R.id.secondTopUsername);
        two=(TextView)findViewById(R.id.secondRunsText);
        three=(TextView)findViewById(R.id.secondMatchText);
        four=(TextView)findViewById(R.id.secondWicketText);
        five=(TextView)findViewById(R.id.secondUsernameView);
        six=(TextView)findViewById(R.id.secondGenderView);
        seven=(TextView)findViewById(R.id.secondPositionView);
        eight=(TextView)findViewById(R.id.secondJerseyView);

        shirshakDatabaseFifth=new ShirshakDatabase(this);
        String gpj=shirshakDatabaseFifth.getGenPosJersey($finalUser);
        forGenPosJersey=gpj.split(",");
        five.setText($finalUser);
        six.setText(forGenPosJersey[0]);
        seven.setText(forGenPosJersey[1]);
        eight.setText(forGenPosJersey[2]);

        String rmw=shirshakDatabaseFifth.getRunsMatchesWickets($finalUser);
        forRunsMatWic=rmw.split(",");
        two.setText(forRunsMatWic[0]);
        four.setText(forRunsMatWic[1]);
        three.setText(forRunsMatWic[2]);

        String fl=shirshakDatabaseFifth.getFirstAndLast($finalUser);
        one.setText(fl);
    }
}