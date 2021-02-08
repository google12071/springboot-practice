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

    @NotNull(message = "员工ID不能为空")
    private Long personId;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "性别必须为男/女")
    @NotBlank(message = "性别不能为空")
    private String sex;

    @Email(message = "Email格式不正确")
    @NotBlank(message = "Email不能为空")
    private String email;

    @Max(message = "年龄不得超过35周岁", value = 35)
    @NotNull(message = "年龄不能为空")
    private Integer age;
}
