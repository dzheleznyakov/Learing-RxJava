package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Windowing_boundary_based extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Understanding boundary-based windowing", Windowing_boundary_based::understandingBoundaryBasedWindowing);
    }

    private static Disposable understandingBoundaryBasedWindowing() {
        Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
        Disposable d = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .window(cutOffs)
                .flatMapSingle(obs -> obs.reduce(
                        "",
                        (total, next) -> total + (total.equals("") ? "" : "|") + next))
                .subscribe(System.out::println);

        sleep(5000);
        return d;
    }
}
