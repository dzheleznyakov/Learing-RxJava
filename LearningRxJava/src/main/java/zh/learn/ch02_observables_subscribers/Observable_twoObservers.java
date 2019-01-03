package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Observable_twoObservers {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source.subscribe(s -> System.out.println("Observer 1 Received: " + s));
        source.subscribe(s -> System.out.println("Observer 2 Received: " + s));
    }
}
