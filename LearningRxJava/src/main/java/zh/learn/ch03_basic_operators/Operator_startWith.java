package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

public class Operator_startWith {
    public static void main(String[] args) {
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");
        menu.startWith("COFFEE SHOP MENU")
                .subscribe(System.out::println);


        System.out.println();

        menu.startWithArray("COFFEE SHOP MENU", "------------------")
                .subscribe(System.out::println);
    }
}
