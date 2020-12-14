package com.codercamp.LoginActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codercamp.Home.MainActivity;
import com.codercamp.Home.R;
import com.codercamp.RegisterActivity.RegisterActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;


public class LoginActivity extends AppCompatActivity {

    private EditText EmailText, PasswordText;
    private CircularProgressButton cirLoginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        cirLoginButton = findViewById(R.id.cirLoginButton);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn();
                //Toast.makeText(LoginActivity.this, "Click me", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void onLoginClick(View View) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

    }

//    Start Login Button

    private void LogIn() {

        EmailText = findViewById(R.id.editTextEmail);
        PasswordText = findViewById(R.id.editTextPassword);

        String email, password;
        email = EmailText.getText().toString();
        password = PasswordText.getText().toString();

        if (TextUtils.isEmpty(email)) {
            //cirLoginButton.setEnabled(false);
            EmailText.setError("Please Enter Email");
            Toast.makeText(getApplicationContext(), "Please Enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //cirLoginButton.setEnabled(false);
            PasswordText.setError("Please Enter Password");
            Toast.makeText(getApplicationContext(), "Please Enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        cirLoginButton.startAnimation();
        startActivity(new Intent(this, MainActivity.class));

    }
//    End Login Button
}
