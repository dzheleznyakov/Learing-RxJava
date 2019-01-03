package zh.learn.ch01_intro;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class PushLauncher {
    public static void main(String[] args) {
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);

        secondIntervals.subscribe(System.out::println);

        sleep(5000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
