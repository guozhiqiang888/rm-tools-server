/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2017. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.service.impl;

import com.farben.readme.service.ICacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements ICacheService {

    private static CacheManager cacheManager = new CacheManager();


    /*
     * (non-Javadoc)
     *
     * @see
     * com.hsbc.gbbp.wechat.bsl.services.ICacheService#put(java.lang.String,
     * java.lang.String, java.lang.Object)
     */
    public void put(final String cacheName, final String key, final Object val) {
        Cache cache = CacheServiceImpl.cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(new Element(key, val));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.hsbc.gbbp.wechat.bsl.services.ICacheService#get(java.lang.String,
     * java.lang.String)
     */
    public Object get(final String cacheName, final String key) {
        Cache cache = CacheServiceImpl.cacheManager.getCache(cacheName);
        if (cache != null) {
            Element element = cache.get(key);
            if (element != null) {
                return element.getObjectValue();
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.hsbc.gbbp.wechat.bsl.services.ICacheService#remove(java.lang.String,
     * java.lang.String)
     */
    public void remove(final String cacheName, final String key) {
        Cache cache = CacheServiceImpl.cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.remove(key);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.hsbc.gbbp.wechat.bsl.services.ICacheService#put(java.lang.String,
     * java.lang.String, java.lang.Object, int)
     */
    public void put(final String cacheName, final String key, final Object val, final int timeout) {
        Cache cache = CacheServiceImpl.cacheManager.getCache(cacheName);
        if (cache != null) {
            Element element = new Element(key, val);
            element.setTimeToLive(timeout);
            cache.put(element);
        }
    }

}
