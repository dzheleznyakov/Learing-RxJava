package ks;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Throttling extends Demonstration {
    private static final Observable<String> source1 = Observable.interval(100, TimeUnit.MILLISECONDS)
            .map(i -> (i + 1) * 100)
            .map(i -> "SOURCE 1 " + i)
            .take(10);
    private static final Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
            .map(i -> (i + 1) * 300)
            .map(i -> "SOURCE 2 " + i)
            .take(3);
    private static final Observable<String> source3 = Observable.interval(2000, TimeUnit.MILLISECONDS)
            .map(i -> (i + 1) * 2000)
            .map(i -> "SOURCE 3 " + i)
            .take(2);

    public static void main(String[] args) {
        demonstrate("Example of throttling", Throttling::throttlingExample);
        demonstrate("Throttling: pick up the last event in the 1 second window", Throttling::throttleLastExample);
        demonstrate("Throttling: pick up the first event in the 1 second window", Throttling::throttleFirstExample);
        demonstrate("Throttling with 1 second timeout", Throttling::throttleWithTimeoutExample);
    }

    private static Disposable throttlingExample() {
        Disposable d = Observable.concat(source1, source2, source3)
                .subscribe(System.out::println);

        sleep(6000);
        return d;
    }

    private static Disposable throttleLastExample() {
        Disposable d = Observable.concat(source1, source2, source3)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        sleep(6000);
        return d;
    }

    private static Disposable throttleFirstExample() {
        Disposable d = Observable.concat(source1, source2, source3)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        sleep(6000);
        return d;
    }

    private static Disposable throttleWithTimeoutExample() {
        Disposable d = Observable.concat(source1, source2, source3)
                .throttleWithTimeout(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        sleep(6000);
        return d;
    }
}
