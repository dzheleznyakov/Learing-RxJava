package ks;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

import java.time.LocalTime;

public class Parallelization extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Running intense calculations sequentially", Parallelization::runningIntenseCalculationsSequentially);
//        demonstrate("Running intense calculations in parallel", Parallelization::runningIntenseCalculationInParallel);
    }

    private static Disposable runningIntenseCalculationsSequentially() {
        return Observable.range(1, 10)
                .map(Parallelization::intenseCalculation)
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now()));
    }

    private static Disposable runningIntenseCalculationInParallel() {
        Disposable d = Observable.range(1, 10)
                .flatMap(i -> Observable.just(i)
                        .subscribeOn(Schedulers.computation())
                        .map(Parallelization::intenseCalculation)
                )
                .subscribe(i -> System.out.println("Received " + i + " " + LocalTime.now() + " on thread " +
                        Thread.currentThread().getName()));

        sleep(20000);
        return d;
    }
}
