package zh.learn.ch03_basic_operators;

import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;

import java.util.HashSet;

public class Operator_collect {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .collect(HashSet::new, HashSet::add)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        System.out.println();

        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
                .collect(ImmutableList::builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
