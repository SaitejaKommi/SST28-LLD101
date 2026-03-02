package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Thread-safe, reflection-safe, serialization-safe Singleton.
 *
 * Uses the static holder pattern for lazy initialization and thread safety.
 * The constructor is private and checks against reflection attacks.
 * readResolve() ensures deserialization returns the same singleton instance.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    /**
     * Private constructor that prevents construction after the singleton is initialized.
     * This blocks reflection-based attacks attempting to create multiple instances.
     */
    private MetricsRegistry() {
        // Prevent reflection from creating multiple instances
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already instantiated");
        }
    }

    /**
     * Returns the singleton instance using the static holder pattern.
     * This ensures:
     * - Lazy initialization (Holder loaded only when getInstance() is first called)
     * - Thread safety (class loader synchronizes)
     * - Reflection resistance (constructor check above)
     */
    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Static holder class for lazy initialization.
     * The class is loaded and instantiated only when getInstance() is first called,
     * ensuring true lazy initialization with no synchronization overhead.
     */
    private static class Holder {
        static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    /**
     * Ensure singleton is preserved during deserialization.
     * When an object is deserialized, this method is called and returns the
     * existing singleton instance instead of creating a new one.
     */
    @Serial
    private Object readResolve() {
        return getInstance();
    }
}