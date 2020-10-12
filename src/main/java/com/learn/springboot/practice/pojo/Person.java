package com.learn.springboot.practice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @ClassName Person
 * @Description:
 * @Author lfq
 * @Date 2020/10/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotNull(message = "personId不能为空")
    private Long personId;

    @NotBlank(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
    @NotBlank(message = "sex 不能为空")
    private String sex;

    @Email(message = "email 格式不正确")
    @NotBlank(message = "email 不能为空")
    private String email;

    @Max(message = "年龄不得超过35周岁", value = 35)
    @NotNull
    private Integer age;
}
