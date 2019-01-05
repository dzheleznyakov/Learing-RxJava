package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.util.concurrent.CopyOnWriteArrayList;

public class Operator_toList {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList()
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println();

        Observable.range(1, 1000)
                .toList(1000)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList(CopyOnWriteArrayList::new)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
