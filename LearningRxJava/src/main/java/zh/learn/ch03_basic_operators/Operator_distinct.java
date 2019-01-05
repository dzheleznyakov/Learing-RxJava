package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_distinct {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .map(String::length)
                .distinct()
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .distinct(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
