package com.example.android.safar.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Utils.QueryUtils;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String USERS_TABLE = "USERS_TABLE";
    private static final String COL_USER_ID = "USER_ID";
    private static final String COL_USER_EMAIL = "USER_EMAIL";
    private static final String COL_USERNAME = "USERNAME";
    private static final String COL_PASSWORD = "PASSWORD";
    private static final String COL_AUTO_SIGN_IN = "AUTO_SIGN_IN";

    private static final String FAV_COUNTRIES_TABLE = "FAV_COUNTRIES_TABLE";
    private static final String COL_COUNTRY_ID = "COUNTRY_ID";
    private static final String COL_COUNTRY_NAME = "COUNTRY_NAME";

    private static final String FAV_ATTRACTIONS_TABLE = "FAV_ATTRACTIONS_TABLE";
    private static final String COL_ATTR_ID = "ATTR_ID";
    private static final String COL_ATTR_NAME = "ATTR_NAME";

    public SQLiteHelper(Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTableQuery = "CREATE TABLE " + USERS_TABLE + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_EMAIL + " TEXT, " +
                COL_USERNAME + " TEXT, " +
                COL_PASSWORD + " TEXT, " +
                COL_AUTO_SIGN_IN + " BOOL)";

        String createFavCountriesTableQuery = "CREATE TABLE " + FAV_COUNTRIES_TABLE + " (" +
                COL_COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_COUNTRY_NAME + " TEXT)";

        String createFavAttractionsTableQuery = "CREATE TABLE " + FAV_ATTRACTIONS_TABLE + " (" +
                COL_ATTR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ATTR_NAME + " TEXT)";

        db.execSQL(createUsersTableQuery);
        db.execSQL(createFavCountriesTableQuery);
        db.execSQL(createFavAttractionsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_EMAIL, user.getEmail());
        contentValues.put(COL_USERNAME, user.getUsername());
        contentValues.put(COL_PASSWORD, user.getPassword());

        int autoSignInInt = user.isAutoSignIn() ? 1 : 0;
        contentValues.put(COL_AUTO_SIGN_IN, autoSignInInt);

        SQLiteDatabase db = this.getWritableDatabase();
        long insertResult = db.insert(USERS_TABLE, null, contentValues);

        return insertResult != -1;
    }

    public ArrayList<User> getRegisteredUsersList() {
        ArrayList<User> users = new ArrayList<>();

        String queryString = "SELECT * FROM " + USERS_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                boolean autoSignIn = cursor.getInt(4) == 1;

                users.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        autoSignIn));
            } while (cursor.moveToNext());
        }

        return users;
    }

    public boolean isUserRegistered(String username) {
        for (User user : getRegisteredUsersList()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public User getAutoSignedInUser() {
        for (User regUser : getRegisteredUsersList()) {
            if (regUser.isAutoSignIn()) {
                return regUser;
            }
        }

        return null;
    }

    public void updateUser(int userId, User newUserInfo) {
        updateUserEmail(userId, newUserInfo.getEmail());
        updateUsername(userId, newUserInfo.getUsername());
        updateUserPassword(userId, newUserInfo.getPassword());
        updateUserAutoSignIn(userId, newUserInfo.isAutoSignIn());
    }

    public void updateUserEmail(int userId, String newEmail) {
        String updateUserEmailQuery = "UPDATE " + USERS_TABLE + " SET " +
                COL_USER_EMAIL + " = \"" + newEmail + "\"" +
                " WHERE " + COL_USER_ID + " = " + userId;

        this.getWritableDatabase().execSQL(updateUserEmailQuery);
    }

    public void updateUsername(int userId, String newUsername) {
        String updateUsernameQuery = "UPDATE " + USERS_TABLE + " SET " +
                COL_USERNAME + " = \"" + newUsername + "\"" +
                " WHERE " + COL_USER_ID + " = " + userId;

        this.getWritableDatabase().execSQL(updateUsernameQuery);
    }

    public void updateUserPassword(int userId, String newPassword) {
        String updateUserPassQuery = "UPDATE " + USERS_TABLE + " SET " +
                COL_PASSWORD + " = \"" + newPassword + "\"" +
                " WHERE " + COL_USER_ID + " = " + userId;

        this.getWritableDatabase().execSQL(updateUserPassQuery);
    }

    public void updateUserAutoSignIn(int userId, boolean autoSignIn) {
        int autoSignInInt = autoSignIn ? 1 : 0;

        String updateQuery = "UPDATE " + USERS_TABLE + " SET " +
                COL_AUTO_SIGN_IN + " = " + autoSignInInt +
                " WHERE " + COL_USER_ID + " = " + userId;

        this.getWritableDatabase().execSQL(updateQuery);
    }

    public void logoutUser(int userId) {
        updateUserAutoSignIn(userId, false);
    }

    /**
     * Returns the user whose username is 'username' and whose password is 'password'.
     *
     * @return user with the provided info. If no user was found, it returns null.
     */
    public User getUserIfRegistered(String username, String password) {
        for (User user : getRegisteredUsersList()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public void deleteUser(int userID) {
        String deleteUserQuery = "DELETE FROM " + USERS_TABLE + " WHERE " + COL_USER_ID + " = " + userID;
        this.getWritableDatabase().execSQL(deleteUserQuery);
    }

    public void deleteAllUsers() {
        String deleteAllUsersQuery = "DELETE FROM " + USERS_TABLE;
        this.getWritableDatabase().execSQL(deleteAllUsersQuery);
    }

    public void addCountryToFavourites(String countryName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_COUNTRY_NAME, countryName);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(FAV_COUNTRIES_TABLE, null, contentValues);
    }

    public void removeCountryFromFavourites(String countryName) {
        String removeCountryQuery = "DELETE FROM " + FAV_COUNTRIES_TABLE + " WHERE "
                + COL_COUNTRY_NAME + " = " + "\"" + countryName + "\"";

        this.getWritableDatabase().execSQL(removeCountryQuery);
    }

    public boolean isCountryFav(String countryName) {
        for (String name : getFavCountriesNames()) {
            if (name.equals(countryName)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> getFavCountriesNames() {
        ArrayList<String> countryNames = new ArrayList<>();

        String queryString = "SELECT * FROM " + FAV_COUNTRIES_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                countryNames.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return countryNames;
    }

    public ArrayList<Country> getFavCountriesList() {
        ArrayList<Country> favCountriesList = new ArrayList<>();

        for (String countryName : getFavCountriesNames()) {
            favCountriesList.add(QueryUtils.getCountryByName(countryName));
        }

        return favCountriesList;
    }

    public void addAttractionToFavourites(String attrName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ATTR_NAME, attrName);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(FAV_ATTRACTIONS_TABLE, null, contentValues);
    }

    public void removeAttractionFromFavourites(String attrName) {
        String removeAttrQuery = "DELETE FROM " + FAV_ATTRACTIONS_TABLE + " WHERE " +
                COL_ATTR_NAME + " = " + "\"" + attrName + "\"";

        this.getWritableDatabase().execSQL(removeAttrQuery);
    }

    public boolean isAttrFav(String attrName) {
        for (String name : getFavAttractionsNames()) {
            if (name.equals(attrName)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> getFavAttractionsNames() {
        ArrayList<String> favAttractionsNames = new ArrayList<>();

        String query = "SELECT * FROM " + FAV_ATTRACTIONS_TABLE;

        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                favAttractionsNames.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return favAttractionsNames;
    }

    public ArrayList<Attraction> getFavAttractionsList() {
        ArrayList<Attraction> favAttractionsList = new ArrayList<>();

        for (String attrName : getFavAttractionsNames()) {
            favAttractionsList.add(QueryUtils.getAttractionByName(attrName));
        }

        return favAttractionsList;
    }

    public void deleteAllFavCountries() {
        String deleteQuery = "DELETE FROM " + FAV_COUNTRIES_TABLE;
        this.getWritableDatabase().execSQL(deleteQuery);
    }

    public void deleteAllFavAttractions() {
        String deleteQuery = "DELETE FROM " + FAV_ATTRACTIONS_TABLE;
        this.getWritableDatabase().execSQL(deleteQuery);
    }

}
























