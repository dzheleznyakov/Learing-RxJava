package zh.learn.ch03_basic_operators;

import io.reactivex.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Operator_map {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");

        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
