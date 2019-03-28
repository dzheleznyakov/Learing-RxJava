package zh.learn.ch11_rxkotlin

fun main(args: Array<String>) {
    val str = "GAMMA"
    val lowerCaseWithReversed = str.toLowerCase().let { it + " " + it.reversed() }
    println(lowerCaseWithReversed)
}
