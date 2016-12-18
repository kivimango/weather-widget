package kivimango.weatherwidget.model;

import java.util.*;

public class Cache {

    private static Cache instance;
    private static Object monitor = new Object();
    private static Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());

    private Cache() {
    }

    public static void put(String cacheKey, Object value) {
        cache.put(cacheKey, value);
    }

    public Object get(String cacheKey) {
        return cache.get(cacheKey);
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static Cache getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new Cache();
                }
            }
        }
        return instance;
    }

}