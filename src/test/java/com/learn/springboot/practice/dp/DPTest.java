package com.learn.springboot.practice.dp;

import com.learn.springboot.practice.dp.adapter.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计模式测试类
 */
@Slf4j
public class DPTest {
    @Test
    public void adapterTest() {
        List<DC5Adapter> adapters = new ArrayList<>();
        adapters.add(new ChinaPowerAdapter());
        adapters.add(new JapanPowerAdapter());

        AC chinaAC = new AC220();
        DC5Adapter adapter = getPowerAdapter(chinaAC, adapters);
        adapter.outputDC5V(chinaAC);

        // 去日本旅游，电压是 110V
        AC japanAC = new AC110();
        adapter = getPowerAdapter(japanAC, adapters);
        adapter.outputDC5V(japanAC);

    }

    // 根据电压找合适的变压器
    public DC5Adapter getPowerAdapter(AC ac, List<DC5Adapter> adapters) {
        DC5Adapter adapter = null;
        for (DC5Adapter ad : adapters) {
            if (ad.support(ac)) {
                adapter = ad;
                break;
            }
        }
        if (adapter == null) {
            throw new IllegalArgumentException("没有找到合适的变压适配器");
        }
        return adapter;
    }

}
