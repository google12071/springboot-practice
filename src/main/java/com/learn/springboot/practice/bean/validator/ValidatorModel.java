package com.learn.springboot.practice.bean.validator;

import com.learn.springboot.practice.annotation.IdCardNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author lfq
 */
@Data
public class ValidatorModel {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "年龄不能为空")
    @Max(value = 30, message = "年龄不能超过30岁")
    @Min(value = 16, message = "年龄不能小于16岁")
    private Integer age;

    @AssertFalse(message = "必须为false")
    private Boolean isFalse;

    /**
     * 自定义校验
     */
    @NotBlank(message = "身份证号不能为空")
    @IdCardNumber(message = "身份证信息有误,请核对后提交")
    private String idCardNumber;

    @Valid
    @NotNull(message = "关联model不能为空")
    private RelevanceModel relevanceModel;

    @NotEmpty(message = "列表不能为空")
    private List<RelevanceModel> modelList;
}
