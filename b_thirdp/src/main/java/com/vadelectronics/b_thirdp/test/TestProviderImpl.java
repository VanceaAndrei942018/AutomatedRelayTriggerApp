package com.vadelectronics.b_thirdp.test;

import android.app.Application;
import com.vadelectronics.c_core.business.link.TestProvider;
import javax.inject.Inject;

public class TestProviderImpl implements TestProvider {

    @Inject Application applicationContext;

    @Override
    public void doSomething() {
        System.out.println(applicationContext.getPackageName());
    }
}
