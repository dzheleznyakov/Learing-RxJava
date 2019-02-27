package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

public class Windowing_fixed_size extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Windowing: buffer as observable", Windowing_fixed_size::understandingWindowing);
        demonstrate("Windowing: buffer 2, skip 3", Windowing_fixed_size::windowingWithSkip);
    }

    private static Disposable understandingWindowing() {
        return Observable.range(1, 50)
                .window(8)
                .flatMapSingle(obs -> obs.reduce(
                        "",
                        (total, next) -> total + (total.equals("") ? "" : "|") + next))
                .subscribe(System.out::println);
    }

    private static Disposable windowingWithSkip() {
        return Observable.range(1, 50)
                .window(2, 3)
                .flatMapSingle(obs -> obs.reduce(
                        "",
                        (total, next) -> total + (total.equals("") ? "" : "|") + next))
                .subscribe(System.out::println);
    }
}
