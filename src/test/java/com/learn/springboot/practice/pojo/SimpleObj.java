package com.learn.springboot.practice.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象逻辑相等时候，需要同时重写equals与hashCode方法
 * <p>
 * 相同的对象hashcode必须相同，相同的hashCode则对象不一定是同一个对象
 */
@Data
public class SimpleObj {
    private Integer value;
    private String str;
    private Boolean flag;

    public SimpleObj(Integer value, String str, boolean flag) {
        this.value = value;
        this.str = str;
        this.flag = flag;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof SimpleObj) {
            SimpleObj simpleObj = (SimpleObj) obj;
            return same(value, simpleObj.getValue())
                    && same(str, simpleObj.getStr())
                    && same(flag, simpleObj.getFlag());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (value == null ? 0 : value);
        result = 31 * result + (flag ? 1 : 0);
        result = 31 * result + (str == null ? 0 : str.hashCode());
        return result;
    }

    private boolean same(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    public static void main(String[] args) {
        SimpleObj obj1 = new SimpleObj(null, "test", true);
        SimpleObj obj2 = new SimpleObj(1, "test", true);
        Map<SimpleObj, Integer> map = new HashMap<>();
        map.put(obj1, 1);
        map.put(obj2, 2);
        System.out.println(obj1.equals(obj2));
    }
}
