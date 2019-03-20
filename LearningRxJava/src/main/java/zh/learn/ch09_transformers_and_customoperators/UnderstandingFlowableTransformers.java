package zh.learn.ch09_transformers_and_customoperators;

import com.google.common.collect.ImmutableList;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import zh.learn.Demonstration;

public class UnderstandingFlowableTransformers extends Demonstration {
    public static void main(String[] args) {
        demonstrate("Use custom operator to collect emissions to list", UnderstandingFlowableTransformers::useFlowableTransfoermer_toImmiutableList);
    }

    private static void useFlowableTransfoermer_toImmiutableList() {
        Flowable.just("Alpha", "Beta", "Gamma", "Delta",  "Epsilon")
                .compose(toImmutableList())
                .subscribe(System.out::println);

        Flowable.range(1, 10)
                .compose(toImmutableList())
                .subscribe(System.out::println);
    }

    private static <T> FlowableTransformer<T, ImmutableList<T>> toImmutableList() {
        return upstream -> upstream.collect(ImmutableList::<T>builder, ImmutableList.Builder::add)
                .map(ImmutableList.Builder::build)
                .toFlowable();
    }
}
