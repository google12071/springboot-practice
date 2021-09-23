package com.learn.springboot.practice.bean.validator;

import com.learn.springboot.practice.annotation.Sex;
import com.learn.springboot.practice.model.constant.SexEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 关联model
 * @author lfq
 */
@Data
public class RelevanceModel {
    @Sex
    private SexEnum sex;

    @NotEmpty(message = "值列表不能为空")
    private List<Integer> numberList;

    @NotBlank(message = "值不能为空")
    private String value;
}
