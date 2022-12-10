package adventofcode2022

import adventofcode2022.common.*

//     R   P   S
// R   3   6   0
// P   0   3   6
// S   6   0   3
var scoreMatrix: Array<IntArray> = arrayOf(intArrayOf(3, 6, 0), intArrayOf(0, 3, 6), intArrayOf(6, 0, 3))

fun getGameScore(opponentResponse: Int, response: Int): Int{
    return scoreMatrix[opponentResponse][response] + response + 1;
}

fun main(args:Array<String>) {
    var rounds: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/2.txt")

    var score = 0
    for(round in rounds){
        score += getGameScore(round[0].code - 65, round[2].code - 88)
    }
    
    println(score)
}