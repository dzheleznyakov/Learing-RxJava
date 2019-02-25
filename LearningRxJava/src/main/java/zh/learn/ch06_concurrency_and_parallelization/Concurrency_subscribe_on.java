package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

import java.util.concurrent.TimeUnit;

public class Concurrency_subscribe_on extends Demonstration {
    public static void main(String[] args) {
        demonstrate("subscribeOn()", Concurrency_subscribe_on::demonstrateSubscribeOn);
        demonstrate("Subscribe on the same computation thread", Concurrency_subscribe_on::subscribeOnTheSameThread);
        demonstrate("Subscribe on a IO thread", Concurrency_subscribe_on::subscribeOnIOScheduler);
        demonstrate("Observable interval accepts schedulers on creation", Concurrency_subscribe_on::observableIntervalAcceptsSchedulersOnCreation);
        demonstrate("First subscribeOn wins", Concurrency_subscribe_on::firstSubscribeOnWins);
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

    private static Disposable observableIntervalAcceptsSchedulersOnCreation() {
        Disposable d = Observable.interval(1, TimeUnit.SECONDS, Schedulers.newThread())
                .subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));

        sleep(5000);

        return d;
    }

    private static Disposable firstSubscribeOnWins() {
        Disposable d = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .filter(s -> s.length() == 5)
                .subscribeOn(Schedulers.io())
                .subscribe(i -> System.out.println("Received " + i + " on thread " + Thread.currentThread().getName()));

        sleep(5000);

        return d;
    }
}
