package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

public class Concurrency_keep_app_alive extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Block subscription till it expires", Concurrency_keep_app_alive::blockingSubscribe);
    }

    private static void blockingSubscribe() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .subscribeOn(Schedulers.computation())
                .map(Demonstration::intenseCalculation)
                .blockingSubscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done!"));
    }
}
