package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Observable_error {
    public static void main(String[] args) {
        Observable<String> error = Observable.error(() -> new Exception("Crash and burn!"));
        error.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done!"));
    }
}
