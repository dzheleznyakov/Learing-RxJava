package zh.learn.ch11_rxkotlin

fun main(args: Array<String>) {
    val myItem = MyItem().apply {
        startProcess()
    }
}

class MyItem {
    fun startProcess() = println("Starting Process!")
}
