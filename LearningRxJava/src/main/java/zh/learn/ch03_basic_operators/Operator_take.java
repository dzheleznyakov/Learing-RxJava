package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_take {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .take(3)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
