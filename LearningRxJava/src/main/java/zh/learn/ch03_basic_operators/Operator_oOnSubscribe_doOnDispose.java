package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_oOnSubscribe_doOnDispose {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
