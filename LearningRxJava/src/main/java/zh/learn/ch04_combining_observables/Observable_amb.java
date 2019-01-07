package zh.learn.ch04_combining_observables;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_amb {
    public static void main(String[] args) {
        demonstrate("Emit ambiguously; second source wins", Observable_amb::emitAmbiguously);
        demonstrate("Emit ambiguously with operator; second source wins", Observable_amb::emitAmbiguouslyWithOperator);
    }

    private static Disposable emitAmbiguously() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(i -> i + 1)
                .map(l -> "Source 1: " + l + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source 2: " + l + " milliseconds");

        Disposable disposable = Observable.amb(Arrays.asList(source1, source2))
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        sleep(5050);

        return disposable;
    }

    private static Disposable emitAmbiguouslyWithOperator() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(i -> i + 1)
                .map(l -> "Source 1: " + l + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source 2: " + l + " milliseconds");

        Disposable disposable = source1.ambWith(source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        sleep(5050);

        return disposable;
    }
}
