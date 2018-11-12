package com.vadelectronics.c_core.business.usecase;

import com.vadelectronics.c_core.business.link.PersistantStorage;
import javax.inject.Inject;

public class TestUseCase {

    @Inject PersistantStorage persistantStorage;

    public void saveToStorage(String something) {
        persistantStorage.saveToStorage(something);
    }
}
