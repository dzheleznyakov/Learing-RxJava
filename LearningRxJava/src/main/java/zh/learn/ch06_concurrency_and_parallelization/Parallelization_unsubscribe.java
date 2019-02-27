package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Parallelization_unsubscribe extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Main thread gets busy on disposing", Parallelization_unsubscribe::mainThreadBusy);
        demonstrate("Make another thread busy instead of the main one", Parallelization_unsubscribe::unsubscribeOnBasic);
    }

    private static void mainThreadBusy() {
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Disposing on thread " + Thread.currentThread().getName()))
                .subscribe(i -> System.out.println("Received " + i));

        sleep(3000);
        d.dispose();
        sleep(3000);
    }

    private static void unsubscribeOnBasic() {
        Disposable d = Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> System.out.println("Disposing on thread " + Thread.currentThread().getName()))
                .unsubscribeOn(Schedulers.io())
                .subscribe(i -> System.out.println("Received " + i));

        sleep(3000);
        d.dispose();
        sleep(3000);
    }
}
