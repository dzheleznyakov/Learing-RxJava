package zh.learn.ch04_combining_observables;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_concat {
    public static void main(String[] args) {
        demonstrate("Simple concatenation", Observable_concat::simpleConcatenation);
        demonstrate("Concatenation with operator", Observable_concat::concatenationAsOperator);
        demonstrate("Concatenation with infinite sources", Observable_concat::concatenationWithInfiniteSources);
        demonstrate("Map and concatenate", Observable_concat::mapAndConcatenate);
    }

    private static void simpleConcatenation() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
        Observable.concat(source1, source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void concatenationAsOperator() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
        source1.concatWith(source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }

    private static void concatenationWithInfiniteSources() {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> l + 1)
                .map(l -> "Source 1: " + l + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source 2: " + l + " milliseconds");

        Observable.concat(source1, source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        sleep(5050);
    }

    private static void mapAndConcatenate() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .concatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }
}
