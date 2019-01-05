package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_defaultIfEmpty {
    public static void main(String[] args) {
        Observable<String> items = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        items.filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("None")
                .subscribe(System.out::println);
    }
}
