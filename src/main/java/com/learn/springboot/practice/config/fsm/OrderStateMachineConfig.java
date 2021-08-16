package com.learn.springboot.practice.config.fsm;

import com.learn.springboot.practice.enums.OrderEventEnum;
import com.learn.springboot.practice.enums.OrderStatusEnum;
import com.learn.springboot.practice.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 订单状态机配置
 *
 * @author lfq
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
@Slf4j
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum> {

    /**
     * 状态配置
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states.withStates().initial(OrderStatusEnum.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    /**
     * 状态转换配置
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
        transitions.withExternal().source(OrderStatusEnum.WAIT_PAYMENT).target(OrderStatusEnum.WAIT_DELIVER).event(OrderEventEnum.PAYED)
                .and().withExternal().source(OrderStatusEnum.WAIT_DELIVER).target(OrderStatusEnum.WAIT_RECEIVE).event(OrderEventEnum.DELIVERY)
                .and().withExternal().source(OrderStatusEnum.WAIT_RECEIVE).target(OrderStatusEnum.FINISH).event(OrderEventEnum.RECEIVED);
    }


    @Bean
    public DefaultStateMachinePersister persist() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<Object, Object, Order>() {
            @Override
            public void write(StateMachineContext<Object, Object> context, Order order) throws Exception {
                log.info("write...order:{}", order);
            }

            @Override
            public StateMachineContext<Object, Object> read(Order order) throws Exception {
                log.info("read...order:{}", order);
                return new DefaultStateMachineContext(order.getStatus(), null, null, null);
            }
        });
    }
}
