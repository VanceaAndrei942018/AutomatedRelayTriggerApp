package com.vadelectronics.b_store;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.vadelectronics.c_core.business.link.PersistantStorage;
import javax.inject.Inject;

public class SimplePersistedStorage implements PersistantStorage {

    public static final String SHARED_PREFS_NAME = "shared.prefs.name";

    @Inject Application applicationContext;

    private SharedPreferences sharedPreferences;

    private SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    @Override
    public void saveToStorage(String something) {

    }
}
