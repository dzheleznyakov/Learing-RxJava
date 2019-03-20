package zh.learn.ch09_transformers_and_customoperators;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import zh.learn.Demonstration;

public class UnderstandingObservableTransformers extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Use custom operator to collect emissions to list", UnderstandingObservableTransformers::useObservableTransformer_toImmutableList);
        demonstrate("Use custom operator to join emissions to string", UnderstandingObservableTransformers::useObservableTransformer_joinToString);
    }

    private static void useObservableTransformer_toImmutableList() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(toImmutableList())
                .subscribe(System.out::println);

        Observable.range(1, 10)
                .compose(toImmutableList())
                .subscribe(System.out::println);
    }

    private static void useObservableTransformer_joinToString() {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(joinToString("/"))
                .subscribe(System.out::println);
    }

    private static <T> ObservableTransformer<T, ImmutableList<T>> toImmutableList() {
        return upstream -> upstream.collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .toObservable();
    }

    private static ObservableTransformer<String, String> joinToString(String separator) {
        return upstream -> upstream
                .collect(StringBuilder::new, (b, s) -> {
                    if (b.length() == 0)
                        b.append(s);
                    else
                        b.append(separator).append(s);
                })
                .map(StringBuilder::toString)
                .toObservable();
    }
}
