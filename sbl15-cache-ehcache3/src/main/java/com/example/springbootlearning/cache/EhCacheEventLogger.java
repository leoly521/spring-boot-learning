package com.example.springbootlearning.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class EhCacheEventLogger implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<?, ?> event) {
        System.out.println(event);
    }
}
