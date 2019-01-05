package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_repeat {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .repeat(2)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
