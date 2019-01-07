package zh.learn.ch04_combining_observables;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.GroupedObservable;

import static zh.learn.Demonstration.demonstrate;

public class Observable_groupBy {
    public static void main(String[] args) {
        demonstrate("Group strings by length", Observable_groupBy::groupBy);
        demonstrate("Group strings by length and attach the length to the output", Observable_groupBy::groupByLengthAndAddKey);
    }

    private static Disposable groupBy() {
        Observable<GroupedObservable<Integer, String>> byLength = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(String::length);
        return byLength.flatMapSingle(Observable::toList)
                .subscribe(System.out::println);
    }

    private static Disposable groupByLengthAndAddKey() {
        return Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .groupBy(String::length)
                .flatMapSingle(grp -> grp.reduce("", (x, y) -> x.equals("") ? y : x + ", " + y)
                        .map(s -> grp.getKey() + ": " + s))
                .subscribe(System.out::println);
    }
}
