package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_all {
    public static void main(String[] args) {
        Observable.just(5, 3, 7, 11, 2, 14)
                .all(i -> i < 10)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
