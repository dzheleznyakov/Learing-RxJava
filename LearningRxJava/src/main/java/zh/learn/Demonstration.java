package zh.learn;

public class Demonstration {
    public static void demonstrate(String header, Runnable code) {
        System.out.println("-------------");
        System.out.println(header);
        System.out.println("-------------");
        code.run();
        System.out.println();
        System.out.println();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
