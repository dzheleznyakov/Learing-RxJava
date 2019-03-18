package zh.learn.ch08_flowables_and_backpressure;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zh.learn.Demonstration;

public class UnderstandingFlowable extends Demonstration {
    public static void main(String[] args) {
        demonstrateLast("Rapid emissions",
                UnderstandingFlowable::rapidEmissions_serialised,
                UnderstandingFlowable::rapidEmissions_async,
                UnderstandingFlowable::introducingFlowable);
    }

    private static Disposable rapidEmissions_serialised() {
        return Observable.range(1, 999_999_999)
                .map(MyItem::new)
                .subscribe(myItem -> {
                    sleep(50);
                    System.out.println("Received MyItem " + myItem.id);
                });
    }

    private static void rapidEmissions_async() {
        Observable.range(1, 999_999_999)
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {
                    sleep(50);
                    System.out.println("Received MyItem " + myItem.id);
                });

        sleep(Long.MAX_VALUE);
    }

    private static void introducingFlowable() {
        Flowable.range(1, 999_999_999)
                .map(MyItem::new)
                .observeOn(Schedulers.io())
                .subscribe(myItem -> {
                    sleep(50);
                    System.out.println("Received MyItem " + myItem.id);
                });

        sleep(Long.MAX_VALUE);
    }

    static final class MyItem {
        final int id;

        MyItem(int id) {
            this.id = id;
            System.out.println("Constructing MyItem " + id);
        }
    }
}
