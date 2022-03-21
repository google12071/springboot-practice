package com.learn.springboot.practice.metric;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @author lfq
 */
@Component
@Slf4j
public class MetricCollector implements MeterBinder {
    private int metricSize = 100;
    private int labelSize = 400;
    private MeterRegistry registry;
    private static Set<String> metrics = new HashSet<>();
    private Map<String, Set<String>> metricMap = new ConcurrentHashMap<>();

    @Override
    public void bindTo(MeterRegistry registry) {
        this.registry = registry;
    }

    /**
     * 每个指标label组合数量不得超过400个
     *
     * @param metricName
     * @param suppler
     * @param description
     * @param tagMap
     */
    public void addMetric(String metricName, Supplier<Number> suppler, String description, Map<String, String> tagMap) {

        List<String> tags = new ArrayList<>();
        tagMap.forEach((tagName, tagValue) -> {
            tags.add(tagName);
            tags.add(tagValue);
        });

        StringBuffer stringBuffer = new StringBuffer();
        tagMap.values().forEach(value -> stringBuffer.append(value));
        String labelValues = stringBuffer.toString();

        synchronized (MetricCollector.class) {
            if (metrics.size() >= metricSize) {
                return;
            }
            metrics.add(metricName);
            Set<String> set = metricMap.computeIfAbsent(metricName, (x) -> new HashSet<>());
            set.add(labelValues);
            if (set.size() > labelSize) {
                set.remove(labelValues);
                return;
            }
        }

        String[] tagArr = new String[tags.size()];
        tags.toArray(tagArr);
        Gauge.builder(metricName, suppler).tags(tagArr)
                .description(description).register(registry);
    }

    public AtomicInteger counter(String metricName, String description, Map<String, String> tagMap) {
        AtomicInteger count = new AtomicInteger(0);
        this.addMetric(metricName, () -> count, description, tagMap);
        return count;
    }
}
