package com.learn.springboot.practice.dp.chain;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 责任链调用示例: ManagerHandler->DirectorHandler->CEOHandler
 */
@Slf4j
public class ChainClient {
    /**
     * 处理器链
     */
    private List<ChainHandler> handlerList = new ArrayList<>();

    public void addHandler(ChainHandler handler) {
        handlerList.add(handler);
    }

    /**
     * 遍历看是否
     *
     * @param request
     * @return
     */
    public boolean process(ChainRequest request) {
        for (ChainHandler handler : handlerList) {
            Boolean r = handler.execute(request);
            if (r != null) {
                System.out.println(request + " " + (r ? "Approved by " : "Denied by ") + handler.getClass().getSimpleName());
                return r;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ChainClient client = new ChainClient();
        client.addHandler(new ManagerHandler());
        client.addHandler(new DirectorHandler());
        client.addHandler(new CEOHandler());

        client.process(new ChainRequest("Bob", new BigDecimal("123.45")));
        client.process(new ChainRequest("Alice", new BigDecimal("1234.56")));
        client.process(new ChainRequest("Bill", new BigDecimal("12345.67")));
        client.process(new ChainRequest("John", new BigDecimal("123456.78")));
    }
}
