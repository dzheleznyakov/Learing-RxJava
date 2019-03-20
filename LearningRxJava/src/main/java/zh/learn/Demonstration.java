package zh.learn;

import io.reactivex.disposables.Disposable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;
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

    public static void demonstrateLast(String header, Runnable... runners) {
        if  (runners == null || runners.length == 0)
            return;
        demonstrate(header, runners[runners.length - 1]);
    }

    public static void demonstrateLast(String header, Supplier<Disposable>... observableRunners) {
        if (observableRunners == null || observableRunners.length == 0)
            return;
        demonstrate(header, observableRunners[observableRunners.length - 1]);
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
            System.out.println("INTERRUPTED: " + e.getMessage());
        }
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepWhile(BooleanSupplier wakeUp, int bound) {
        long start = System.currentTimeMillis();
        while (!wakeUp.getAsBoolean() && (System.currentTimeMillis() - start) < bound) {
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
        return intenseCalculation(value, 3000);
    }

    public static <T> T intenseCalculation(T value, int bound) {
        sleep(randomInt(bound));
        return value;
    }

    public static String getResponse(String path) {
        try {
            return new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static void write(String text, String path) {
        BufferedWriter writer = null;
        try {
            File file = new File(path);
            writer = new BufferedWriter((new FileWriter(file)));
            writer.append(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
