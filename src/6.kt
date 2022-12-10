package adventofcode2022

import adventofcode2022.common.*

fun main(args:Array<String>) {
    var line = readFileAsLine("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/6.txt")

    val usedChar:MutableMap<Char, Int> = mutableMapOf();  
    var cnt = 0;

    for((i, ch) in line.withIndex())
    {
        if(usedChar.get(ch) == null){
            cnt++
        } 
        else
        {
            var prevCharIndex = usedChar.getValue(ch)

            if(i - prevCharIndex <= cnt){
                cnt = i - prevCharIndex
            }
            else{
                cnt++
            }
        }

        usedChar.put(ch, i)

        if(cnt == 4)
        {
            println(i+1)
            break
        }
    }    
}