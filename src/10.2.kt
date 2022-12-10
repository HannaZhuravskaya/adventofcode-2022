package adventofcode2022

import java.io.File
import java.util.ArrayDeque

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun drawPixel(pixels:MutableList<Int>, cycle:Int, x:Int){
    var cycleXPos = cycle - 1
    var xPos = (cycleXPos/40) * 40 + x
    
    if(cycleXPos == xPos || cycleXPos == xPos - 1 || cycleXPos == xPos + 1){
        pixels.add(1)
    }
    else{
        pixels.add(0)
    }
}

fun printCpu(pixels:MutableList<Int>){
    for(i in 0..pixels.size-1){
        var sign:Char = if(pixels[i] == 1) '#' else ' '
        print(sign)

        if((i+1)%40 == 0){
            print("\n")
        }
    }
}

fun main(args:Array<String>) { 
    val addRegex = "addx ([-]{0,1}\\d+)".toRegex()

    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/10.2.txt")

    var x = 1
    var cycle = 1
    var pixels: MutableList<Int> = mutableListOf()

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
            drawPixel(pixels, cycle, x)
            ++cycle
            --cyclesToExecute
        }

        x += addValue
    }

    drawPixel(pixels, cycle, x)
    printCpu(pixels)
}