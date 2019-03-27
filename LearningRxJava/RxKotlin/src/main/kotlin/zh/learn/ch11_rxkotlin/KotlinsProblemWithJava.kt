package zh.learn.ch11_rxkotlin

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.zipWith

fun main(args: Array<String>) {
    val strings = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
    val numbers = Observable.range(1, 4)
    val zipped = Observable.zip(strings, numbers, BiFunction<String, Int, String> { s, n -> "$s $n" })
    zipped.subscribe(::println)

    println()

    val zipped2 = Observables.zip(strings, numbers) { s, n -> "$s $n" }
    zipped2.subscribe(::println)

    println()

    val zipped3 = strings.zipWith(numbers) { s, n -> "$s $n" }
    zipped3.subscribe(::println)
}
