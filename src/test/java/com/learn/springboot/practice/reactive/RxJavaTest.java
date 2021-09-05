package com.learn.springboot.practice.reactive;

import com.learn.springboot.practice.BaseTest;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * RxJava2响应式编程测试
 */
@Slf4j
public class RxJavaTest extends BaseTest {

    @Test
    public void quickStart() {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                log.info("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                log.info("next:{}", value);
            }

            @Override
            public void onError(Throwable e) {
                log.error("error:{}", e);
            }

            @Override
            public void onComplete() {
                log.info("complete");
            }
        };
        //建立连接
        observable.subscribe(observer);

    }
}
