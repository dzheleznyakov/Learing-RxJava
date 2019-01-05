package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_scan {
    public static void main(String[] args) {
        Observable.just(5, 3, 7, 10, 2, 14)
                .scan((accumulator, next) -> accumulator + next)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .scan(0, (total, next) -> total + 1)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
