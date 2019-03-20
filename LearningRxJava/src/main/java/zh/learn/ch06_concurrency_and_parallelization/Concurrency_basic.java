package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

import static zh.learn.Demonstration.*;

public class Concurrency_basic {
    public static void main(String[] args) {
        demonstrateWithTimeGauge("Running intensive calculation tasks sequentially", Concurrency_basic::runIntensiveCalculationTasksSequentially);
        demonstrateWithTimeGauge("Running intensive calculation tasks in parallel", Concurrency_basic::runIntensiveCalculationTasksInParallel);
        demonstrateWithTimeGauge("Zip concurrent streams", Concurrency_basic::zipConcurrentStreams);
    }

    private static void runIntensiveCalculationTasksSequentially() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(Demonstration::intenseCalculation)
                .subscribe(System.out::println);

        Observable.range(1, 6)
                .map(Demonstration::intenseCalculation)
                .subscribe(System.out::println);
    }

    private static void runIntensiveCalculationTasksInParallel() {
        Disposable d1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation)
                .subscribe(System.out::println);

        Disposable d2 = Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation)
                .subscribe(System.out::println);

        sleepUntil(() -> DisposableArray.of(d1, d2).isDisposed(), 20000);
    }

    private static void zipConcurrentStreams() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation);

        Observable<Integer> source2 = Observable.range(1, 6)
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation);

        Disposable d = Observable.zip(source1, source2, (s, i) -> s + "-" + i)
                .subscribe(System.out::println);

        sleepUntil(d::isDisposed, 20000);
    }
}
