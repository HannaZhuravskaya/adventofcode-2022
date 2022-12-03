package adventofcode2022

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun main(args:Array<String>) {
    var fileData: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/1.txt")

    var maxNum = 0
    var curNum = 0
    for (row in fileData){
        if(row.isEmpty()){
            maxNum = if(curNum > maxNum) curNum else maxNum
            curNum = 0
        }
        else {
            curNum += row.toInt()
        }
    }
    
    maxNum = if(curNum > maxNum) curNum else maxNum

    println(maxNum)
}