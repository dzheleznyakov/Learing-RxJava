package zh.learn.ch10_testing_and_debugging;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ManipulatingTimeTest {
    @Test
    public void usingTestScheduler() {
        TestScheduler testScheduler = new TestScheduler();
        TestObserver<Long> testObserver = new TestObserver<>();

        Observable<Long> minuteTicker = Observable.interval(1, TimeUnit.MINUTES, testScheduler);
        minuteTicker.subscribe(testObserver);

        testScheduler.advanceTimeBy(30, TimeUnit.SECONDS);
        testObserver.assertValueCount(0);

        testScheduler.advanceTimeBy(70, TimeUnit.SECONDS);
        testObserver.assertValueCount(1);

        testScheduler.advanceTimeBy(89, TimeUnit.MINUTES);
        testObserver.assertValueCount(90);
    }
}
