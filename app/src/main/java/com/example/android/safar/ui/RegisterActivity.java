package com.example.android.safar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;
import com.example.android.safar.model.SQLiteHelper;
import com.example.android.safar.model.User;

import Utils.Utils;

public class RegisterActivity extends AppCompatActivity {

    private String emailStr, usernameStr, passwordStr, confirmPassStr;
    private static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqLiteHelper = new SQLiteHelper(RegisterActivity.this);

        final EditText email = findViewById(R.id.email_edit_text);
        final EditText username = findViewById(R.id.username_reg);
        final EditText password = findViewById(R.id.password1_reg);
        final EditText confirmPass = findViewById(R.id.password2_reg);

        Button register = findViewById(R.id.register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailStr = email.getText().toString();
                usernameStr = username.getText().toString();
                passwordStr = password.getText().toString();
                confirmPassStr = confirmPass.getText().toString();

                // Check the validation of the entered user info
                if (Utils.isValidUserInfo(v.getContext(), emailStr,
                        usernameStr, passwordStr, confirmPassStr)) {
                    if (sqLiteHelper.addUser(new User(
                            -1,
                            emailStr,
                            usernameStr,
                            passwordStr,
                            false))) {
                        Toast.makeText(
                                RegisterActivity.this,
                                "Successful User Added " + usernameStr,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(
                                RegisterActivity.this,
                                "Failed Adding User " + usernameStr,
                                Toast.LENGTH_SHORT).show();
                    }

                    startActivity(new Intent(
                            RegisterActivity.this,
                            MainActivity.class));
                } else {
                    Toast.makeText(
                            RegisterActivity.this,
                            "Invalid user info or user already registered!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}











