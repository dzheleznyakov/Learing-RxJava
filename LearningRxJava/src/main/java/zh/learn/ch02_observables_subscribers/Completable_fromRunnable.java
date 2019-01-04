package zh.learn.ch02_observables_subscribers;

import io.reactivex.Completable;

public class Completable_fromRunnable {
    public static void main(String[] args) {
        Completable.fromRunnable(() -> runProcess())
                .subscribe(() -> System.out.println("Done"));
    }

    private static void runProcess() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
