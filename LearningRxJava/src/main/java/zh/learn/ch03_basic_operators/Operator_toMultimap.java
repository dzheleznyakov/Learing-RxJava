package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_toMultimap {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
