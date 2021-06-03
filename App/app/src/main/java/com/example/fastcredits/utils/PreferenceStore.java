package com.example.fastcredits.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceStore {
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String ROLE = "ROLE";
    public static final String PERSIST_SESSION = "PERSIST_SESSION";
    public static final String PERSIST_CREDENTIALS = "PERSIST_CREDENTIALS";
    public static final String PERSIST_MONGO_ID = "PERSIST_MONGO_ID";

    public static boolean setEmailPassword(String email, String password, int role, String mongoId, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.putInt(ROLE,  role);
        editor.putString(PERSIST_MONGO_ID, mongoId);
        editor.apply();
        return true;
    }



    public static boolean setPersistSession(Boolean persistSession, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PERSIST_SESSION, persistSession);
        editor.apply();
        return true;
    }

    public static boolean setPersistCredentials(Boolean persistCredentials, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PERSIST_CREDENTIALS, persistCredentials);
        editor.apply();
        return true;
    }

    public static Boolean getPersistCredentials(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PERSIST_CREDENTIALS, false);
    }

    public static Boolean getPersistSession(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(PERSIST_SESSION, false);
    }


    public static String getPersistUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(EMAIL, null);
    }

    public static String getPersistPassword(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PASSWORD, null);
    }

    public static int getRolePersisted(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(ROLE, 5);
    }

    public static String getPersistMongoId(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PERSIST_MONGO_ID, null);
    }

    public static void clearMongoId(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(PERSIST_MONGO_ID);
    }

    public static void clearSession(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(EMAIL);
        editor.remove(PASSWORD);
        editor.apply();
    }
}
