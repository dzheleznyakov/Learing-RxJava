package zh.learn.ch10_testing_and_debugging;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestObserverTest {
    @Test
    public void usingTestObserver() {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .take(5);

        TestObserver<Long> testObserver = new TestObserver<>();

        testObserver.assertNotSubscribed();

        source.subscribe(testObserver);

        testObserver.assertSubscribed()
            .awaitTerminalEvent();

        testObserver.assertComplete()
                .assertNoErrors()
                .assertValueCount(5)
                .assertValues(0L, 1L, 2L, 3L, 4L);
    }
}
