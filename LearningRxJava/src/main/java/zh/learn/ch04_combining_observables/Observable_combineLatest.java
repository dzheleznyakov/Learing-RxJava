package zh.learn.ch04_combining_observables;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_combineLatest {
    public static void main(String[] args) {
        demonstrate("Simple combineLast", Observable_combineLatest::combineLatest);
    }

    private static Disposable combineLatest() {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = Observable.combineLatest(source1, source2, (l1, l2) -> "SOURCE 1: " + l1 + "  SOURCE 2: " + l2)
                .subscribe(System.out::println);
        sleep(3050);
        return disposable;
    }
}
