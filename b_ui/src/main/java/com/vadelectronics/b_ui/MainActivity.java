package com.vadelectronics.b_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.vadelectronics.c_core.business.link.InjectionProvider;
import com.vadelectronics.c_core.business.usecase.TestUseCase;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject TestUseCase testUseCase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        InjectionProvider.get().inject(this);

        testUseCase.saveToStorage("Something");
    }
}
