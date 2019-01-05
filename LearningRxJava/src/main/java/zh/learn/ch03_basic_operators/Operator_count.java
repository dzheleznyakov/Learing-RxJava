package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_count {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .count()
                .subscribe(s -> System.out.println("RECEIVED: " + s));

    }
}
