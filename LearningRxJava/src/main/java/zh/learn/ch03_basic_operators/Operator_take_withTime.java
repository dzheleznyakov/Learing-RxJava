package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Operator_take_withTime {
    public static void main(String[] args) {
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

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
