package com.example.rohan.f7;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OfflineMode extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
