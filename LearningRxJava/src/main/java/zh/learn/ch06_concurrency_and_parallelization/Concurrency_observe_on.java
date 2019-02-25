package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

public class Concurrency_observe_on extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Understanding .observeOn()", Concurrency_observe_on::understandingObserveOn);
        demonstrate("Switch thread twice", Concurrency_observe_on::switchSchedulerTwice);
    }

    private static Disposable understandingObserveOn() {
        Disposable d = Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
                .subscribeOn(Schedulers.io())
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .observeOn(Schedulers.computation())
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .reduce((total, next) -> total + next)
                .subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));

        sleep(1000);

        return d;
    }

    private static Disposable switchSchedulerTwice() {
        Disposable d = Observable.just("WHISKEY/27653/TANGO", "6555/BRAVO", "232352/5675675/FOXTROT")
                .subscribeOn(Schedulers.io())
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .doOnNext(s -> System.out.println("Split out " + s + " on thread " + Thread.currentThread().getName()))

                .observeOn(Schedulers.computation())
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Calculate sum " + i + " on thread " + Thread.currentThread().getName()))

                .observeOn(Schedulers.io())
                .map(i -> i.toString())
                .doOnSuccess(s -> System.out.println("Writing " + s + " to file on thread " + Thread.currentThread().getName()))
                .subscribe(s -> write(s, "output.txt"));

        sleep(1000);

        return d;
    }
}
