package adventofcode2022.common

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun max(a: Int, b: Int): Int{
    return if(a > b) a else b
}

fun mod(a: Int):Int{
    return if(a > 0) a else -a
}