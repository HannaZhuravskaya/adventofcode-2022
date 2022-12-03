package adventofcode2022

import java.io.File

fun readFileAsLines(fileName: String): List<String> = File(fileName).readLines()

fun getPriority(item: Char): Int{
    var value = item.toInt()

    if(value >= 97 &&  value <= 122){
        return value - 96
    }

   if(value >= 65 &&  value <= 90){
        return value - 65 + 27
   }

   throw IllegalArgumentException("Cannot find priority")
}

fun main(args:Array<String>) {
    var fileData: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/3.txt")

    var totalPriority = 0
    for (rucksack in fileData){
        val set:MutableSet<Char> = mutableSetOf();  

        for((i, item) in rucksack.withIndex()){
            if(i < rucksack.length / 2)
            {
                set.add(rucksack[i])
            }
            else if(set.contains(item)){
                totalPriority += getPriority(item)
                break
            }
        }
    }

    println(totalPriority)
}