package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Concurrency_subscribe_on extends Demonstration {
    public static void main(String[] args) {
        demonstrate("subscribeOn()", Concurrency_subscribe_on::demonstrateSubscribeOn);
        demonstrate("Subscribe on the same computation thread", Concurrency_subscribe_on::subscribeOnTheSameThread);
        demonstrate("Subsribe on a IO thread", Concurrency_subscribe_on::subscribeOnIOScheduler);
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

    private static Disposable subscribeOnIOScheduler() {
        Disposable d = Observable.fromCallable(() -> getResponse("https://api.github.com/users/thomasnield"))
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println);

        sleep(10000);

        return d;
    }

    private static String getResponse(String path) {
        try {
            return new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
