package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_skip {
    public static void main(String[] args) {
        Observable.range(1, 100)
                .skip(90)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
