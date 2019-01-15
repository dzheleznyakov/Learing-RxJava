package zh.learn.ch05_multicasting_replaying_caching;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.*;
import zh.learn.DisposableArray;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Subjects {
    public static void main(String[] args) {
        demonstrate("Publish subject", Subjects::publishSubject);
        demonstrate("Use subject to merge and multicast", Subjects::mergeAndMulticast);
        demonstrate("Subjects are hot, so subscribe before emitting", Subjects::subjectsAreHot);
        demonstrate("BehaviourSubject: replays the last emitted item on connect", Subjects::behaviorSubject);
        demonstrate("ReplaySubject: caches and replays all items on connect", Subjects::replaySubject);
        demonstrate("AsyncSubject: emits only the last item and replays", Subjects::asyncSubject);
        demonstrate("UnicastSubject: caches emitted items till subscription", Subjects::unicastSubject);
        demonstrate("UnicastSubject: trick to multicast", Subjects::unicastSubjectForMulticasting);
    }

    private static void publishSubject() {
        Subject<String> subject = PublishSubject.create();

        subject.map(String::length)
                .subscribe(System.out::println);

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();
    }

    private static Disposable mergeAndMulticast() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> (l + 1) + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> ((l + 1) * 300) + " milliseconds");
        PublishSubject<String> subject = PublishSubject.create();

        Disposable d = subject.subscribe(System.out::println);

        source1.subscribe(subject);
        source2.subscribe(subject);

        sleep(3000);
        return d;
    }

    private static void subjectsAreHot() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

        subject.map(String::length)
                .subscribe(System.out::println);
    }

    private static void behaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.subscribe(s -> System.out.println("Observer 1: " + s));

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");

        subject.subscribe(s -> System.out.println("Observer 2: " + s));
    }

    private static void replaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();

        subject.subscribe(s -> System.out.println("Observer 1: " + s));

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

        subject.subscribe(s -> System.out.println("Observer 2: " + s));
    }

    private static void asyncSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();

        subject.subscribe(
                s -> System.out.println("Observer 1: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Observer 1 done!")
        );

        subject.onNext("Alpha");
        subject.onNext("Beta");
        subject.onNext("Gamma");
        subject.onComplete();

        subject.subscribe(
                s -> System.out.println("Observer 2: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Observer 2 done!")
        );
    }

    private static Disposable unicastSubject() {
        UnicastSubject<String> subject = UnicastSubject.create();

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> ((l + 1) * 300) + " milliseconds")
                .subscribe(subject);

        sleep(2000);

        Disposable d = subject.subscribe(s -> System.out.println("Observer 1: " + s));

        sleep(2000);

        return d;
    }

    private static Disposable unicastSubjectForMulticasting() {
        UnicastSubject<String> subject = UnicastSubject.create();

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> ((l + 1) * 300) + " milliseconds")
                .subscribe(subject);

        sleep(2000);
        Observable<String> multicast = subject.publish().autoConnect();

        Disposable d1 = multicast.subscribe(s -> System.out.println("Observer 1: " + s));
        sleep(2000);

        Disposable d2 = multicast.subscribe(s -> System.out.println("Observer 2: " + s));
        sleep(1000);

        return DisposableArray.of(d1, d2);
    }
}
