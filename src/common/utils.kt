package adventofcode2022.common

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()