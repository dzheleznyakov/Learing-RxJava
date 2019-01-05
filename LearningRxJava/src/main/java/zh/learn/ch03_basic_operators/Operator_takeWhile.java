package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_takeWhile {
    public static void main(String[] args) {
        Observable.range(1, 100)
                .takeWhile(i -> i < 5)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
