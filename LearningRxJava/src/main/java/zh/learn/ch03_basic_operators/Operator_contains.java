package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_contains {
    public static void main(String[] args) {
        Observable.range(1, 10000)
                .contains(9563)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
