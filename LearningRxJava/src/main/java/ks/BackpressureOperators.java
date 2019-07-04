package ks;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class BackpressureOperators extends Demonstration {
    public static void main(String[] args) {
//        demonstrate("Implement backpressure strategy BUFFER downstream", BackpressureOperators::backpressureDownstream);
//        demonstrate("Implement backpressure overvlow DROP_LAST strategy", BackpressureOperators::backpressure_overflowStrategy);
        demonstrate("Implement backpressure strategy LATEST downstream", BackpressureOperators::backpressure_latest);
//        demonstrate("Implement backpressure strategy DROP downstream", BackpressureOperators::backpressure_drop);
    }

    private static Disposable backpressureDownstream() {
        Disposable d = Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer()
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    sleep(5);
                    System.out.println(i);
                });
        sleep(5000);

        return d;
    }

    private static Disposable backpressure_overflowStrategy() {
        Disposable d = Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureBuffer(10,
                        () -> System.out.println("overflow!"),
                        BackpressureOverflowStrategy.DROP_LATEST)
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    sleep(5);
                    System.out.println(i);
                });
        sleep(5000);
        return d;
    }

    private static Disposable backpressure_latest() {
        Disposable d = Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    sleep(5);
                    System.out.println(i);
                });
        sleep(5000);
        return d;
    }

    private static Disposable backpressure_drop() {
        Disposable d = Flowable.interval(1, TimeUnit.MILLISECONDS)
                .onBackpressureDrop(i -> System.out.println("Dropping " + i))
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    sleep(5);
                    System.out.println(i);
                });
        sleep(5000);
        return d;
    }
}
