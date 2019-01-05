package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_elementAt {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                .elementAt(3)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
