package com.vadelectronics.relaytriggerapp;

import android.app.Application;
import android.content.Context;
import com.vadelectronics.b_thirdp.injection.ToothPickInjectionProvider;
import com.vadelectronics.c_core.business.link.InjectionProvider;
import com.vadelectronics.x_util.inject.InjectBinding;
import com.vadelectronics.x_util.inject.InjectConfig;
import javax.inject.Inject;

public class RelayApp extends Application {
    @Inject Context self;

    private static Package[] injectablePackages = new Package[] {
        com.vadelectronics.b_ui.BuildConfig.class.getPackage(),
        com.vadelectronics.b_thirdp.BuildConfig.class.getPackage(),
        com.vadelectronics.c_core.BuildConfig.class.getPackage()
    };

    @Override
    public void onCreate() {
        super.onCreate();
        configureInjection();
    }

    private void configureInjection() {
        ToothPickInjectionProvider.initialize(this);

        for (Package injectablePackage : injectablePackages) {
            InjectConfig injectConfig = injectablePackage.getAnnotation(InjectConfig.class);
            for (InjectBinding injectBinding : injectConfig.simpleBindings()) {
                InjectionProvider.get().bind(injectBinding.bind(), injectBinding.to());
            }
        }

        InjectionProvider.get().inject(this);
    }
}
