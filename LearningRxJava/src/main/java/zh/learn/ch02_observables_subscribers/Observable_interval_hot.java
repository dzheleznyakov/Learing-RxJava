package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class Observable_interval_hot {
    public static void main(String[] args) {
        ConnectableObservable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish();
        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        seconds.connect();
        sleep(5000);

        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(5000);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
