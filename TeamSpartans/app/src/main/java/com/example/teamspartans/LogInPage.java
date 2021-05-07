package com.example.teamspartans;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {
    private ShirshakDatabase shirshakDatabaseSecond;
    private Button registerButton,logInButton;
    private EditText userText,passText;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        registerButton = (Button)findViewById(R.id.registerButton);
        logInButton=(Button)findViewById(R.id.loginButton);
        userText=(EditText)findViewById(R.id.usernameText);
        passText=(EditText)findViewById(R.id.passwordText);
        shirshakDatabaseSecond = new ShirshakDatabase(this);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String $username=userText.getText().toString();
                String $pass=passText.getText().toString();
                if($username.isEmpty() || $pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Incorrect Username or password",Toast.LENGTH_SHORT).show();
                }
                else {
                    String check = shirshakDatabaseSecond.getUsernameAndPassword($username);
                    if (check.equals($pass)) {
                        Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                        intent.putExtra("tempname", $username);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplication(), "Incorrect username or password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void register(View v){
        Intent intent = new Intent(this,RegisterPage.class);
        startActivity(intent);
    }
}
