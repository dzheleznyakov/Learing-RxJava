package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class Disposable_from_create {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.create(observableEmitter -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    while (!observableEmitter.isDisposed()) {
                        observableEmitter.onNext(i);
                    }
                    if (observableEmitter.isDisposed()) {
                        return;
                    }
                }
                observableEmitter.onComplete();
                ;
            } catch (Throwable e) {
                observableEmitter.onError(e);
            }
        });
    }
}
