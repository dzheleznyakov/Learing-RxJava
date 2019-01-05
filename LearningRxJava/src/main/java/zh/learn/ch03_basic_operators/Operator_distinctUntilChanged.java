package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_distinctUntilChanged {
    public static void main(String[] args) {
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        System.out.println();

        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                .distinctUntilChanged(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
