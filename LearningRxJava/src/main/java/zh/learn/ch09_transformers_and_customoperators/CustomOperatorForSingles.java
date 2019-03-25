package zh.learn.ch09_transformers_and_customoperators;

import io.reactivex.Observable;
import io.reactivex.SingleTransformer;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;

import java.util.Collection;
import java.util.Collections;

public class CustomOperatorForSingles extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Transformer: to Single", CustomOperatorForSingles::transformToSingleton);
    }

    private static Disposable transformToSingleton() {
        return Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .toList()
                .compose(toUnmodifiable())
                .subscribe(System.out::println);
    }

    private static <T> SingleTransformer<Collection<T>, Collection<T>> toUnmodifiable() {
        return singleObserver -> singleObserver.map(Collections::unmodifiableCollection);
    }
}
