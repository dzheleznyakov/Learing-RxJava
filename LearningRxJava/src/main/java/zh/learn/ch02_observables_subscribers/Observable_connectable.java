package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class Observable_connectable {
    public static void main(String[] args) {
        ConnectableObservable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .publish();

        source.subscribe(s -> System.out.println("Observer 1: " + s));
        source.map(String::length)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        source.connect();
    }
}
