package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Observable_interval_cold {
    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        seconds.subscribe(l -> System.out.println("Observer 1: " + l));

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
