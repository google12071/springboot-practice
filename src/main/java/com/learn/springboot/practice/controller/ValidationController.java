package com.learn.springboot.practice.controller;

import com.learn.springboot.practice.pojo.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ValidationController
 * @Description:
 * @Author lfq
 * @Date 2020/10/12
 **/
@RestController
@RequestMapping("/validate")
@Validated
public class ValidationController {
    @PostMapping("/checkBody")
    public ResponseEntity<Person> checkPerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok().body(person);
    }

    @GetMapping("/checkParam")
    public ResponseEntity<Map<String, String>> checkParam(@Valid @RequestParam("id")  @Max(value = 5, message = "超过id的范围了") Integer id,
                                                          @Valid @RequestParam("name") @Size(max = 3, message = "超过name的范围了") String name) {
        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        param.put("name", name);
        return ResponseEntity.ok().body(param);
    }
}
