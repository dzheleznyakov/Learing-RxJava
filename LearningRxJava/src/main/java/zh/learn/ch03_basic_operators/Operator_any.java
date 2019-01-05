package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.time.LocalDate;

public class Operator_any {
    public static void main(String[] args) {
        Observable.just("2016-01-01", "2016-05-02", "2016-09-12", "2016_04-03")
                .map(LocalDate::parse)
                .any(dt -> dt.getMonthValue() >= 6)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
