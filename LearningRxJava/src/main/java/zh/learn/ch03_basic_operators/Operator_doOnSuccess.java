package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_doOnSuccess {
    public static void main(String[] args) {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
