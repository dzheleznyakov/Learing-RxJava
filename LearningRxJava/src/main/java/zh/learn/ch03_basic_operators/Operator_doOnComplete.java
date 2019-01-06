package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_doOnComplete {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnComplete(() -> System.out.println("Source is done emitting!"))
                .map(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
