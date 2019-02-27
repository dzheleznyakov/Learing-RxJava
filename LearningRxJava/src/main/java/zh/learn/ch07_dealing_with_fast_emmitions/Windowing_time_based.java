package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Windowing_time_based extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Windowing: slice each second", Windowing_time_based::windowingWithTiming);
    }

    private static Disposable windowingWithTiming() {
        Disposable d = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .window(1, TimeUnit.SECONDS)
                .flatMapSingle(obs -> obs.reduce(
                        "",
                        (total, next) -> total + (total.equals("") ? "" : "|") + next))
                .subscribe(System.out::println);

        sleep(5000);
        return d;
    }
}
