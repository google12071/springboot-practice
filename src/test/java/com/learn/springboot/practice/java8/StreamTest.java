package com.learn.springboot.practice.java8;

import com.learn.springboot.practice.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
public class StreamTest {
    private List<Student> studentList = new ArrayList<>();

    @Before
    public void init() {
        studentList.add(new Student(1, 1, "张三"));
        studentList.add(new Student(2, 1, "李四"));
        studentList.add(new Student(3, 1, "王五"));
        studentList.add(new Student(4, 1, "马六"));
        studentList.add(new Student(5, 2, "陈七"));
        studentList.add(new Student(6, 3, "刘八"));
        studentList.add(new Student(7, 3, "赵九"));
        studentList.add(new Student(8, 3, "钱十"));
        studentList.add(new Student(9, 4, "周十一"));
        studentList.add(new Student(10, 4, "白十二"));
        studentList.add(new Student(11, 4, "付十三"));
        studentList.add(new Student(12, 4, "艾十四"));
    }

    @Test
    public void groupBy(){
        Map<Integer, List<Student>> map = studentList.stream().collect(Collectors.groupingBy(Student::getTeachId));
        System.out.println(map);

        Map<Integer, Long> countMap = studentList.stream().collect(Collectors.groupingBy(Student::getTeachId, Collectors.counting()));
        System.out.println(countMap);
    }
}
