package zh.learn.ch05_multicasting_replaying_caching;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import static zh.learn.Demonstration.demonstrate;

public class Observable_hot_vs_cold {
    public static void main(String[] args) {
        demonstrate("Cold Observable: one stream for each observer", Observable_hot_vs_cold::coldStreamForEachObserver);
        demonstrate("Hot Observable: one stream for all observers", Observable_hot_vs_cold::hotStreamForAllObservers);
    }

    private static void coldStreamForEachObserver() {
        Observable<Integer> threeIntegers = Observable.range(1, 3);

        threeIntegers.subscribe(i -> System.out.println("Observer One: " + i));
        threeIntegers.subscribe(i -> System.out.println("Observer Two: " + i));
    }

    private static void hotStreamForAllObservers() {
        ConnectableObservable<Integer> threeIntegers = Observable.range(1, 3).publish();

        threeIntegers.subscribe(i -> System.out.println("Observer One: " + i));
        threeIntegers.subscribe(i -> System.out.println("Observer Two: " + i));

        threeIntegers.connect();
    }
}
