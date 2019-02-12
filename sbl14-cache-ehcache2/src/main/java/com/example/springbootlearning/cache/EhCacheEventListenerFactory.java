package com.example.springbootlearning.cache;

import java.util.Properties;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class EhCacheEventListenerFactory extends CacheEventListenerFactory {

    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return new EhCacheEventLogger();
    }

    private static class EhCacheEventLogger implements CacheEventListener {

        private enum EventType {
            EVICTED, EXPIRED, REMOVED, CREATED, UPDATED, REMOVE_ALL
        }

        @Override
        public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
            this.log(EventType.REMOVED, cache, element);
        }

        @Override
        public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
            this.log(EventType.CREATED, cache, element);
        }

        @Override
        public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
            this.log(EventType.UPDATED, cache, element);
        }

        @Override
        public void notifyElementExpired(Ehcache cache, Element element) {
            this.log(EventType.EXPIRED, cache, element);
        }

        @Override
        public void notifyElementEvicted(Ehcache cache, Element element) {
            this.log(EventType.EVICTED, cache, element);
        }

        @Override
        public void notifyRemoveAll(Ehcache cache) {
            this.log(EventType.REMOVE_ALL, cache, null);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this;
        }

        @Override
        public void dispose() {
        }

        private void log(EventType type, Ehcache cache, Element element) {
            if (EventType.REMOVE_ALL.equals(type)) {
                System.out.println(type + " on " + cache.getName());
            } else {
                System.out.println(type + " on " + cache.getName() + ": key=" + element.getObjectKey() + ", value=" + element.getObjectValue());
            }
        }
    }
}
