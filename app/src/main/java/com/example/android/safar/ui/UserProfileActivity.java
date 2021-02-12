package com.example.android.safar.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;
import com.example.android.safar.model.SQLiteHelper;

import Utils.Utils;

import static com.example.android.safar.ui.MainActivity.signedInUser;

public class UserProfileActivity extends AppCompatActivity {

    private TextView usernameTV;
    private TextView userEmailTV;

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sqLiteHelper = new SQLiteHelper(this);

        usernameTV = findViewById(R.id.user_name_in_profile);
        userEmailTV = findViewById(R.id.user_email_in_profile);

        RelativeLayout userNameRL = findViewById(R.id.username_in_profile_rl);
        userNameRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditUsernameDialog();
            }
        });

        RelativeLayout userEmailRL = findViewById(R.id.user_email_in_profile_rl);
        userEmailRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditUserEmailDialog();
            }
        });

        Button changesPassBtn = findViewById(R.id.change_password_btn);
        changesPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePassDialog();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUIText();
    }

    private void showEditUsernameDialog() {
        final View editUsernameDialog = LayoutInflater.from(this)
                .inflate(R.layout.edit_one_user_record_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter new username for user \'" + signedInUser.getUsername() + "\'")
                .setView(editUsernameDialog);

        final EditText usernameET = editUsernameDialog.findViewById(R.id.edit_one_user_record_et);
        usernameET.setText(signedInUser.getUsername());

        final EditText currentPassET = editUsernameDialog.findViewById(R.id.current_password_et);

        builder.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newUserName = usernameET.getText().toString();
                String password = currentPassET.getText().toString();

                if (Utils.isValidUsername(newUserName) &&
                        !sqLiteHelper.isUserRegistered(newUserName) &&
                        password.equals(signedInUser.getPassword())) {
                    sqLiteHelper.updateUsername(signedInUser.getUserID(), newUserName);

                    signedInUser = sqLiteHelper.getUserIfRegistered(
                            newUserName, signedInUser.getPassword());

                    updateUIText();

                    Toast.makeText(
                            UserProfileActivity.this,
                            "Username updated for user " + signedInUser.getUsername(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            UserProfileActivity.this,
                            "Username " + newUserName + " already exists or incorrect password!",
                            Toast.LENGTH_LONG).show();
                }
            }
        })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showEditUserEmailDialog() {
        final View editUserEmailDialog = LayoutInflater.from(this)
                .inflate(R.layout.edit_one_user_record_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new email for user \'" + signedInUser.getUsername() + "\'")
                .setView(editUserEmailDialog);

        final EditText editUerEmailET = editUserEmailDialog.findViewById(R.id.edit_one_user_record_et);
        editUerEmailET.setText(signedInUser.getEmail());

        final EditText passwordET = editUserEmailDialog.findViewById(R.id.current_password_et);

        builder.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEmail = editUerEmailET.getText().toString();
                String password = passwordET.getText().toString();

                if (Utils.isValidEmail(newEmail) &&
                        password.equals(signedInUser.getPassword())) {
                    sqLiteHelper.updateUserEmail(signedInUser.getUserID(), newEmail);

                    signedInUser = sqLiteHelper.getUserIfRegistered(
                            signedInUser.getUsername(), signedInUser.getPassword());

                    updateUIText();

                    Toast.makeText(
                            UserProfileActivity.this,
                            "Email updated for user " + signedInUser.getUsername(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            UserProfileActivity.this,
                            "Invalid email info or incorrect password!",
                            Toast.LENGTH_LONG).show();
                }
            }
        })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void showChangePassDialog() {
        final View changePassDialog = LayoutInflater.from(this)
                .inflate(R.layout.change_user_password_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change password for user \'" + signedInUser.getUsername() + "\'")
                .setView(changePassDialog)
                .setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText currentPassET = changePassDialog.findViewById(R.id.profile_current_pass_et);
                        String password = currentPassET.getText().toString();

                        EditText newPassET = changePassDialog.findViewById(R.id.profile_new_pass_et);
                        String newPassStr = newPassET.getText().toString();

                        EditText confirmPassET = changePassDialog.findViewById(R.id.profile_confirm_new_pass_et);
                        String confirmPssStr = confirmPassET.getText().toString();

                        if (password.equals(signedInUser.getPassword()) &&
                                newPassStr.equals(confirmPssStr)) {
                            sqLiteHelper.updateUserPassword(signedInUser.getUserID(), newPassStr);

                            // The app crashes when executing the below code. [no idea why!]
                            /*signedInUser = sqLiteHelper.getUserIfRegistered(
                                    signedInUser.getUsername(), signedInUser.getPassword());

                            updateUIText();*/

                            Toast.makeText(
                                    UserProfileActivity.this,
                                    "Password updated for user " + signedInUser.getUsername(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(
                                    UserProfileActivity.this,
                                    "Current password or new password and confirm password don't match!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateUIText() {
        this.setTitle(signedInUser.getUsername() + " Account");
        usernameTV.setText(signedInUser.getUsername());
        userEmailTV.setText(signedInUser.getEmail());
    }

}


















