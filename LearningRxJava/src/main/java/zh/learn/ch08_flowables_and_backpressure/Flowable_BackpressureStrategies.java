package zh.learn.ch08_flowables_and_backpressure;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

public class Flowable_BackpressureStrategies extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Buffering Flowable", Flowable_BackpressureStrategies::customFlowable_buffer);
        demonstrate("Turn Observable into Flowable", Flowable_BackpressureStrategies::getFlowableDownstream);
        demonstrate("Trun Flowable into Observable", Flowable_BackpressureStrategies::turnFlowbaleIntoObservable);
    }

    private static void customFlowable_buffer() {
        Flowable<Integer> source = Flowable.create(emitter -> {
            for (int i = 0; i <= 1000; i++) {
                if (emitter.isCancelled())
                    return;
                emitter.onNext(i);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);

        source.observeOn(Schedulers.io())
                .subscribe(System.out::println);
        sleep(1000);
    }

    private static void getFlowableDownstream() {
        Observable<Integer> source = Observable.range(1, 1000);
        Disposable d = source.toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.io())
                .subscribe(System.out::println);

        sleepWhile(d::isDisposed, 10000);
    }

    private static void turnFlowbaleIntoObservable() {
        Flowable<Integer> integers = Flowable.range(1, 1000)
                .subscribeOn(Schedulers.computation());
        Disposable d = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .flatMap(s -> integers.map(i -> i + "-" + s).toObservable())
                .subscribe(System.out::println);
        sleepWhile(d::isDisposed, 5000);
    }
}
