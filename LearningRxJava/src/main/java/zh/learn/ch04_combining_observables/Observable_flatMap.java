package zh.learn.ch04_combining_observables;

import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_flatMap {
    public static void main(String[] args) {
        demonstrate("Flat map a string into substrings", Observable_flatMap::stringToLetters);
        demonstrate("Extract integers from strings", Observable_flatMap::getNumbersFromString);
        demonstrate("Map integers to intervals and merge", Observable_flatMap::mapIntegersToIntervals);
        demonstrate("Associate a source string with letters", Observable_flatMap::associateSourceStringWithLetters);
    }

    private static void stringToLetters() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        source.flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }

    private static void getNumbersFromString() {
        Observable.just("521934/2342/FOXTROT", "21962/12112/78886/TANGO", "283242/4542/WHISKEY/2348562")
                .flatMap(s -> Observable.fromArray(s.split("/")))
                .filter(s -> s.matches("[0-9]+"))
                .map(Integer::valueOf)
                .subscribe(System.out::println);
    }

    private static void mapIntegersToIntervals() {
        Observable.just(2, 0, 3, 10, 7)
                .flatMap(i -> i == 0
                        ? Observable.empty()
                        : Observable.interval(i, TimeUnit.SECONDS)
                                .map(i2 -> i + "s interval: " + ((i2 + 1) * i) + " seconds elapsed")
                )
                .subscribe(System.out::println);

        sleep(12000);
    }

    private static void associateSourceStringWithLetters() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .flatMap(s -> Observable.fromArray(s.split("")), (s, r) -> s + "-" + r)
                .subscribe(System.out::println);
    }
}
