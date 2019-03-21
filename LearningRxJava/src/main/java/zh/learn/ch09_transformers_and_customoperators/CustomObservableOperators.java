package zh.learn.ch09_transformers_and_customoperators;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import zh.learn.Demonstration;
import zh.learn.DisposableArray;

import java.util.ArrayList;
import java.util.List;

public class CustomObservableOperators extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Use ObservableOperator: doOnEmpty()", CustomObservableOperators::useCustomOperator_doOnEmpty);
        demonstrate("Use ObservableOperator: myToList*(", CustomObservableOperators::useCustomOperator_myToList);
    }

    private static Disposable useCustomOperator_doOnEmpty() {
        Disposable d1 = Observable.range(1, 5)
                .lift(doOnEmpty(() -> System.out.println("Operation 1 Empty!")))
                .subscribe(v -> System.out.println("Operation 1: " + v));

        Disposable d2 = Observable.<Integer>empty()
                .lift(doOnEmpty(() -> System.out.println("Operation 2 Empty!")))
                .subscribe(v -> System.out.println("Operation 2: " + v));

        return DisposableArray.of(d1, d2);
    }

    private static Disposable useCustomOperator_myToList() {
        Disposable d1 = Observable.range(1, 5)
                .lift(myToList())
                .subscribe(v -> System.out.println("Operation 1: " + v));

        Disposable d2 = Observable.<Integer>empty()
                .lift(myToList())
                .subscribe(v -> System.out.println("Operation 2: " + v));

        return DisposableArray.of(d1, d2);
    }

    private static <T> ObservableOperator<T, T> doOnEmpty(Action action) {
        return observer -> new DisposableObserver<T>() {
            boolean isEmpty = true;

            @Override
            public void onNext(T value) {
                isEmpty = false;
                observer.onNext(value);
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                if (isEmpty) {
                    try {
                        action.run();
                    } catch (Exception e) {
                        onError(e);
                        return;
                    }
                }
                observer.onComplete();
            }
        };
    }

    private static <T> ObservableOperator<List<T>, T> myToList() {
        return observer -> new DisposableObserver<T>() {
            ArrayList<T> list = new ArrayList<>();

            @Override
            public void onNext(T value) {
                list.add(value);
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }

            @Override
            public void onComplete() {
                observer.onNext(list);
                observer.onComplete();
            }
        };
    }
}
