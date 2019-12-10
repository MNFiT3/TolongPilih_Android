package com.missfortune.tolongpilih.services;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences("app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void isLoggedIn(boolean isLogged, String userEmail, String token){
        editor.putBoolean("loggedInMode", isLogged);
        editor.putString("userEmail", userEmail);
        editor.putString("token", token);
        editor.commit();
    }

    public boolean loggedIn() {
        return sharedPreferences.getBoolean("loggedInMode", false);
    }

    public String userEmail() {
        return sharedPreferences.getString("userEmail", "");
    }
}
