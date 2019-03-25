package zh.learn.ch10_testing_and_debugging;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class RxJavaTest {
    @Test
    public void test_blockingSubscribe() {
        AtomicInteger hitCount = new AtomicInteger();
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .take(5);
        source.blockingSubscribe(i -> hitCount.incrementAndGet());
        assertEquals(5, hitCount.get());
    }

    @Test
    public void test_blockingFirst() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta");
        String firstWithLengthFour = source.filter(s -> s.length() == 4)
                .blockingFirst();
        assertEquals("Beta", firstWithLengthFour);
    }

    @Test
    public void test_blockingGet() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta");
        List<String> allWithLengthFour = source.filter(s -> s.length() == 4)
                .toList()
                .blockingGet();

        assertEquals(Arrays.asList("Beta", "Zeta"), allWithLengthFour);
    }

    @Test
    public void test_blockingLast() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta");
        String lastWithLengthFour = source.filter(s -> s.length() == 4)
                .blockingLast();

        assertEquals("Zeta", lastWithLengthFour);
    }

    @Test
    public void test_blockingIterable() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta");
        Iterable<String> allWithLengthFive = source.filter(s -> s.length() == 5)
                .blockingIterable();

        for (String s : allWithLengthFive) {
            assertEquals(5, s.length());
        }
    }

    @Test
    public void test_blockingForEach() {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Zeta");
        source.filter(s -> s.length() == 5)
                .blockingForEach(s -> assertEquals(5, s.length()));
    }

    @Test
    public void test_blockingNext() {
        Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS)
                .take(1000);
        Iterable<Long> iterable = source.blockingNext();

        for (Long i : iterable) {
            System.out.println(i);
        }
    }

    @Test
    public void test_blockingLatest() {
        Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS)
                .take(1000);
        Iterable<Long> iterable = source.blockingLatest();

        for (Long i : iterable) {
            System.out.println(i);
        }
    }

    @Test
    public void test_blockingMostRecent() {
        Observable<Long> source = Observable.interval(10, TimeUnit.MILLISECONDS)
                .take(5);
        Iterable<Long> iterable = source.blockingMostRecent(-1L);

        for (Long i : iterable) {
            System.out.println(i);
        }
    }
}
