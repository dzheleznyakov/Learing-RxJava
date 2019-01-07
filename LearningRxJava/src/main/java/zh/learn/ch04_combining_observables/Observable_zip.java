package zh.learn.ch04_combining_observables;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_zip {
    public static void main(String[] args) {
        demonstrate("Pair two sources", Observable_zip::simpleZip);
        demonstrate("Pair two sources (operator)", Observable_zip::simpleZipWithOperator);
        demonstrate("Slow down emissions with zip", Observable_zip::slowDownEmissions);
    }

    private static Disposable simpleZip() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 6);
        return Observable.zip(source1, source2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);
    }

    private static Disposable simpleZipWithOperator() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 6);
        return source1.zipWith(source2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);
    }

    private static Disposable slowDownEmissions() {
        Observable<String> strings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = Observable.zip(strings, seconds, (s, l) -> s)
                .subscribe(s -> System.out.println("Received " + s + " at " + LocalTime.now()));
        sleep(6000);
        return disposable;
    }
}
