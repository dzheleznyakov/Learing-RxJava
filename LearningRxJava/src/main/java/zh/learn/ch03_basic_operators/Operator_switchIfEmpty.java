package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_switchIfEmpty {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(s -> System.out.println("RECEIVED: " + s), e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
