package adventofcode2022

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

//     R   P   S
// R   3   6   0
// P   0   3   6
// S   6   0   3
var scoreMatrix: Array<IntArray> = arrayOf(intArrayOf(3, 6, 0), intArrayOf(0, 3, 6), intArrayOf(6, 0, 3))

//     L   D   W
// R   2   0   1
// P   0   1   2
// S   1   2   0
var scoreMatrixByGameResult: Array<IntArray> = arrayOf(intArrayOf(2, 0, 1), intArrayOf(0, 1, 2), intArrayOf(1, 2, 0))

fun getGameScore(opponentResponse: Int, gameResult: Int): Int{
    var response = scoreMatrixByGameResult[opponentResponse][gameResult];
    return scoreMatrix[opponentResponse][response] + response + 1;
}

fun main(args:Array<String>) {
    var rounds: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/2.2.txt")

    var score = 0
    for(round in rounds){
        score += getGameScore(round[0].code - 65, round[2].code - 88)
    }
    
    println(score)
}