package com.learn.springboot.practice.pojo;

import com.learn.springboot.practice.annotation.IdCardNumber;
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

    @Pattern(regexp = "((^男$|^女$|^未知$))", message = "性别不正确")
    @NotBlank(message = "性别不能为空")
    private String sex;

    @Email(message = "Email格式不正确")
    @NotBlank(message = "Email不能为空")
    private String email;

    @Max(message = "年龄不得超过35周岁", value = 35)
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "身份证号不能为空")
    @IdCardNumber(message = "身份证信息有误,请核对后提交")
    private String idCardNumber;
}
