package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Observable_never {
    public static void main(String[] args) {
        Observable<String> empty = Observable.never();

        empty.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done!"));

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
