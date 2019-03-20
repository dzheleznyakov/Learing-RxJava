package zh.learn.ch08_flowables_and_backpressure;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Flowable_generate extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Flowable: random generator", Flowable_generate::customFlowable_randomGenerator);
        demonstrateLast("Flowable: reversed range", Flowable_generate::customFlowable_rangeReverse);
    }

    private static Disposable customFlowable_randomGenerator() {
        Disposable d = randomGenerator(1, 10000)
                .subscribeOn(Schedulers.computation())
                .doOnNext(i -> System.out.println("Emitting " + i))
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    sleep(50);
                    System.out.println("Received " + i);
                });
        sleepUntil(d::isDisposed, 10000);

        return d;
    }

    private static Disposable customFlowable_rangeReverse() {
        Disposable d = rangeReverse(100, -100)
                .subscribeOn(Schedulers.computation())
                .doOnNext(i -> System.out.println("Emitting " + i))
                .observeOn(Schedulers.io())
                .subscribe(i -> {
                    sleep(50);
                    System.out.println("Received " + i);
                });
        sleepUntil(d::isDisposed, 50000);

        return d;
    }

    private static Flowable<Integer> randomGenerator(int min, int max) {
        return Flowable.generate(emitter -> emitter.onNext(ThreadLocalRandom.current().nextInt(min, max)));
    }

    static Flowable<Integer> rangeReverse(int upperBound, int lowerBound) {
        return Flowable.generate(() -> new AtomicInteger(upperBound + 1),
                (state, emitter) -> {
                    int current = state.decrementAndGet();
                    emitter.onNext(current);
                    if (current == lowerBound)
                        emitter.onComplete();
                }
        );
    }
}
