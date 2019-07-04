package ks;

import io.reactivex.Observable;

public class _03_ErrorHandling {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "", "Gamma", "Delta", "Epsilon");

        source.map(String::length)
                .map(l -> l * 10 / l)
                .subscribe(System.out::println);
//                .subscribe(System.out::println, t -> System.out.println(t.getMessage()));
    }
}
