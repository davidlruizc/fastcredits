package com.example.fastcredits.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceStore {
    // email, password, store session, remember user
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String PERSIST_SESSION = "PERSIST_SESSION";
    public static final String PERSIST_USER = "PERSIST_USER";
    public static final String PERSIST_PASSWORD = "PERSIST_PASSWORD";

    public static boolean setEmailPassword(String email, String password, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.apply();
        return true;
    }

    public static boolean setPersistSession(Boolean persisSession, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PERSIST_SESSION, persisSession);
        editor.apply();
        return true;
    }

    public static Boolean getPersistSession(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PERSIST_SESSION, false);
    }

    // persist the user and password when check remember user on checkbox
    public static boolean setPersistCredentials(String email, String password, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PERSIST_USER, email);
        editor.putString(PERSIST_PASSWORD, password);
        editor.apply();
        return true;
    }

    public static String getPersistUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PERSIST_USER, null);
    }

    public static String getPersistPassword(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PERSIST_PASSWORD, null);
    }

    public static void clearSession(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(EMAIL);
        editor.remove(PASSWORD);
        editor.apply();
    }
}
