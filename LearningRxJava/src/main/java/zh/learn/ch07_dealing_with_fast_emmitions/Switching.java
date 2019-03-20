package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Switching extends Demonstration {
    public static void main(String[] args) {
        demonstrate("switchMap()", Switching::intensiveCalculation);
        demonstrate("Emit every 5 seconds and kill previous instances", Switching::switchMap_killPreviousInstance);
    }

    private static Disposable intensiveCalculation() {
        Observable<String> items = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon",
                "Zeta", "Eta", "Theta", "Iota");
        Observable<String> processString = items.concatMap(
                s -> Observable.just(s).delay(randomInt(2000), TimeUnit.MILLISECONDS));

        Disposable d = processString.subscribe(System.out::println);

        sleepUntil(d::isDisposed, 20000);

        return d;
    }

    private static Disposable switchMap_killPreviousInstance() {
        Observable<String> items = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon",
                "Zeta", "Eta", "Theta", "Iota");
        Observable<String> processString = items.concatMap(
                s -> Observable.just(s).delay(randomInt(2000), TimeUnit.MILLISECONDS));

        Disposable d = Observable.interval(5, TimeUnit.SECONDS)
                .switchMap(i -> processString
                        .doOnDispose(() -> System.out.println("Disposing! Starting next"))
                ).subscribe(System.out::println);

        sleepUntil(d::isDisposed, 20000);

        return d;
    }
}
