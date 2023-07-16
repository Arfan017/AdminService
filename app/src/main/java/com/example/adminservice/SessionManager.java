package com.example.adminservice;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String KEY_ROLE = "role";
    private static final String KEY_USERNAME = "username";
    private static final String PREF_NAME = "Session";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String role, String username) {
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getString(KEY_ROLE, null) != null;
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public String getRole() {
        return sharedPreferences.getString(KEY_ROLE, null);
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }
}
