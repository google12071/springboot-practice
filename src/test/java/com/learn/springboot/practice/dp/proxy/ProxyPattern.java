package com.learn.springboot.practice.dp.proxy;

/**
 * 代理测试类
 */
public class ProxyPattern {
    public static void main(String[] args) {
        //包装代理
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }
}
