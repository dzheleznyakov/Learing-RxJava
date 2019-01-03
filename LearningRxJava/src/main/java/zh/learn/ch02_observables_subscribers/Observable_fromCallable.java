package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;

public class Observable_fromCallable {
    public static void main(String[] args) {
        Observable.fromCallable(() -> 1 / 0)
                .subscribe(i -> System.out.println("Received: " + i), e -> System.out.println("Error Captured: " + e));
    }
}
