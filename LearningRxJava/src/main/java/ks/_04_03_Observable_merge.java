package ks;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class _04_03_Observable_merge {
    public static void main(String[] args) {
//        demonstrate("Merge up to four sources", _04_03_Observable_merge::mergeUpTo4Sources);
//        demonstrate("Merge as operator", _04_03_Observable_merge::mergeWith);
//        demonstrate("Merge more then four sources", _04_03_Observable_merge::mergeArray);
//        demonstrate("Merge iterable", _04_03_Observable_merge::mergeIterable);
        demonstrate("Merge infinite sources", _04_03_Observable_merge::mergeInfinite);
    }

    private static void mergeUpTo4Sources() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
        Observable.merge(source1, source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void mergeWith() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
        source1.mergeWith(source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void mergeArray() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Gamma", "Delta");
        Observable<String> source3 = Observable.just("Epsilon", "Zeta");
        Observable<String> source4 = Observable.just("Eta", "Theta");
        Observable<String> source5 = Observable.just("Iota", "Kappa");
        Observable.mergeArray(source1, source2, source3, source4, source5)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void mergeIterable() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Gamma", "Delta");
        Observable<String> source3 = Observable.just("Epsilon", "Zeta");
        Observable<String> source4 = Observable.just("Eta", "Theta");
        Observable<String> source5 = Observable.just("Iota", "Kappa");

        List<Observable<String>> sources = Arrays.asList(source1, source2, source3, source4, source5);

        Observable.merge(sources)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void mergeInfinite() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l + 1)
                .map(l -> "Source 1: " + l + " seconds");

        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source 2: " + l + " milliseconds");

        Observable.merge(source1, source2)
                .subscribe(System.out::println);
        sleep(3000);
    }
}
