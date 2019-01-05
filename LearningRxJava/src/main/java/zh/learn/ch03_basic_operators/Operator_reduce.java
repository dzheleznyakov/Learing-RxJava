package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_reduce {
    public static void main(String[] args) {
        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce((total, next) -> total + next)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        Observable.just(5, 3, 7, 10, 2, 14)
                .reduce("", (total, next) -> total + (total.equals("") ? "" : ",") + next)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
