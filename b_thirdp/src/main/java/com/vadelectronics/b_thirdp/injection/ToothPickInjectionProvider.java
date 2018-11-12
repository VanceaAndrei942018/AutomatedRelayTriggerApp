package com.vadelectronics.b_thirdp.injection;

import android.app.Application;
import android.content.Context;
import com.vadelectronics.c_core.business.link.InjectionProvider;
import com.vadelectronics.x_util.inject.WeakSingleton;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import toothpick.Factory;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistry;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistry;
import toothpick.registries.MemberInjectorRegistryLocator;

/**
 * The toothpick based implementation of the injection provider
 */
public class ToothPickInjectionProvider extends InjectionProvider {
    private static final String SCOPE_NAME = "Replica Injection Scope";
    private final Module reusableModule = new Module();
    private Scope scope;

    public static void initialize(Application applicationContext) {
        defaultInstance = new ToothPickInjectionProvider(applicationContext);
    }

    private ToothPickInjectionProvider(Application applicationContext) {
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());

        String factoryRegistryName = applicationContext.getPackageName() + ".FactoryRegistry";
        String memberInjectorRegistryName = applicationContext.getPackageName() + ".MemberInjectorRegistry";

        try {
            FactoryRegistry factoryRegistry = (FactoryRegistry) Class.forName(factoryRegistryName).newInstance();
            FactoryRegistryLocator.setRootRegistry(new WeakSingletonFactoryProxy(factoryRegistry));
            MemberInjectorRegistryLocator.setRootRegistry((MemberInjectorRegistry) Class.forName(memberInjectorRegistryName).newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Toothpick registry error !", e);
        }
        scope = Toothpick.openScope(SCOPE_NAME);
        reusableModule.bind(Context.class).toInstance(applicationContext);
        reusableModule.bind(Application.class).toInstance(applicationContext);
        scope.installModules(reusableModule);
    }

    @Override
    public <T> void bind(Class<T> clazz1, Class<? extends T> clazz2) {
        reusableModule.bind(clazz1).to(clazz2);
        scope.installModules(reusableModule);
    }

    @Override
    public void inject(Object target) {
        Toothpick.inject(target, scope);
    }

    /**
     * Proxy for the factory registry, that will give out weak singleton factory wrappers where needed
     * In order to provide weak singleton instances for classes annotated with WeakSingleton
     */
    private class WeakSingletonFactoryProxy implements FactoryRegistry {
        FactoryRegistry originalFactoryRegistry;

        public WeakSingletonFactoryProxy(FactoryRegistry originalFactoryRegistry) {
            this.originalFactoryRegistry = originalFactoryRegistry;
        }

        @Override
        public <T> Factory<T> getFactory(Class<T> clazz) {
            Factory<T> originalFactory = originalFactoryRegistry.getFactory(clazz);
            return clazz.isAnnotationPresent(WeakSingleton.class)
                ? new WeakSingletonFactoryWrapper<>(originalFactory, clazz)
                : originalFactory;
        }
    }

    /**
     * Factory that will create weak singleton instances for a given class
     *
     * @param <T> the given class type
     */
    private static class WeakSingletonFactoryWrapper<T> implements Factory<T> {
        private static Map<Class, WeakReference> weakSingletonInstances = new HashMap<>();

        private Class<T> clazz;
        private Factory<T> originalFactory;

        public WeakSingletonFactoryWrapper(Factory<T> originalFactory, Class<T> clazz) {
            this.originalFactory = originalFactory;
            this.clazz = clazz;
        }

        @Override
        public T createInstance(Scope scope) {
            T instance;
            try {
                if ((instance = weakSingletonInstances.containsKey(clazz) ? (T) weakSingletonInstances.get(clazz).get() : null) == null) {
                    instance = originalFactory.createInstance(scope);
                    weakSingletonInstances.put(clazz, new WeakReference<>(instance));
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to create " + clazz.getName(), e);
            }
            return instance;
        }

        @Override
        public Scope getTargetScope(Scope scope) {
            return originalFactory.getTargetScope(scope);
        }

        @Override
        public boolean hasScopeAnnotation() {
            return originalFactory.hasScopeAnnotation();
        }

        @Override
        public boolean hasProvidesSingletonInScopeAnnotation() {
            return originalFactory.hasProvidesSingletonInScopeAnnotation();
        }
    }
}
