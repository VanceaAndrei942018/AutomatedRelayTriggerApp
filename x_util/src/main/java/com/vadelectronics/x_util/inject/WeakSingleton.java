package com.vadelectronics.x_util.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies a type that the injector only instantiates once but does not hold a strong reference towards,
 * so it may get garbage collected if not held strongly by other instances.
 * Not inherited.
 */

@Documented
@Retention(RUNTIME)
public @interface WeakSingleton {
}
