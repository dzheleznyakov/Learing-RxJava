package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Operator_delay {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

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
