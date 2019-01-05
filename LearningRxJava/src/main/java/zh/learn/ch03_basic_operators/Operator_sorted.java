package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.util.Comparator;

public class Operator_sorted {
    public static void main(String[] args) {
        Observable<Integer> numbers = Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3);

        numbers.sorted()
                .subscribe(System.out::println);

        System.out.println();

        numbers.sorted(Comparator.reverseOrder())
                .subscribe(System.out::println);

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .sorted(Comparator.comparingInt(String::length))
                .subscribe(System.out::println);
    }
}
