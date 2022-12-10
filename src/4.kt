package adventofcode2022

import adventofcode2022.common.*

fun main(args:Array<String>) {
    var fileData: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/4.txt")

    var cnt = 0
    for (row in fileData){
        val regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()
        val matchResult = regex.find(row)!!
        var l1 = matchResult.groupValues[1].toInt()
        var r1 = matchResult.groupValues[2].toInt()
        var l2 = matchResult.groupValues[3].toInt()
        var r2 = matchResult.groupValues[4].toInt()

        if(l1<=l2 && r2 <= r1){
            cnt++;
        }
        else if(l2 <= l1 && r1 <= r2){
            cnt++;
        }
    }
    
    println(cnt)
}