package com.vadelectronics.c_core.business.link;

public abstract class InjectionProvider {
    protected static InjectionProvider defaultInstance;

    public abstract <T> void bind(Class<T> clazz1, Class<? extends T> clazz2);

    public abstract void inject(Object target);

    public static InjectionProvider get() {
        if (defaultInstance == null) {
            throw new RuntimeException("Must initialize InjectionProvider before you can use it !");
        }
        return defaultInstance;
    }
}
