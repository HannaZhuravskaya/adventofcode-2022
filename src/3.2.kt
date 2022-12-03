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
    var fileData: List<String> = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/3.2.txt")

    var totalPriority = 0
    for (i in 0..fileData.size-3 step 3){
        val map:MutableMap<Char, Int> = mutableMapOf();  

        for (item in fileData[i]){
            map.put(item, 10)
        }

        for (item in fileData[i+1]){
            if(map.get(item) == null)
            {
                map.put(item, 1)
            }
            else if(map.getValue(item)%10 != 1) {
                map.put(item, map.getValue(item) + 1)
            }
           
        }

        for (item in fileData[i+2]){
            if (map.get(item) == 11){
                totalPriority += getPriority(item)
                break
            }
        }
    }

    println(totalPriority)
}