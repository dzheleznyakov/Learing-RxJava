package zh.learn.ch05_multicasting_replaying_caching;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.DisposableArray;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_replay {
    public static void main(String[] args) {
        demonstrate("Demonstrate replay", Observable_replay::replay);
        demonstrate("Replay with buffer size specified", Observable_replay::replayWithBuffer);
        demonstrate("Replay with refCount replays the whole stream", Observable_replay::replayWithBufferRefCount);
        demonstrate("Replay last seconds", Observable_replay::replayWithTime);
        demonstrate("Replay last seconds with buffer size 1", Observable_replay::replayWithTimeAndBufferSize);
    }

    private static Disposable replay() {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();

        Disposable d1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));

        sleep(3000);

        Disposable d2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));

        sleep(3000);

        return DisposableArray.of(d1, d2);
    }

    private static Disposable replayWithBuffer() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .replay(1)
                .autoConnect();

        Disposable d1 = source.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable d2 = source.subscribe(l -> System.out.println("Observer 2: " + l));
        return DisposableArray.of(d1, d2);
    }

    private static Disposable replayWithBufferRefCount() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .replay(1)
                .refCount();

        Disposable d1 = source.subscribe(l -> System.out.println("Observer 1: " + l));
        Disposable d2 = source.subscribe(l -> System.out.println("Observer 2: " + l));
        return DisposableArray.of(d1, d2);
    }

    private static Disposable replayWithTime() {
        Observable<Long> seconds = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .replay(1005, TimeUnit.MILLISECONDS)
                .autoConnect();

        Disposable d1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        sleep(2000);
        Disposable d2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(1000);
        return DisposableArray.of(d1, d2);
    }

    private static Disposable replayWithTimeAndBufferSize() {
        Observable<Long> seconds = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .replay(1, 1005, TimeUnit.MILLISECONDS)
                .autoConnect();

        Disposable d1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        sleep(2000);
        Disposable d2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(1000);
        return DisposableArray.of(d1, d2);
    }
}
