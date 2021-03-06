package com.example.passwordmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText signedUpEmail, signedUpPassword;
    Button signupButton;
    LoginHelper loginDatabase;
    PasswordHelper passwordHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signedUpEmail = (EditText)findViewById(R.id.signupEmail);
        signedUpPassword = (EditText)findViewById(R.id.signupPassword);
        signupButton = (Button)findViewById(R.id.signupButton);
        loginDatabase = new LoginHelper(this);
        passwordHelper = new PasswordHelper(this);

        signupButton.setOnClickListener(view -> {
            if(TextUtils.isEmpty(signedUpEmail.getText().toString())) {
                signedUpEmail.setError("Please enter an Email id");
            }
            else if(TextUtils.isEmpty(signedUpPassword.getText().toString())) {
                signedUpPassword.setError("Please enter a password");
            }
            else
            {
                String userName = signedUpEmail.getText().toString();
                String password = signedUpPassword.getText().toString();
                Cursor storedpassword = loginDatabase.getData(userName);
                storedpassword.moveToFirst();
                String resEmail = storedpassword.getString(storedpassword.getColumnIndex(LoginHelper.CONTACTS_COLUMN_EMAIL));
                String resPassword = storedpassword.getString(storedpassword.getColumnIndex(LoginHelper.CONTACTS_COLUMN_PASSWORD));

                if(resEmail.equals(userName) && resPassword.equals(password))
                {
                    Toast.makeText(Login.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    //Dialoge dismiss

                    int passwordDBValues = passwordHelper.getAllData();
                    if(passwordDBValues == 1){
                        Intent intent = new Intent(Login.this, MainLayout.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Login.this, AddPassword.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        passwordHelper.close();
        loginDatabase.close();
    }
}