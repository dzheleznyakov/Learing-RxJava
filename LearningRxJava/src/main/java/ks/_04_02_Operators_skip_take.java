package ks;

import io.reactivex.Observable;

public class _04_02_Operators_skip_take {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");


        System.out.println("----- skip -----");
        source.skip(2)
                .subscribe(System.out::println);

        System.out.println("\n----- take -----");
        source.take(2)
                .subscribe(System.out::println);
    }
}
