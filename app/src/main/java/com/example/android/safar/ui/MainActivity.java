package com.example.android.safar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;
import com.example.android.safar.model.SQLiteHelper;
import com.example.android.safar.model.User;

public class MainActivity extends AppCompatActivity {

    private String defaultAdminPassword;

    private EditText usernameLoginET, passwordLoginET;

    private SQLiteHelper sqLiteHelper;

    private CheckBox autoSignInCH;

    public static User signedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(MainActivity.this);

        MainActivity.signedInUser = sqLiteHelper.getAutoSignedInUser();

        if (MainActivity.signedInUser != null) {
            Intent intent = new Intent(MainActivity.this, CountryActivity.class);
            startActivity(intent);
        }

        defaultAdminPassword = "admin";

        usernameLoginET = findViewById(R.id.username__login_et);
        passwordLoginET = findViewById(R.id.password_login_et);
        autoSignInCH = findViewById(R.id.auto_sign_in_check_box);

        Button signInBtn = findViewById(R.id.sign_in_btn);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = usernameLoginET.getText().toString();
                String passwordStr = passwordLoginET.getText().toString();

                signedInUser = sqLiteHelper.getUserIfRegistered(usernameStr, passwordStr);

                if (signedInUser != null) {
                    sqLiteHelper.updateUserAutoSignIn(signedInUser.getUserID(), autoSignInCH.isChecked());

                    startActivity(new Intent(
                            MainActivity.this,
                            CountryActivity.class));
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Username or password incorrect.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        Button signUp = findViewById(R.id.sign_up_btn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(
                        MainActivity.this,
                        RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        usernameLoginET.setText("");
        passwordLoginET.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.admin_login:
                showDialog();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    private void showDialog() {
        View adminLoginDialog = LayoutInflater.from(this)
                .inflate(R.layout.admin_login_dialog, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(adminLoginDialog);

        AlertDialog alertDialog = dialogBuilder.create();

        final EditText adminPassword = adminLoginDialog.findViewById(R.id.admin_pass);

        final Button adminLoginBtn = adminLoginDialog.findViewById(R.id.admin_login_btn);
        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adminPassword.getText().toString().equals(defaultAdminPassword)) {
                    startActivity(new Intent(MainActivity.this, AdminActivity.class));
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Admin Password is incorrect",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();
    }

}













