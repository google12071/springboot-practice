package com.learn.springboot.practice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lfq
 */
@Slf4j
@RestController
@RequestMapping("/metric")
public class MetricController {
    @GetMapping("/pull")
    public String pull() {
        return "pull success";
    }
}
