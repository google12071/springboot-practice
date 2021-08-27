
package com.learn.springboot.practice.bean.impl;

import com.learn.springboot.practice.bean.MailService;
import com.learn.springboot.practice.bean.OnSmtpEnvCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
/**
 * @author lfq
 */
@Component
@Conditional(OnSmtpEnvCondition.class)
@Slf4j
public class SmtpMailServiceImpl implements MailService {
    @Override
    public void sendEmail(String message) {
        log.info("smtp,message:{}", message);
    }
}

