package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Parallelization_basic extends Demonstration {
    public static void main(String[] args) {
        demonstrate("No parallelization", Parallelization_basic::noParallelization);
        demonstrate("Use flatMap for parallelization", Parallelization_basic::flatMapForParallelization);
        demonstrate("Parallelization with manual assignment", Parallelization_basic::smarterParallelization);
    }

    private static Disposable noParallelization() {
        return Observable.range(1, 10)
                .map(i -> intenseCalculation(i))
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now()));
    }

    private static Disposable flatMapForParallelization() {
        Disposable d = Observable.range(1, 10)
                .flatMap(i -> Observable.just(i)
                        .subscribeOn(Schedulers.computation())
                        .map(i2 -> intenseCalculation(i2))
                )
                .subscribe(i -> System.out.println("Received " + i + " "
                        + LocalTime.now() + " on thread " + Thread.currentThread().getName()));

        sleepWhile(d::isDisposed, 20000);

        return d;
    }

    private static Disposable smarterParallelization() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        AtomicInteger assigner = new AtomicInteger(0);

        Disposable d = Observable.range(1, 10)
                .groupBy(i -> assigner.incrementAndGet() % coreCount)
                .flatMap(grp -> grp.observeOn(Schedulers.io())
                        .map(i2 -> intenseCalculation(i2))
                )
                .subscribe(i -> System.out.println("Received " + i + " "
                        + LocalTime.now() + " on thread "
                        + Thread.currentThread().getName()));

        sleepWhile(d::isDisposed, 20000);

        return d;
    }
}
