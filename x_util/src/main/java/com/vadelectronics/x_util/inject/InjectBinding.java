package com.vadelectronics.x_util.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectBinding {
    Class bind();

    Class to();
}
