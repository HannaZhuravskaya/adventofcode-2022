package adventofcode2022

import adventofcode2022.common.*
import java.util.LinkedList

fun readStacks(lines: List<String>, stackNumLineIndex: Int):MutableList<LinkedList<Char>>{
    var stackNumLine = lines[stackNumLineIndex]
    var stacks: MutableList<LinkedList<Char>>  = mutableListOf();

    for(i in 0..stackNumLine.length - 1){
        if(!stackNumLine[i].isDigit())
        {
            continue
        }
        
        var stack = LinkedList<Char>()
        for(j in stackNumLineIndex - 1 downTo 0){
            if(lines[j][i] != ' '){
                stack.addLast(lines[j][i])
            }
        }

        stacks.add(stack)
    }

    return stacks
}

fun main(args:Array<String>) {
    var lines = readFileAsLines("/Users/hzhuravskaya/Documents/adventofcode-2022/inputs/5.2.txt")

    var emptyLineIndex: Int = -1
    for((i, str) in lines.withIndex())
    {
        if(str.isEmpty()){
            emptyLineIndex = i
            break
        }
    }

    var stacks = readStacks(lines, emptyLineIndex - 1)

    val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
    for(move in emptyLineIndex+1..lines.size-1){
        val (cnt, from, to) = regex.find(lines[move])!!.destructured

        val tempStack = LinkedList<Char>()
        for (i in 1..cnt.toInt()){
            var value = stacks[from.toInt() - 1].removeLast()
            tempStack.addLast(value)
        }

        var pushTo = to.toInt() - 1
        while(!tempStack.isEmpty()){
            stacks[pushTo].addLast(tempStack.removeLast())
        }
    }

    var result: MutableList<Char> = mutableListOf()
    for(stack in stacks){
        if(stack.isEmpty()){
            result.add(' ')
        }
        else{
            result.add(stack.last)
        }
    }

    println(String(result.toCharArray()))
}