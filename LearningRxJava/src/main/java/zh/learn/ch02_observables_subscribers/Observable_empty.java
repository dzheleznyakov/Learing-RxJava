package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Observable_empty {
    public static void main(String[] args) {
        Observable<String> empty = Observable.empty();

        empty.subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done!"));
    }
}
