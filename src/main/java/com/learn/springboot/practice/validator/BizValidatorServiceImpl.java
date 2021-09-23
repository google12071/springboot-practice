package com.learn.springboot.practice.validator;

import com.learn.springboot.practice.annotation.ParamValidate;
import com.learn.springboot.practice.bean.validator.ValidatorModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author lfq
 */
@Service
@Validated
public class BizValidatorServiceImpl implements BizValidatorService {
    @Override
    @ParamValidate
    public boolean saveModel(@Valid ValidatorModel model) {
        return false;
    }

    @Override
    public ValidatorModel queryById(@Max(value = 10, message = "id不能超过10")
                                    @NotNull(message = "id不能为空") Integer id) {
        return null;
    }
}
