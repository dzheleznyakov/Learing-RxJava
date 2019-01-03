package zh.learn.ch01_intro;

import io.reactivex.Observable;

public class Launcher {
    public static void main(String[] args) {
        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        myStrings
                .map(String::length)
                .subscribe(System.out::println);
    }
}
