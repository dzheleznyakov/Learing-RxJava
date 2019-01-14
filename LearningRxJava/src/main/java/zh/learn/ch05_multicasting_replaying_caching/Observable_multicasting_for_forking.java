package zh.learn.ch05_multicasting_replaying_caching;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import zh.learn.DisposableArray;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.*;

public class Observable_multicasting_for_forking {
    public static void main(String[] args) {
        demonstrate("Use multicasting for forking the stream", Observable_multicasting_for_forking::forkStream);
        demonstrate("Fork with autoConnect()", Observable_multicasting_for_forking::forStreamWithAutoConnect);
        demonstrate("Auto-connect of the first subscriber", Observable_multicasting_for_forking::autoConnectOnFirstSubscriber);
        demonstrate("refCount(): disposes when there are no subscribers", Observable_multicasting_for_forking::demonstrateRefCount);
    }

    private static Disposable forkStream() {
        ConnectableObservable<Integer> threeRandoms = Observable.range(1, 3)
                .map(i -> randomInt()).publish();

        Disposable d1 = threeRandoms.subscribe(i -> System.out.println("Observer 1: " + i));

        Disposable d2 = threeRandoms.reduce(0, (total, next) -> total + next)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        Disposable d3 = threeRandoms.connect();

        return DisposableArray.of(d1, d2, d3);
    }

    private static Disposable forStreamWithAutoConnect() {
        Observable<Integer> threeRandom = Observable.range(1, 3)
                .map(i -> randomInt())
                .publish()
                .autoConnect(2);

        Disposable d1 = threeRandom.subscribe(i -> System.out.println("Observer 1: " + i));

        Disposable d2 = threeRandom.reduce(0, (total, next) -> total + next)
                .subscribe(i -> System.out.println("Observer 2: " + i));

        return DisposableArray.of(d1, d2);
    }

    private static Disposable autoConnectOnFirstSubscriber() {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .publish()
                .autoConnect();

        Disposable d1 = seconds.subscribe(i -> System.out.println("Observer 1: " + i));

        sleep(3000);

        Disposable d2 = seconds.subscribe(i -> System.out.println("Observer 2: " + i));

        sleep(3000);

        return DisposableArray.of(d1, d2);
    }

    private static Disposable demonstrateRefCount() {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .publish()
                .refCount();

        Disposable d1 = seconds.take(5)
                .subscribe(l -> System.out.println("Observer 1: " + l));

        sleep(3000);

        Disposable d2 = seconds.take(2)
                .subscribe(l -> System.out.println("Observer 2: " + l));

        sleep(3000);

        Disposable d3 = seconds.subscribe(l -> System.out.println("Observer 3: " + l));

        sleep(3000);

        return DisposableArray.of(d1, d2, d3);
    }
}
