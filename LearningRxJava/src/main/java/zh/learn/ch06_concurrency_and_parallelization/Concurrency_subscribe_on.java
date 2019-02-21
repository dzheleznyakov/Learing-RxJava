package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

import java.util.concurrent.ThreadLocalRandom;

public class Concurrency_subscribe_on extends Demonstration {
    public static void main(String[] args) {
        demonstrate("subscribeOn()", Concurrency_subscribe_on::demonstrateSubscribeOn);
        demonstrate("Subscribe on the same computation thread", Concurrency_subscribe_on::subscribeOnTheSameThread);
    }

    private static Disposable demonstrateSubscribeOn() {
        Observable<Integer> lengths = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation)
                .map(String::length);

        Disposable d1 = lengths.subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));
        Disposable d2 = lengths.subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));

        sleep(10000);

        return DisposableArray.of(d1, d2);
    }

    private static Disposable subscribeOnTheSameThread() {
        Observable<Integer> lengths = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation)
                .map(String::length)
                .publish()
                .autoConnect(2);

        Disposable d1 = lengths.subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));
        Disposable d2 = lengths.subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));

        sleep(10000);

        return DisposableArray.of(d1, d2);
    }
}
