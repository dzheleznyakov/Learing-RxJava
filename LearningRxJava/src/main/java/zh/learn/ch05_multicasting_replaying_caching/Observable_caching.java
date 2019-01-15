package zh.learn.ch05_multicasting_replaying_caching;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static zh.learn.Demonstration.demonstrate;
import static zh.learn.Demonstration.sleep;

public class Observable_caching {
    public static void main(String[] args) {
        demonstrate("Caching", Observable_caching::caching);
    }

    private static Disposable caching() {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .cache();

        Disposable d1 = seconds.subscribe(l -> System.out.println("Observer 1: " + l));

        sleep(3000);
        d1.dispose();

        Disposable d2 = seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(3000);

        return d2;
    }
}
