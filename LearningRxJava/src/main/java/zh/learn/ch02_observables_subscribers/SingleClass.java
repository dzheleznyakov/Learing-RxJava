package zh.learn.ch02_observables_subscribers;

import io.reactivex.Single;

public class SingleClass {
    public static void main(String[] args) {
        Single.just("Hello")
                .map(String::length)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }
}
