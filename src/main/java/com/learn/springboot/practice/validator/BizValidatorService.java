package com.learn.springboot.practice.validator;

import com.learn.springboot.practice.bean.validator.ValidatorModel;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public interface BizValidatorService {
    /**
     *
     * @param model
     * @return
     */
    boolean saveModel( @Valid ValidatorModel model);

    ValidatorModel queryById(@Max(value = 10, message = "id不能超过10")
                             @NotNull(message = "id不能为空") Integer id);
}
