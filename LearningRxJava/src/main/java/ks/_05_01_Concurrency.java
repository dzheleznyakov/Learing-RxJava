package ks;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

public class _05_01_Concurrency extends Demonstration {
    public static void main(String[] args) {
        demonstrate("subscribeOn()", _05_01_Concurrency::demonstrateSubscribeOn);
    }

    private static Disposable demonstrateSubscribeOn() {
        Observable<Integer> lengths = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.io())
                .map(s -> {
                    System.out.println("Received " + s + " on thread " + Thread.currentThread().getName());
                    return s;
                })
                .observeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation)
                .map(String::length);

        Disposable d1 = lengths.subscribe(i -> System.out.println("(1) Received " + i + " on thread " + Thread.currentThread().getName()));
//        Disposable d2 = lengths.subscribe(i -> System.out.println("(2) Received " + i + " on thread " + Thread.currentThread().getName()));

        sleep(10000);

        return DisposableArray.of(d1);
    }
}
