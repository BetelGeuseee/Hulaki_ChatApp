package com.example.teamspartans;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SpartansList extends AppCompatActivity  {
    private ShirshakDatabase shirshakDatabaseThird;
    String[] tempo;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spartans_list);
        listView=(ListView)findViewById(R.id.listView);
        shirshakDatabaseThird=new ShirshakDatabase(this);
        String player=shirshakDatabaseThird.getAllUsername();
        tempo=player.split(",");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tempo);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String $tempUser=tempo[position];
                Toast.makeText(getApplicationContext(),$tempUser,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AllPlayer.class);
                intent.putExtra("hello",$tempUser);
                startActivity(intent);
            }
        });
    }
}
