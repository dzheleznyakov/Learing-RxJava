package zh.learn.ch11_rxkotlin

import io.reactivex.Observable

fun main(args: Array<String>) {
    val source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
    val asSet = source.toSet()
    asSet.subscribe({ it.forEach(::println)}, Throwable::printStackTrace)

    println()

    val intSource = Observable.just(100, 50, 250, 150)
    val total = intSource.sum()
    total.subscribe(::println, Throwable::printStackTrace)
}

fun <T> Observable<T>.toSet() =
        collect({ HashSet<T>() }, { set, next -> set.add(next) })
                .map { it as Set<T> }

fun Observable<Int>.sum() =
        reduce(0) { total, next -> total + next }
