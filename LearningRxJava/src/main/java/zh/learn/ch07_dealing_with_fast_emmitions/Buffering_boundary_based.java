package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Buffering_boundary_based extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Unerstanding boundary-based buffer", Buffering_boundary_based::understandingBoundaryBuffering);
    }

    private static Disposable understandingBoundaryBuffering() {
        Observable<Long> cutOffs = Observable.interval(1, TimeUnit.SECONDS);
        Disposable d = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .buffer(cutOffs)
                .subscribe(System.out::println);

        sleep(5000);
        return d;
    }
}
