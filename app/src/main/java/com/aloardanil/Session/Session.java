package com.aloardanil.Session;

import android.content.Context;
import android.content.SharedPreferences;

import com.aloardanil.model.User;


public class Session {

    private Context mContext;
    private SharedPreferences prefLogin;
    private SharedPreferences.Editor sessionLogin;

    private static final String PREF_LOGIN = "Login";
    private static final String IS_LOGIN = "IsLogin";
    private static final String USERNAME = "username";

    public Session(Context context) {
        this.mContext = context;
        prefLogin = mContext.getSharedPreferences(PREF_LOGIN, 0);

        sessionLogin = prefLogin.edit();
    }

    public boolean setUserLogin(User user) {
        sessionLogin.putBoolean(IS_LOGIN, true);
        sessionLogin.putString(USERNAME, user.getUsername());
        sessionLogin.commit();
        return true;
    }

    public boolean isLogin() {
        return prefLogin.getBoolean(IS_LOGIN, false);
    }

    public void clearSession() {
        sessionLogin.clear();
        sessionLogin.commit();
    }
}