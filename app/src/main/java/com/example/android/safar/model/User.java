package com.example.android.safar.model;

public class User {

    private final int userID;
    private final String email;
    private final String username;
    private final String password;
    private final boolean autoSignIn;

    public User(int userID, String email, String username,
                String password, boolean autoSignIn) {
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.autoSignIn = autoSignIn;
    }

    @Override
    public String toString() {
        return "> User ID: " + userID + "\n\n" +
                "Email: " + email + "\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n";
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAutoSignIn() {
        return autoSignIn;
    }
}
