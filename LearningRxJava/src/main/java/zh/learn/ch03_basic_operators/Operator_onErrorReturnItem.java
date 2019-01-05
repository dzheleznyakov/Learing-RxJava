package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_onErrorReturnItem {
    public static void main(String[] args) {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .map(i -> 10 / i)
                .onErrorReturnItem(-1)
                .subscribe(i -> System.out.println("RECEIVED: " + i), e -> System.out.println("RECEIVED ERROR: " + e));
    }
}
