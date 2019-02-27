package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import zh.learn.Demonstration;

import java.util.HashSet;

public class Buffering_fixed_size extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Buffer emissions to lists", Buffering_fixed_size::bufferToList);
        demonstrate("Buffer emissions to sets", Buffering_fixed_size::bufferToSet);
        demonstrate("When buffering, skip every third element", Buffering_fixed_size::skipEveryThirdElement);
        demonstrate("Rolling buffers", Buffering_fixed_size::rollingBuffers);
        demonstrate("Rolling buffers with filtering out the remaining buffer", Buffering_fixed_size::rollingBuffersWithFilter);
    }

    private static void bufferToList() {
        Observable.range(1, 50)
                .buffer(8)
                .subscribe(l -> System.out.println(l.getClass().getSimpleName() + ": " + l));
    }

    private static void bufferToSet() {
        Observable.range(1, 50)
                .buffer(8, HashSet::new)
                .subscribe(s -> System.out.println(s.getClass().getSimpleName() + ": " + s));
    }

    private static void skipEveryThirdElement() {
        Observable.range(1, 10)
                .buffer(2, 3)
                .subscribe(System.out::println);
    }

    private static void rollingBuffers() {
        Observable.range(1, 10)
                .buffer(3, 1)
                .subscribe(System.out::println);
    }

    private static void rollingBuffersWithFilter() {
        Observable.range(1, 10)
                .buffer(2, 1)
                .filter(c -> c.size() == 2)
                .subscribe(System.out::println);
    }
}
