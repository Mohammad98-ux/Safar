package com.example.android.safar.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.safar.R;
import com.example.android.safar.model.SQLiteHelper;
import com.example.android.safar.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import Utils.Utils;

public class AdminActivity extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;
    private ListView registeredUsersLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sqLiteHelper = new SQLiteHelper(AdminActivity.this);

        Button viewAllUsersBtn = findViewById(R.id.view_all_users_btn);
        viewAllUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllUsers();
            }
        });

        Button deleteAllUsersBtn = findViewById(R.id.delete_all_users_btn);
        deleteAllUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAllUsersDialog();
            }
        });

        registeredUsersLV = findViewById(R.id.registered_users_list_view);
        TextView emptyStateTV = findViewById(R.id.users_empty_state_text_view);
        registeredUsersLV.setEmptyView(emptyStateTV);

        showAllUsers();

        registeredUsersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final PopupMenu popupMenu = new PopupMenu(AdminActivity.this, view);
                getMenuInflater().inflate(
                        R.menu.activity_admin_click_on_user_popup_menu, popupMenu.getMenu());

                final User clickedUser = (User) registeredUsersLV.getItemAtPosition(position);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_user_item:
                                showEditUserDialog(clickedUser);
                                break;

                            case R.id.delete_user_item:
                                showConfirmDeleteUserDialog(clickedUser);
                                break;
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        FloatingActionButton addUserFAB = findViewById(R.id.add_user_fab);
        addUserFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent loginActivityIntent = new Intent(this, MainActivity.class);
        loginActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(loginActivityIntent);
    }

    private void showDeleteAllUsersDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm deletion of all users")
                .setMessage("Are you sure you want to delete all registered users?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqLiteHelper.deleteAllUsers();
                        showAllUsers();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showConfirmDeleteUserDialog(final User clickedUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Confirm deletion")
                .setMessage("Are you sure you want to delete user " + clickedUser.getUsername() + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqLiteHelper.deleteUser(clickedUser.getUserID());
                        showAllUsers();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAddUserDialog() {
        final View addUserDialog = LayoutInflater.from(this)
                .inflate(R.layout.add_or_edit_user_dialog, null);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                .setView(addUserDialog)
                .setTitle("Add User");

        final EditText newEmail = addUserDialog.findViewById(R.id.new_email_edit_text);
        final EditText newUsername = addUserDialog.findViewById(R.id.new_username_edit_text);
        final EditText newPassword = addUserDialog.findViewById(R.id.new_password_edit_text);
        final EditText confirmPassword = addUserDialog.findViewById(R.id.confirm_password_edit_text);

        dialogBuilder.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String emailStr = newEmail.getText().toString();
                        String usernameStr = newUsername.getText().toString();
                        String passwordStr = newPassword.getText().toString();
                        String confirmPasswordStr = confirmPassword.getText().toString();

                        if (Utils.isValidUserInfo(getApplicationContext(), emailStr, usernameStr,
                                passwordStr, confirmPasswordStr) &&
                                !sqLiteHelper.isUserRegistered(usernameStr)) {
                            if (sqLiteHelper.addUser(new User(
                                    -1,
                                    emailStr,
                                    usernameStr,
                                    passwordStr,
                                    false))) {
                                Toast.makeText(
                                        AdminActivity.this,
                                        "Successful User Added  " + usernameStr,
                                        Toast.LENGTH_SHORT).show();

                                showAllUsers();
                            } else {
                                Toast.makeText(
                                        AdminActivity.this,
                                        "Failed Adding User " + usernameStr,
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(
                                    AdminActivity.this,
                                    "Invalid user info!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        dialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        dialogBuilder.show();

    }

    private void showEditUserDialog(final User userToEdit) {
        final View editUserDialog = LayoutInflater.from(this)
                .inflate(R.layout.add_or_edit_user_dialog, null);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                .setView(editUserDialog)
                .setTitle("Edit User '" + userToEdit.getUsername() + "'");

        final EditText newEmailET = editUserDialog.findViewById(R.id.new_email_edit_text);
        newEmailET.setText(userToEdit.getEmail());

        final EditText newUsernameET = editUserDialog.findViewById(R.id.new_username_edit_text);
        newUsernameET.setText(userToEdit.getUsername());

        final EditText newPasswordET = editUserDialog.findViewById(R.id.new_password_edit_text);
        newPasswordET.setText(userToEdit.getPassword());

        final EditText confirmPasswordET = editUserDialog.findViewById(R.id.confirm_password_edit_text);
        confirmPasswordET.setText("");

        dialogBuilder.setPositiveButton("Commit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmailStr = newEmailET.getText().toString();
                        String newUsernameStr = newUsernameET.getText().toString();
                        String newPassStr = newPasswordET.getText().toString();
                        String confirmNewPassStr = confirmPasswordET.getText().toString();

                        if (Utils.isValidUserInfo(getApplicationContext(), newEmailStr,
                                newUsernameStr, newPassStr, confirmNewPassStr)) {
                            sqLiteHelper.updateUser(
                                    userToEdit.getUserID(),
                                    new User(-1, newEmailStr,
                                            newUsernameStr, newPassStr, false));

                            Toast.makeText(
                                    AdminActivity.this,
                                    "User Updated " + newUsernameStr,
                                    Toast.LENGTH_SHORT).show();

                            showAllUsers();
                        } else {
                            Toast.makeText(
                                    AdminActivity.this,
                                    "Invalid user info or user already isUserRegistered!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        dialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        dialogBuilder.show();
    }

    private void showAllUsers() {
        ArrayAdapter usersAdapter = new ArrayAdapter<>(
                AdminActivity.this,
                R.layout.custom_simple_list_item,
                sqLiteHelper.getRegisteredUsersList());

        registeredUsersLV.setAdapter(usersAdapter);
    }


}















