package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_skipWhile {
    public static void main(String[] args) {
        Observable.range(1, 100)
                .skipWhile(i -> i <= 95)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
