package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.util.concurrent.ConcurrentHashMap;

public class Operator_toMap {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(s -> s.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toMap(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
