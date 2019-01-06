package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_doOnNext {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnNext(s -> System.out.println("Processing: " + s))
                .map(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
