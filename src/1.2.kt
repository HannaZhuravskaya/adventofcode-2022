package adventofcode2022

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun findMaxs(maxs:MutableList<Int>, cur:Int): MutableList<Int> {
    var min = maxs[0]
    var i = 0

    if(maxs[1] < min){
        min = maxs[1]
        i = 1
    }

    if(maxs[2] < min){
        min = maxs[2]
        i = 2
    }

    if(cur > min){
        maxs.removeAt(i)
        maxs.add(cur)
    }

    return maxs
} 

fun main(args:Array<String>) {
    var fileData: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/1.2.txt")

    var maxs: MutableList<Int> = mutableListOf<Int>(0, 0, 0);

    var curNum = 0
    for (row in fileData){
        if(row.isEmpty()){
            maxs = findMaxs(maxs, curNum);
            curNum = 0
        }
        else {
            curNum += row.toInt()
        }
    }
    
    maxs = findMaxs(maxs, curNum);

    println(maxs[0] + maxs[1] + maxs[2])
}