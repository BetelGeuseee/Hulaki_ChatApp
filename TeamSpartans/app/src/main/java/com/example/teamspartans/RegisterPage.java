package com.example.teamspartans;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    private Button save;
    private ShirshakDatabase shirshakDatabase;
    private CheckBox check;
    private EditText usernameReg,firstname,lastname,$jerseyNumber;
    private EditText edtPassword;
    private RadioGroup genderRadioGroup,posRadioGroup;
    private RadioButton genderButton,positionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        //Finding view by id of all views
        check=(CheckBox)findViewById(R.id.checkPassword);
        edtPassword=(EditText)findViewById(R.id.passwordRegister);
        save=(Button)findViewById(R.id.saveButton);

        usernameReg = (EditText)findViewById(R.id.userNameRegister);
        firstname=(EditText)findViewById(R.id.firstNameText);
        lastname=(EditText)findViewById(R.id.lastNameText);
        genderRadioGroup=(RadioGroup)findViewById(R.id.genderRadioButton);
        posRadioGroup=(RadioGroup)findViewById(R.id.positionRadioButton);
        $jerseyNumber=(EditText)findViewById(R.id.jerseyNumber);
        shirshakDatabase=new ShirshakDatabase(this);
        //save button click listener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  try {
                      String user = usernameReg.getText().toString();
                      String f_name = firstname.getText().toString();
                      String l_name = lastname.getText().toString();
                      int selectedIdSex = genderRadioGroup.getCheckedRadioButtonId();
                      genderButton = (RadioButton) findViewById(selectedIdSex);
                      String sex = genderButton.getText().toString();
                      int selectedIdPos = posRadioGroup.getCheckedRadioButtonId();
                      positionButton = (RadioButton) findViewById(selectedIdPos);
                      String position = positionButton.getText().toString();
                      String pass = edtPassword.getText().toString();
                      String num = $jerseyNumber.getText().toString();

                      int number = Integer.parseInt(num);
                      long id = shirshakDatabase.insertAllData(user, pass, f_name, l_name, sex, position, number);

                      if (id < 0) {
                          Toast.makeText(getApplication(), "Cannot be saved", Toast.LENGTH_SHORT).show();
                      } else {
                          Toast.makeText(getApplication(), "saved successfully", Toast.LENGTH_SHORT).show();
                          Intent intent =new Intent(RegisterPage.this,LogInPage.class);
                          startActivity(intent);
                          finish();
                      }
                  }catch(Exception e){
                      Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_SHORT).show();
                  }
            }
        });
        //this code is for showing the password when check button is checked
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}
