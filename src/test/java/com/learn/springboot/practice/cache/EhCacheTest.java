package com.learn.springboot.practice.cache;

import lombok.extern.slf4j.Slf4j;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.jupiter.api.Test;


/**
 * Ehcache测试类
 * Ehcache是Hibernate默认的缓存框架，是轻量级的Java进程级缓存框架
 */
@Slf4j
public class EhCacheTest {

    /**
     * 使用Ehcache默认配置(classpath下的ehcache.xml)获取单例的CacheManager实例
     */
    @Test
    public void operation() {
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("./src/main/resources/cache/ehcache.xml");

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("ehCache");

        // 3. 创建元素
        Element element = new Element("key1", "value1");
        Element num=new Element("score",100);

        // 4. 将元素添加到缓存
        cache.put(element);
        cache.put(num);

        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());
        System.out.println(cache.get("score").getObjectValue());

        // 6. 删除元素
        cache.remove("key1");

        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();

    }
}
