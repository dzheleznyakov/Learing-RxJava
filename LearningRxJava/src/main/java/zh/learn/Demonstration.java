package zh.learn;

import io.reactivex.disposables.Disposable;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Demonstration {
    public static void demonstrate(String header, Runnable code) {
        printHeader(header);
        code.run();
        printTail();
    }

    public static void demonstrate(String header, Supplier<Disposable> observableRunner) {
        printHeader(header);
        Disposable disposable = observableRunner.get();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        printTail();
    }

    public static void demonstrateWithTimeGauge(String header, Runnable code) {
        printHeader(header);
        long beginning = System.currentTimeMillis();
        code.run();
        System.out.println("Time taken: " + (System.currentTimeMillis() - beginning) + " ms");
        printTail();
    }

    public static void demonstrateWithTimeGauge(String header, Supplier<Disposable> observableRunner) {
        printHeader(header);
        long beginning = System.currentTimeMillis();
        Disposable disposable = observableRunner.get();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - beginning) + " ms");
        printTail();
    }

    private static void printTail() {
        System.out.println();
        System.out.println();
    }

    private static void printHeader(String header) {
        System.out.println("-------------");
        System.out.println(header);
        System.out.println("-------------");
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepWhile(BooleanSupplier test, int bound) {
        long start = System.currentTimeMillis();
        while (!test.getAsBoolean() && (System.currentTimeMillis() - start) < bound) {
            sleep(5);
        }
    }

    public static int randomInt() {
        return randomInt(100000);
    }

    public static int randomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static <T> T intenseCalculation(T value) {
        sleep(randomInt(3000));
        return value;
    }
}
