package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Concurrency_schedulers extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Custom scheduler from an executor service", Concurrency_schedulers::customScheduler);
    }

    private static void customScheduler() {
        int numberOfThreads = 20;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Scheduler scheduler = Schedulers.from(executor);

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(scheduler)
                .doFinally(executor::shutdown)
                .blockingSubscribe(System.out::println);
    }
}
