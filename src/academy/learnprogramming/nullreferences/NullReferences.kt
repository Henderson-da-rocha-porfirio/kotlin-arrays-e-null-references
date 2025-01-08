package academy.learnprogramming.nullreferences

fun main(args: Array<String>) {

//    val str: String? = "This isn't null"
//    str?.let { printText(it) }
//
//    val str4 : String? = null
//    val anotherStr = "This isn't nullable"
//    println(str4 == anotherStr)
//
//    val str2 = str!!
//    val str3: String = str2.toUpperCase()

    val nullableInts = arrayOfNulls<Int>(5)
    for (i in nullableInts) {
        println(i)
    }
    println(nullableInts[3].toString())
    nullableInts[3].toString()
}

fun printText(text: String) {
    println(text)
}





