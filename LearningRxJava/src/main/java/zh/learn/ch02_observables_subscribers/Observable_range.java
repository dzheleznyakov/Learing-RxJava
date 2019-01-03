package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Observable_range {
    public static void main(String[] args) {
        Observable.range(5, 10)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
