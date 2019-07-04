package ks;

import io.reactivex.Observable;

public class _02_03_Observable_range {
    public static void main(String[] args) {
        Observable.range(5, 10)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
