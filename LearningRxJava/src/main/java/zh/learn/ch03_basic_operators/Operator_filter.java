package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_filter {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s -> s.length() != 5)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
