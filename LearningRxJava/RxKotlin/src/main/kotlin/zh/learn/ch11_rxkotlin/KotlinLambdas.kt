package zh.learn.ch11_rxkotlin

import io.reactivex.Observable

fun main(args: Array<String>) {
    Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .map { it.length }
            .subscribe { println(it) }

    println("------")
    Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .map(String::length)
            .subscribe(::println)

    println("------")
    Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
            .map { it.length }
            .scan(0) { total, next -> total + next}
            .subscribe { println("Rolling sum of String lengths is $it") }
}
