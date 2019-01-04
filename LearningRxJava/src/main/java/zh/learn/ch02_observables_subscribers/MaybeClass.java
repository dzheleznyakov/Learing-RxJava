package zh.learn.ch02_observables_subscribers;

import io.reactivex.Maybe;

public class MaybeClass {
    public static void main(String[] args) {
        Maybe<Integer> presentSource = Maybe.just(100);
        presentSource.subscribe(
                s -> System.out.println("Process 1 received: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Process 1 done!"));

        Maybe<Integer> emptySource = Maybe.empty();
        emptySource.subscribe(
                s -> System.out.println("Process 2 received: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Process 2 done!"));
    }
}
