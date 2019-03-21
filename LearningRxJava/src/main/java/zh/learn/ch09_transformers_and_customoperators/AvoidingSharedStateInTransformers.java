package zh.learn.ch09_transformers_and_customoperators;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

import java.util.concurrent.atomic.AtomicInteger;

public class AvoidingSharedStateInTransformers extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Transformers: shared state", AvoidingSharedStateInTransformers::transformer_withIndex);
        demonstrate("Transformers: shared state---quick fix", AvoidingSharedStateInTransformers::transformer_withIndex_defer);
        demonstrate("Transformers: shared state---fix with .zip", AvoidingSharedStateInTransformers::transformer_withIndex_zip);
    }

    private static Disposable transformer_withIndex() {
        Observable<IndexedValue<String>> indexedStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(withIndex());

        Disposable d1 = indexedStrings.subscribe(v -> System.out.println("Subscriber 1: " + v));
        Disposable d2 = indexedStrings.subscribe(v -> System.out.println("Subscriber 2: " + v));

        return DisposableArray.of(d1, d2);
    }

    private static Disposable transformer_withIndex_defer() {
        Observable<IndexedValue<String>> indexedStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(withIndex_defer());

        Disposable d1 = indexedStrings.subscribe(v -> System.out.println("Subscriber 1: " + v));
        Disposable d2 = indexedStrings.subscribe(v -> System.out.println("Subscriber 2: " + v));

        return DisposableArray.of(d1, d2);
    }

    private static Disposable transformer_withIndex_zip() {
        Observable<IndexedValue<String>> indexedStrings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .compose(withIndex_zip());

        Disposable d1 = indexedStrings.subscribe(v -> System.out.println("Subscriber 1: " + v));
        Disposable d2 = indexedStrings.subscribe(v -> System.out.println("Subscriber 2: " + v));

        return DisposableArray.of(d1, d2);
    }

    private static <T> ObservableTransformer<T, IndexedValue<T>> withIndex() {
        final AtomicInteger indexer = new AtomicInteger(-1);
        return upstream -> upstream.map(v -> new IndexedValue<T>(indexer.incrementAndGet(), v));
    }

    private static <T> ObservableTransformer<T, IndexedValue<T>> withIndex_defer() {
        return upstream -> Observable.defer(() -> {
            AtomicInteger indexer = new AtomicInteger((-1));
            return upstream.map(v -> new IndexedValue<T>(indexer.incrementAndGet(), v));
        });
    }

    private static <T> ObservableTransformer<T, IndexedValue<T>> withIndex_zip() {
        return upstream -> Observable.zip(upstream,
                Observable.range(0, Integer.MAX_VALUE),
                (v, i) -> new IndexedValue<>(i, v)
        );
    }

    private static final class IndexedValue<T> {
        final int index;
        final T value;

        IndexedValue(int index, T value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return index + " - " + value;
        }
    }
}
