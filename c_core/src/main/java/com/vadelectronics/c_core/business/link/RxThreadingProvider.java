package com.vadelectronics.c_core.business.link;

import com.google.common.reflect.TypeToken;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import javax.inject.Singleton;

/**
 * Interface of component that provides rx threading policies
 */
@Singleton
public interface RxThreadingProvider {
    <T> ObservableTransformer<T, T> getIoThreadingPolicy(Class<T> type);

    <T> ObservableTransformer<T, T> getIoThreadingPolicy(TypeToken<T> typeToken);

    <T> ObservableTransformer<T, T> getThreadSafeThreadingPolicy(Class<T> type);

    <T> ObservableTransformer<T, T> getThreadSafeThreadingPolicy(TypeToken<T> typeToken);

    <T> ObservableTransformer<T, T> getComputationThreadingPolicy(Class<T> type);

    <T> ObservableTransformer<T, T> getComputationThreadingPolicy(TypeToken<T> typeToken);

    Observable<Long> getRepeatingPolicy(long delay, long timeIntervalSeconds);
}
