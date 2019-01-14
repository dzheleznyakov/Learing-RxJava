package zh.learn;

import io.reactivex.disposables.Disposable;

import java.util.concurrent.ThreadLocalRandom;
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

    public static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }
}
