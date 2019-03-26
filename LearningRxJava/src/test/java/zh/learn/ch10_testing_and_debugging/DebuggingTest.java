package zh.learn.ch10_testing_and_debugging;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

public class DebuggingTest {
    @Test
    public void debugWalkthrough() {
        TestObserver<String> testObserver = new TestObserver<>();

        Observable<String> items = Observable.just("521934/2342/Foxtrot",
                "Bravo/12112/78886/Tango",
                "283242/4542/Whiskey/2348562");

        items.doOnNext(s -> System.out.println("Source pushed: " + s))
                .concatMap(s -> Observable.fromArray(s.split("/")))
                .doOnNext(s -> System.out.println("concatMap() pushed: " + s))
                .filter(s -> s.matches("[A-Z]+"))
                .doOnNext(s -> System.out.println("filter() pushed: " + s))
                .subscribe(testObserver);

        System.out.println(testObserver.values());
        testObserver.assertValues("Foxtrot", "Bravo", "Tango", "Whiskey");
    }
}
