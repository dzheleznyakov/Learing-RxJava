package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Maybe_returned {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source.firstElement().subscribe(
                s -> System.out.println("RECEIVED: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Done!"));
    }
}
