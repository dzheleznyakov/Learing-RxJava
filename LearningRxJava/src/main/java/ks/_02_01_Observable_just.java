package ks;

import io.reactivex.Observable;

public class _02_01_Observable_just {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");

        source.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(System.out::println);
    }
}
