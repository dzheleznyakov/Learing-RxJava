package zh.learn.ch08_flowables_and_backpressure;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import zh.learn.Demonstration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UnderstandingFlowableAndSubscriber extends Demonstration {
    public static void main(String[] args) {
        demonstrateLast("Subscriber",
                UnderstandingFlowableAndSubscriber::backpressureException,
                UnderstandingFlowableAndSubscriber::subscriber,
                UnderstandingFlowableAndSubscriber::customSubscriber,
                UnderstandingFlowableAndSubscriber::customSubscriberManagingRequest);
    }

    private static void backpressureException() {
        Flowable.interval(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(Demonstration::intenseCalculation)
                .subscribe(System.out::println, Throwable::printStackTrace);
        sleep(Long.MAX_VALUE);
    }

    private static void subscriber() {
        Flowable.range(1, 1000)
                .doOnNext(s -> System.out.println("Source pushed " + s))
                .observeOn(Schedulers.io())
                .map(value -> intenseCalculation(value, 200))
                .subscribe(
                        s -> System.out.println("Subscriber received " + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!")
                );

        sleep(20000);
    }

    private static void customSubscriber() {
        Flowable.range(1, 1000)
                .doOnNext(s -> System.out.println("Source pushed " + s))
                .observeOn(Schedulers.io())
                .map(i -> intenseCalculation(i, 200))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer s) {
                        sleep(50);
                        System.out.println("Subscriber received " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        sleep(20000);
    }

    private static void customSubscriberManagingRequest() {
        Flowable.range(1, 1000)
                .doOnNext(s -> System.out.println("Source pushed " + s))
                .observeOn(Schedulers.io())
                .map(i -> intenseCalculation(i, 200))
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;
                    AtomicInteger count = new AtomicInteger(0);

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        System.out.println("Requesting 40 items!");
                        subscription.request(40);
                    }

                    @Override
                    public void onNext(Integer s) {
                        sleep(50);
                        System.out.println("Subscriber received " + s);
                        if (count.incrementAndGet() % 20 == 0 && count.get() >= 40) {
                            System.out.println("Requesting 20 more!");
                            subscription.request(20);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Done!");
                    }
                });
        sleep(20000);
    }
}
