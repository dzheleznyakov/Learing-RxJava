package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;

public class Buffering_time_based extends Demonstration {
    public static void main(String[] args) {
        demonstrate("One second buffer", Buffering_time_based::oneSecondBuffers);
        demonstrate("One second buffer with count 2", Buffering_time_based::oneSecondBuffersWithCount);
    }

    private static Disposable oneSecondBuffers() {
        Disposable d = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        sleep(4000);

        return d;
    }

    private static Disposable oneSecondBuffersWithCount() {
        Disposable d = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(i -> (i + 1) * 300)
                .buffer(1, TimeUnit.SECONDS, 2)
                .subscribe(System.out::println);

        sleep(5000);

        return d;
    }
}
