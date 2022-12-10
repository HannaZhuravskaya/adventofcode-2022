package adventofcode2022

import adventofcode2022.common.*

fun main(args:Array<String>) { 
    val addRegex = "addx ([-]{0,1}\\d+)".toRegex()

    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/10.txt")

    var x = 1
    var cycle = 1

    var sum = 0

    for(row in lines){
        var cyclesToExecute = 0
        var addValue = 0

        if(addRegex.matches(row)){
            addValue = addRegex.find(row)!!.groupValues[1].toInt()
            cyclesToExecute = 2
        }
        else if(row == "noop"){
           cyclesToExecute = 1
        }

        while(cyclesToExecute > 0){
            println("beginning of cycle: " + cycle + ", x: " + x + ", sum: " + sum)

            if(cycle >= 20 && cycle <= 220 && (cycle - 20) % 40 == 0){
                sum += cycle * x
            }

            ++cycle
            --cyclesToExecute
        }

        x += addValue
    }

    println("beginning of cycle: " + cycle + ", x: " + x + ", sum: " + sum)

    println(sum)
}