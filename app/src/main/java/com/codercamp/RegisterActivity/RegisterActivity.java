package com.codercamp.RegisterActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codercamp.Home.R;
import com.codercamp.LoginActivity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;


public class RegisterActivity extends AppCompatActivity {

    private EditText NameText, EmailText, PhoneText, BooldText, AddressText, PasswordText;
    private CircularProgressButton cirLoginButton;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private long maxID = 0;
    public DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        reference = FirebaseDatabase.getInstance().getReference().child("UserData");
        dataModel = new DataModel();
//       Start Set Sin-up Button
        cirLoginButton = findViewById(R.id.cirRegisterButton);
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingUp();
                // cirLoginButton.startAnimation();
            }
        });
//       End Set Sin-up Button

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    //   Start Sing-Up Function
    public void SingUp() {

        NameText = findViewById(R.id.editTextName);
        EmailText = findViewById(R.id.editTextEmail);
        PhoneText = findViewById(R.id.editTextMobile);
        BooldText = findViewById(R.id.editTextBoold);
        PasswordText = findViewById(R.id.editTextPassword);
        AddressText = findViewById(R.id.editTextAddress);


        String Name, Email, Mobaile, Boold, Password, Adddresses;
        Name = NameText.getText().toString().trim();
        Email = EmailText.getText().toString().trim();
        Mobaile = PhoneText.getText().toString().trim();
        Boold = BooldText.getText().toString().trim();
        Password = PasswordText.getText().toString().trim();
        Adddresses = AddressText.getText().toString().trim();


        if (TextUtils.isEmpty(Name)) {
            cirLoginButton.setEnabled(false);
            NameText.setError("Please enter Name");
            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(Email)) {
            cirLoginButton.setEnabled(false);
            EmailText.setError("Please enter Email");
            Toast.makeText(getApplicationContext(), "Please enter Email", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(Mobaile)) {
            cirLoginButton.setEnabled(false);
            PhoneText.setError("Please enter Number");
            Toast.makeText(getApplicationContext(), "Please enter Mobaile", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(Boold)) {
            cirLoginButton.setEnabled(false);
            PasswordText.setError("Please enter Boold Group");
            Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(Adddresses)) {
            cirLoginButton.setEnabled(false);
            AddressText.setError("Please enter Address");
            Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_LONG).show();

            return;
        }
        if (TextUtils.isEmpty(Password)) {
            cirLoginButton.setEnabled(false);
            PasswordText.setError("Please enter Password");
            Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();

            return;
        }




        dataModel.setName(Name);
        dataModel.setEmail(Email);
        dataModel.setMobiles(Mobaile);
        dataModel.setBold(Boold);
        dataModel.setAddress(Adddresses);





        SaveData();


    }

    private void SaveData() {

        try {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.exists()){
                        maxID=(snapshot.getChildrenCount());
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            reference.child(String.valueOf(maxID+1)).setValue(dataModel);
            //reference.child(String.valueOf(maxID+1)).removeValue();
            //ClearTextBox();

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void ClearTextBox() {

        NameText.setText(" ");
        EmailText.setText("");
        PhoneText.setText("");
        BooldText.setText("");
        AddressText.setText("");
        PasswordText.setText("");
    }


//   End Sing-Up Function

}
