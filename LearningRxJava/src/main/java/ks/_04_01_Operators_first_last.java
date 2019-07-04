package ks;

import io.reactivex.Observable;

public class _04_01_Operators_first_last {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");


        System.out.println("----- first -----");
        source.first("defaultItem")
                .subscribe(System.out::println);

        System.out.println("\n----- last -----");
        source.last("defaultItem")
                .subscribe(System.out::println);
    }
}
