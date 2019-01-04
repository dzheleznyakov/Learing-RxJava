package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Single_returned {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");

        source.first("Nil")
                .subscribe(System.out::println);
    }
}
